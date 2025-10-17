# Achados

- **[Console]** `src/main/java/br/com/lista/MeuConsole.java` usa `ListaConvidados` para inserir/listar/atualizar/remover por nome.
- **[Serviço]** `src/main/java/br/com/lista/service/ListaConvidados.java` usa JDBC (SQLite) e coluna `tamanho_fralda`.
- **[Modelo]** `src/main/java/br/com/lista/model/Convidado.java` tem `id`, `nome`, `tamanhoFralda`.
- **[Web]** `src/main/java/br/com/lista/controller/ConvidadoController.java` responde mensagem estática.
- **[Testes]** `src/test/java/br/com/lista/controller/ConvidadoControllerTest.java` cobre apenas o endpoint de mensagem.
- **[SQL]** `src/main/resources/sql/schema.sql` e `seed.sql` existem, porém não batem com o modelo/serviço.
- **[Config]** `src/main/resources/application.properties` só define a porta HTTP.
- **[Ausentes]** Referências a `br.com.lista.database.ConexaoSQLite` e `br.com.lista.database.TabelaConvidado` (não existem no projeto).

# Inconsistências e problemas

- **[Esquema vs Código]**
  - `schema.sql` define `convidados(id, nome, quantidade_acompanhantes, confirmado)`.
  - O serviço e o modelo usam `tamanho_fralda` em vez de `quantidade_acompanhantes/confirmado`.
  - Efeito: SQL do serviço falha se a tabela for criada pelo `schema.sql` atual.

- **[Dependências internas ausentes]**
  - `ConexaoSQLite` e `TabelaConvidado` não existem. Sem elas, app e testes de serviço não conectam/criam tabela.

- **[Stack inconsistente]**
  - `pom.xml` traz Spring Data JPA, mas o código usa JDBC manual; não há entidades JPA nem datasource configurado.

# Cenários de teste propostos

- **[Serviço `ListaConvidados`]**
  - **Adicionar com sucesso**: inserir `Convidado(nome, tamanhoFralda)` e verificar via `listarConvidados()`.
  - **Adicionar inválido**: `nome` vazio. Definir comportamento esperado (falha/aceita) e testar conforme.
  - **Listar vazia**: base sem registros retorna lista vazia.
  - **Remover existente por nome**: inserir, remover por `nome` e validar ausência na listagem.
  - **Remover inexistente**: remover `nome` que não existe; validar que não lança exceção e não altera estado.
  - **Resumo**: inserir múltiplos tamanhos e validar contagem de `mostrarResumo()` (idealmente retornando dados; se não, capturar stdout no teste).

- **[Console `MeuConsole` (opcional)]**
  - Simular fluxo adicionar→listar→atualizar→remover. Observação: console fala em "confirmado" e "quantidade", que não existem no modelo atual. Requer alinhar console antes de testar.

- **[Controller]**
  - Já há teste de “alive”. Opcional: expor endpoints reais (listar/insert/delete) e cobrir com `@WebMvcTest`.

# SQL proposto (alinhado ao código atual)

- **`schema.sql` (proposta)**

```sql
CREATE TABLE IF NOT EXISTS convidados (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nome TEXT NOT NULL,
  tamanho_fralda TEXT NOT NULL
);
```

- **`seed.sql` (proposta)**

```sql
INSERT INTO convidados (nome, tamanho_fralda) VALUES
  ('Alice', 'M'),
  ('Bruno', 'G'),
  ('Carla', 'P');
```

- **Seeds adicionais para cenários**

```sql
-- Para testar agrupamento
INSERT INTO convidados (nome, tamanho_fralda) VALUES
  ('Davi', 'M'),
  ('Eva', 'M'),
  ('Fábio', 'G');

-- Para testar remoção por nome repetido (definir regra)
INSERT INTO convidados (nome, tamanho_fralda) VALUES
  ('Alice', 'G');
```

# Implementações faltantes (necessárias para testes)

- **`br/com/lista/database/ConexaoSQLite`**
  - Métodos estáticos `conectar()` e `desconectar()` retornando/fechando `java.sql.Connection`.
  - Permitir configurar a URL (ex.: `jdbc:sqlite::memory:` para testes).

- **`br/com/lista/database/TabelaConvidado`**
  - Método `criarTabela()` que usa `ConexaoSQLite` e executa `resources/sql/schema.sql` na inicialização.

- **Ajuste do console**
  - `MeuConsole` hoje coleta `quantidade`/`confirmado`. Alinhar para `tamanhoFralda` para compatibilidade com o serviço.

- **Regra de remoção**
  - `DELETE WHERE nome = ?` remove múltiplos se houver nomes repetidos. Se não desejado, trocar operações para usar `id`.

# Esqueleto de testes (descrição)

- **Testes de integração do serviço (SQLite em memória)**
  - [setup] Abrir conexão `jdbc:sqlite::memory:`.
  - [setup] Executar `schema.sql`.
  - [seed opcional] Executar `seed.sql`.
  - [execução] Chamar métodos do serviço.
  - [asserções] Validar estado retornado.

- **Alternativa (mock)**
  - Mockar `ConexaoSQLite.conectar()` para devolver conexão em memória (requer classe existir).

# Plano de ação recomendado

- **[1] Corrigir SQL**: atualizar `schema.sql` e `seed.sql` para `tamanho_fralda`.
- **[2] Implementar infra SQLite**: criar `ConexaoSQLite` e `TabelaConvidado`.
- **[3] Ajustar `MeuConsole`**: coletar e persistir `tamanhoFralda`.
- **[4] Criar testes de serviço**: cobrir CRUD e resumo.
- **[5] Evoluir controller (opcional)**: expor endpoints reais e testar.

# Observação

Se preferir manter `quantidade_acompanhantes` e `confirmado` como no `schema.sql` atual, será necessário ajustar o modelo `Convidado` e o serviço `ListaConvidados` para esses campos, além do console. Alinhar ambos (código e SQL) é essencial para os testes passarem.
