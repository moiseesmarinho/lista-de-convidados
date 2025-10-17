<h1 align="center">🎉 Projeto Lista de Convidados</h1>

<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="80" alt="Java Logo" />
</p>

<p align="center">
  <b>Aplicação Java para gerenciamento de convidados de um chá revelação</b><br>
  Com armazenamento em banco de dados SQLite e evolução planejada para Spring Boot + página web.
</p>

---

### 🧾 Sumário

- [🚀 Funcionalidades](#-funcionalidades)
- [🌳 Estrutura do Projeto](#-estrutura-do-projeto)
- [🧩 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [🧠 Conceitos Praticados](#-conceitos-praticados)
- [💾 Banco de Dados SQLite](#-banco-de-dados-sqlite)
- [🌱 Próximas Etapas](#-próximas-etapas)
- [👨‍💻 Autor](#-autor)

---

## 🚀 Funcionalidades

✅ Adicionar novos convidados  
✅ Listar todos os convidados  
✅ Atualizar confirmação de presença  
✅ Mostrar resumo por tamanho de fralda  
✅ Remover convidados  
✅ Armazenamento em banco de dados **SQLite**  
✅ Testes automatizados com **JUnit**

---

## 🌳 Estrutura do Projeto
```
lista-convidados/
│
├── src/
│ └── main/
│ ├── java/br/com/lista/
│ │ ├── model/ → Classe Convidado
│ │ ├── service/ → Regras de negócio (ListaConvidados)
│ │ ├── database/ → Conexão e Tabela SQLite
│ │ ├── controller/ → Controladores de teste (futuras rotas REST)
│ │ └── MeuConsole.java → Interface de console (menu principal)
│ │
│ └── resources/
│ ├── database/sql/ → Scripts schema.sql e seed.sql
│ └── application.properties → Configuração do banco SQLite
│
├── target/ → Saída gerada pelo Maven (build)
├── pom.xml → Configuração de dependências do Maven
├── README.md → Documentação do projeto
└── .gitignore → Arquivo de exclusões do Git
```


---

## 🧩 Tecnologias Utilizadas

<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Windsurf%20IDE-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"/>
</p>

---

## 🧠 Conceitos Praticados

- Estrutura de pacotes Java (`model`, `service`, `database`, `controller`)  
- Estruturas de repetição (`while`, `switch`)  
- Estruturas de dados (`List`, `Optional`)  
- Boas práticas de entrada e validação de dados (`Scanner`, `.trim()`, normalização)  
- Encapsulamento e organização em pacotes (`model`, `service`)  
- Tratamento de exceções (`try / catch`)  
- Conexão **JDBC** com banco de dados **SQLite**  
- Criação e manipulação de tabelas SQL  
- Testes automatizados com **JUnit**  
- Uso de **Maven** para dependências e build  
- Versionamento e portfólio com **Git e GitHub**

---

## 💾 Banco de Dados SQLite

O projeto utiliza o **SQLite** como banco de dados local leve e embutido.  
Scripts disponíveis em `src/main/resources/database/sql`:

- `schema.sql` → Criação da tabela de convidados  
- `seed.sql` → Dados de exemplo (opcional)  
- `application.properties` → Configuração da conexão

---

## 🌱 Próximas Etapas

🚧 **Migração para Spring Boot**  
- Criar API REST com endpoints para CRUD de convidados  
- Implementar camadas Controller, Service e Repository  
- Retornar dados em formato JSON  

🌐 **Criação da interface web**  
- Desenvolver página em **HTML + CSS + JavaScript**  
- Integrar com a API Spring Boot  

🧮 **Melhorias futuras**  
- Adicionar filtros e busca de convidados  
- Exportar lista em CSV  
- Implementar testes de integração

---

## 👨‍💻 Autor

**Moisés Marinho**  
📚 Cursando Análise e Desenvolvimento de Sistemas  
💻 Técnico em Back-end Java (SENAI)  

<p align="left">
  <a href="https://github.com/moiseesmarinho">
    <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/>
  </a>
  <a href="https://www.linkedin.com/in/moiseesmarinho/">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white"/>
  </a>
</p>

---

<p align="center">⭐ Projeto em constante evolução — em breve com Spring Boot e página web!</p>


