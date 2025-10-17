package br.com.lista.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class TabelaConvidado {

    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS convidados (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "tamanho_fralda TEXT NOT NULL, " +
                "confirmado BOOLEAN DEFAULT 0" +
                ")";

        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            try {
                stmt.execute("ALTER TABLE convidados ADD COLUMN confirmado BOOLEAN DEFAULT 0");
            } catch (SQLException e) {
                String msg = e.getMessage();
                if (msg == null || (!msg.contains("duplicate column name") && !msg.contains("already exists"))) {
                    throw e;
                }
            }
            System.out.println("Tabela 'convidados' verificada/criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        } finally {
            ConexaoSQLite.desconectar();
        }
    }
}





