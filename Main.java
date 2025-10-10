import model.Convidado;
import Service.ListaConvidados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        ListaConvidados lista = new ListaConvidados();
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("===Menu===");
            System.out.println("1 - Adicionar convidado");
            System.out.println("2 - Listar Todos");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - Atualizar confirmação");
            System.out.println("5 - Remover convidado");
            System.out.println("6 - Mostrar resumo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
        String entrada = "";
        do {
            System.out.print("Escolha uma opção: ");
            entrada = scanner.nextLine().trim();
        } while (entrada.isEmpty());

        try {
            opcao = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números!");
            continue;
        }
            
    switch (opcao) {
    case 1: {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tamanho da fralda (P/M/G/XG): ");
        String fralda = scanner.nextLine();
        boolean adicionado = lista.adicionar(new Convidado(nome, false, fralda));
        System.out.println(adicionado ? "Convidado adicionado!" : "Erro ao adicionar (nome vazio ou duplicado).");
        break;
    }
    case 2: {
        System.out.println("\n=== Todos os convidados ===");
        List<Convidado> todos = lista.listarTodos();
        if (todos.isEmpty()) System.out.println("Nenhum convidado na lista.");
        else todos.forEach(System.out::println);
        break;
    }
    case 3: {
        System.out.println("Nome para buscar: ");
        String nome = scanner.nextLine();
        Optional<Convidado> c = lista.buscarPorNome(nome);
        System.out.println(c.isPresent() ? c.get() : "Convidado não encontrado.");
        break;
    }
    case 4: {
        System.out.println("Nome para atualizar confirmação: ");
        String nome = scanner.nextLine();
        System.out.println("Confirmado? (sim/não): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        boolean confirmado = resposta.equals("sim") || resposta.equals("s");
        boolean ok = lista.atualizarConfirmacao(nome, confirmado);
        System.out.println(ok ? "Confirmação atualizada!" : "Convidado não encontrado.");
        break;
    }
    case 5: {
        System.out.println("Nome para remover: ");
        String nome = scanner.nextLine();
        boolean ok = lista.removerPorNome(nome);
        System.out.println(ok ? "Convidado removido!" : "Convidado não encontrado.");
        break;
    }

    case 6: {
        System.out.println("=== Resumo da lista ===");
        System.out.println("Total de convidados: " + lista.totalConvidados());
        System.out.println("Confirmados: "+ lista.totalConfirmados());
        System.out.println("Pendentes: " + lista.totalPendentes());
        break;
    }

    default: {
        if (opcao != 0)
            System.out.println("Opção inválida. Tente novamente!");
        break;
    }
    } 
        }

    scanner.close();
    System.out.println("Programa encerrado.");
    
}
}
