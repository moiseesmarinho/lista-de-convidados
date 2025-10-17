package br.com.lista;

import br.com.lista.model.Convidado;
import br.com.lista.service.ListaConvidados;

public class TesteCRUD {
    public static void main(String[] args) {
        ListaConvidados lista = new ListaConvidados();

        Convidado c1 = new Convidado("Moisés", "M");
        lista.adicionarConvidado(c1);

        System.out.println("\nAntes da remoção:");
        lista.listarConvidados();

        lista.removerConvidado("Moisés");

        System.out.println("\nDepois da remoção:");
        lista.listarConvidados();
    }
}

