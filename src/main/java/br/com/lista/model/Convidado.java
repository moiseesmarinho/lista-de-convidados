package br.com.lista.model;

public class Convidado {
    private int id;
    private String nome;
    private String tamanhoFralda;
    private boolean confirmado;

    public Convidado(String nome, String tamanhoFralda) {
        this.nome = nome;
        this.tamanhoFralda = tamanhoFralda;
        this.confirmado = false;
    }

    public Convidado(int id, String nome, String tamanhoFralda, boolean confirmado) {
        this.id = id;
        this.nome = nome;
        this.tamanhoFralda = tamanhoFralda;
        this.confirmado = confirmado;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTamanhoFralda() {
        return tamanhoFralda;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTamanhoFralda(String tamanhoFralda) {
        this.tamanhoFralda = tamanhoFralda;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    @Override
    public String toString() {
        String status = confirmado ? "Confirmado" : "Não confirmado";
        return nome + " (" + tamanhoFralda + ") - " + status;
    }
}


