package model;
import java.util.Locale;
import java.util.Objects;

public class Convidado {
    private String nome;
    private boolean confirmado;
    private String tamanhoFralda;

    public Convidado (String nome, boolean confirmado, String tamanhoFralda) {
        this.nome = nome != null ? nome.trim() : "";
        this.confirmado =  confirmado;
        this.tamanhoFralda = tamanhoFralda != null ? tamanhoFralda.trim().toUpperCase(Locale.ROOT) : "";
    }

    public String getNome() {return nome;}
    public void setNome (String nome) {this.nome = nome != null ? nome.trim() : "";}
    
    public boolean isConfirmado() {return confirmado;}
    public void setConfirmado(boolean confirmado) {this.confirmado = confirmado;}

    public String getTamanhoFralda() {return tamanhoFralda;}
    public void setTamanhoFralda (String tamanhoFralda) {
        this.tamanhoFralda = tamanhoFralda != null ? tamanhoFralda.trim().toUpperCase(Locale.ROOT) : "";
    }

    public String getStatus() {return confirmado ? "Confirmado" : "Não Confirmado";}

    @Override
    public String toString() {
        return String.format("%s - %s - Fralda: %s", nome, getStatus(), tamanhoFralda);
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Convidado)) return false;
        Convidado that = (Convidado) o;
        return nome.equalsIgnoreCase(that.nome);
    }

     @Override
    public int hashCode() {
        return Objects.requireNonNull(nome.toLowerCase(Locale.ROOT)).hashCode();
    }




}
