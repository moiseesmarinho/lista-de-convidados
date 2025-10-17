package br.com.lista.service;

import br.com.lista.database.ConexaoSQLite;
import br.com.lista.model.Convidado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaConvidados {

    public void adicionarConvidado(Convidado convidado) {
        String sql = "INSERT INTO convidados (nome, tamanho_fralda, confirmado) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, convidado.getNome());
            stmt.setString(2, convidado.getTamanhoFralda());
            stmt.setBoolean(3, false);
            stmt.executeUpdate();
            System.out.println("Convidado adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar convidado: " + e.getMessage());
        }
    }

    public List<Convidado> listarConvidados() {
        List<Convidado> lista = new ArrayList<>();
        String sql = "SELECT id, nome, tamanho_fralda, confirmado FROM convidados";
        try (Connection conn = ConexaoSQLite.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String tamanho = rs.getString("tamanho_fralda");
            boolean confirmado = rs.getBoolean("confirmado");
            Convidado c = new Convidado(id, nome, tamanho, confirmado);
            lista.add(c);
        }
    }   catch (SQLException e) {
            System.out.println("Erro ao listar convidados: " + e.getMessage());
    }
        return lista;
}


    public void mostrarResumo() {
        String sql = "SELECT tamanho_fralda, COUNT(*) AS total FROM convidados GROUP BY tamanho_fralda";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nResumo de tamanhos de fralda:");
            while (rs.next()) {
                String tamanho = rs.getString("tamanho_fralda");
                int total = rs.getInt("total");
                System.out.println(tamanho + ": " + total);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao mostrar resumo: " + e.getMessage());
        }
    }

    public void mostrarResumoFraldasConfirmados() {
        String sql = "SELECT tamanho_fralda, COUNT(*) AS total FROM convidados WHERE confirmado = 1 GROUP BY tamanho_fralda";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nResumo de tamanhos de fralda (apenas confirmados):");
            while (rs.next()) {
                String tamanho = rs.getString("tamanho_fralda");
                int total = rs.getInt("total");
                System.out.println(tamanho + ": " + total);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao mostrar resumo (confirmados): " + e.getMessage());
        }
    }

    public void mostrarResumoConfirmacao() {
        String sql = "SELECT confirmado, COUNT(*) AS total FROM convidados GROUP BY confirmado";
        int confirmados = 0;
        int naoConfirmados = 0;
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                boolean conf = rs.getBoolean("confirmado");
                int total = rs.getInt("total");
                if (conf) confirmados = total; else naoConfirmados = total;
            }
            System.out.println("\nResumo de confirmações:");
            System.out.println("Confirmados: " + confirmados);
            System.out.println("Não confirmados: " + naoConfirmados);
        } catch (SQLException e) {
            System.out.println("Erro ao mostrar resumo de confirmações: " + e.getMessage());
        }
    }

    public void removerConvidado(String nome) {
        String sql = "DELETE FROM convidados WHERE nome = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Convidado removido com sucesso!");
            } else {
                System.out.println("Convidado não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover convidado: " + e.getMessage());
        }
    }

    public void atualizarConfirmacao(String nome, boolean confirmado) {
        String sql = "UPDATE convidados SET confirmado = ? WHERE nome = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, confirmado);
            stmt.setString(2, nome);
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Confirmação atualizada com sucesso!");
            } else {
                System.out.println("Convidado não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar confirmação: " + e.getMessage());
        }
    }
}






