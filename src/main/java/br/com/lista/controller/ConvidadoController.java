package br.com.lista.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ConvidadoController {

    @GetMapping("/convidados")
    public Map<String, String> listarConvidados() {
        return Map.of(
            "mensagem", "API de Lista de Convidados está funcionando!"
        );
    }
}
