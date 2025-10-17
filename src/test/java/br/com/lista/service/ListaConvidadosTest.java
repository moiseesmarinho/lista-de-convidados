package br.com.lista.service;

import br.com.lista.database.ConexaoSQLite;
import br.com.lista.database.TabelaConvidado;
import br.com.lista.model.Convidado;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListaConvidadosTest {

    private ListaConvidados service;

    @BeforeEach
    void setUp() {
        // Banco baseado em arquivo em target/ para manter schema entre conexões
        System.setProperty("db.url", "jdbc:sqlite:target/test_lista_convidados.db");
        // Fecha conexão anterior (se houver) para reabrir com a URL atual
        ConexaoSQLite.desconectar();
        // Cria a tabela no banco em memória
        TabelaConvidado.criarTabela();
        service = new ListaConvidados();
        // Garante tabela limpa (idempotente)
        limparTabela();
    }

    @AfterEach
    void tearDown() {
        ConexaoSQLite.desconectar();
    }

    private void limparTabela() {
        try (Connection c = ConexaoSQLite.conectar(); Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM convidados");
        } catch (SQLException e) {
            fail("Falha ao limpar tabela: " + e.getMessage());
        } finally {
            ConexaoSQLite.desconectar();
        }
    }

    @Test
    void listarConvidados_deveRetornarVazio_quandoNaoHaRegistros() {
        List<Convidado> lista = service.listarConvidados();
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void adicionarConvidado_devePersistir_eListar() {
        Convidado novo = new Convidado("Alice", "M");
        service.adicionarConvidado(novo);

        List<Convidado> lista = service.listarConvidados();
        assertEquals(1, lista.size());
        Convidado salvo = lista.get(0);
        assertEquals("Alice", salvo.getNome());
        assertEquals("M", salvo.getTamanhoFralda());
        assertTrue(salvo.getId() > 0);
    }

    @Test
    void removerConvidado_porNome_deveRemoverRegistro() {
        service.adicionarConvidado(new Convidado("Bruno", "G"));
        service.adicionarConvidado(new Convidado("Carla", "P"));
        List<Convidado> antes = service.listarConvidados();
        assertEquals(2, antes.size());

        service.removerConvidado("Bruno");

        List<Convidado> depois = service.listarConvidados();
        assertEquals(1, depois.size());
        assertEquals("Carla", depois.get(0).getNome());
    }

    @Test
    void mostrarResumo_deveExibirContagemPorTamanho() {
        service.adicionarConvidado(new Convidado("Ana", "M"));
        service.adicionarConvidado(new Convidado("Bia", "M"));
        service.adicionarConvidado(new Convidado("Cris", "G"));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outContent));
            service.mostrarResumo();
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("Resumo de fraldas"));
        assertTrue(output.contains("M: 2"));
        assertTrue(output.contains("G: 1"));
    }
}
