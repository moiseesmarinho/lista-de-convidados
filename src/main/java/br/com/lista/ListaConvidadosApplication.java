package br.com.lista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.lista.database.TabelaConvidado;

@SpringBootApplication
public class ListaConvidadosApplication {
    public static void main(String[] args) {
        TabelaConvidado.criarTabela();
        SpringApplication.run(ListaConvidadosApplication.class, args);
    }
}
