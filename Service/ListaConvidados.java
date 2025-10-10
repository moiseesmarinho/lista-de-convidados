package Service;

import model.Convidado;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListaConvidados {
    private final List<Convidado> convidados;

    public ListaConvidados() {
        this.convidados = new ArrayList<>();
    }

    public boolean adicionar (Convidado c) {
        if (c == null || c.getNome().isBlank()) return false;
        if (buscarPorNome(c.getNome()).isPresent()) return false;
        convidados.add(c);
        return true;
    }

    public Optional<Convidado> buscarPorNome(String nome){
        if (nome == null) return Optional.empty();
        return convidados.stream()
                .filter(c -> c.getNome().equals(nome.trim()))
                .findFirst();
    }

    public boolean atualizarConfirmacao(String nome, boolean confirmado) {
        Optional<Convidado> opc = buscarPorNome(nome);
        if (opc.isPresent()) {
            opc.get().setConfirmado(confirmado);
            return true;
        }
        return false;
    }

    public List<Convidado> listarTodos() {
        return new ArrayList<>(convidados);
    }

    public List<Convidado> listarConfirmados(){
        return convidados.stream().filter(Convidado::isConfirmado).collect(Collectors.toList());
    }

    public List<Convidado> listarNaoConfirmados(){
        return convidados.stream().filter(c -> !c.isConfirmado()).collect(Collectors.toList());
    }

    public boolean removerPorNome (String nome){
        if (nome == null) return false;
        return convidados.removeIf(c -> c.getNome().equalsIgnoreCase(nome.trim()));
    }

    public void imprimirResumoConsole(){
        System.out.println("Total de convidados: " + convidados.size());
        System.out.println("Confirmados: " + listarConfirmados().size());
        System.out.println("Não confirmados: " + listarNaoConfirmados().size());    
    }

    public int totalConvidados() {
    return convidados.size();
    }

    public int totalConfirmados() {
    return (int) convidados.stream().filter(Convidado::isConfirmado).count();
    }

    public int totalPendentes() {
    return totalConvidados() - totalConfirmados();
    
}
}

