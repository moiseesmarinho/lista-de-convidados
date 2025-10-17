package br.com.lista;

import br.com.lista.database.ConexaoSQLite;

public class TestaConexao {
    public static void main(String[] args) {
        if (ConexaoSQLite.conectar() != null) {
            System.out.println("Conexão com SQLite estabelecida.");
            ConexaoSQLite.desconectar();
            System.out.println("Conexão encerrada.");
        } else {
            System.out.println("Falha ao conectar ao banco de dados.");
        }
    }
}
