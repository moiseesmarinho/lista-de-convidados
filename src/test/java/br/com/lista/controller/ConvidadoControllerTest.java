package br.com.lista.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ConvidadoController.class)
class ConvidadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarConvidadosDeveResponderMensagem() throws Exception {
        mockMvc.perform(get("/convidados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensagem").value("API de Lista de Convidados está funcionando!"));
    }
}
