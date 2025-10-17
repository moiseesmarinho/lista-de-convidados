package br.com.lista;

import br.com.lista.model.Convidado;
import br.com.lista.service.ListaConvidados;
import br.com.lista.database.TabelaConvidado;
import java.text.Normalizer;
import java.util.Scanner;

public class MeuConsole {
    public static void main(String[] args) {
        TabelaConvidado.criarTabela();
        ListaConvidados lista = new ListaConvidados();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU LISTA DE CONVIDADOS =====");
            System.out.println("1 - Adicionar convidado");
            System.out.println("2 - Atualizar confirmação");
            System.out.println("3 - Listar convidados");
            System.out.println("4 - Mostrar resumo");
            System.out.println("5 - Remover convidado");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do convidado: ");
                    String nome = sc.nextLine();
                    System.out.print("Tamanho da fralda (P/M/G/XG): ");
                    String tamanho = sc.nextLine().trim().toUpperCase();
                    if (!tamanho.equals("P") && !tamanho.equals("M") && !tamanho.equals("G") && !tamanho.equals("XG")) {
                        System.out.println("Tamanho inválido! Use P, M, G ou XG.");
                        break;
                    }
                    Convidado novo = new Convidado(nome, tamanho);
                    lista.adicionarConvidado(novo);
                    break;

                case 2:
                    System.out.print("Nome do convidado para atualizar: ");
                    String nomeAtualizar = sc.nextLine();
                    System.out.print("O convidado confirmou presença? (Sim/Não): ");
                    String entrada = sc.nextLine().trim();
                    String normalizado = Normalizer.normalize(entrada, Normalizer.Form.NFD)
                            .replaceAll("\\p{M}+", "");
                    String up = normalizado.toUpperCase();

                    Boolean isConfirmado = null;
                    if (up.equals("S") || up.equals("SIM")) {
                        isConfirmado = true;
                    } else if (up.equals("N") || up.equals("NAO")) {
                        isConfirmado = false;
                    }

                    if (isConfirmado == null) {
                        System.out.println("Entrada inválida! Use S, N, Sim ou Não.");
                        break;
                    }
                    lista.atualizarConfirmacao(nomeAtualizar, isConfirmado);
                    break;

                case 3:
                    System.out.println("\nLista de convidados:");
                    lista.listarConvidados().forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("\nEscolha o tipo de resumo:");
                    System.out.println("1 - Por fralda (apenas confirmados)");
                    System.out.println("2 - Por confirmação (Confirmados/Não confirmados)");
                    System.out.print("Escolha: ");
                    String tipoResumo = sc.nextLine().trim();
                    if (tipoResumo.equals("1")) {
                        lista.mostrarResumoFraldasConfirmados();
                    } else if (tipoResumo.equals("2")) {
                        lista.mostrarResumoConfirmacao();
                    } else {
                        System.out.println("Opção de resumo inválida!");
                    }
                    break;

                case 5:
                    System.out.print("Nome do convidado para remover: ");
                    String nomeRemover = sc.nextLine();
                    lista.removerConvidado(nomeRemover);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}

