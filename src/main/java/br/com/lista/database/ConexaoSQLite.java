package br.com.lista.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

    private static Connection conexao;

    public static Connection conectar() {
        try {
            if (conexao == null || conexao.isClosed()) {
                String url = System.getProperty("db.url", "jdbc:sqlite:lista_convidados.db");
                conexao = DriverManager.getConnection(url);
                System.out.println("Conexão com SQLite estabelecida.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        return conexao;
    }

    public static void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                conexao = null;
                System.out.println("Conexão encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar conexão: " + e.getMessage());
        }
    }
}




