package com.zupacademy.contadigital.contas.operacoes.debita;

import com.google.gson.Gson;
import com.zupacademy.contadigital.contas.Conta;
import com.zupacademy.contadigital.contas.ContaRepository;
import com.zupacademy.contadigital.contas.operacoes.RequestOperacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllerDebitaTest {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Test
    public void efetuaDebitoComSucesso() throws Exception {

        Conta conta = contaRepository.findById(1L).get();

        RequestOperacao requestOperacao = new RequestOperacao(new BigDecimal(150));

        mockMvc.perform(put("/contas/" + conta.getId().toString() + "/debita")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestOperacao)))
                .andExpect(status().isOk());
    }

    @Test
    public void naoEfetuaDebitoComValorMaiorQueSaldo() throws Exception {

        Conta conta = contaRepository.findById(1L).get();

        RequestOperacao requestOperacao = new RequestOperacao(new BigDecimal(1000));

        mockMvc.perform(put("/contas/" + conta.getId().toString() + "/debita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestOperacao)))
                .andExpect(status().isUnprocessableEntity());
    }

}