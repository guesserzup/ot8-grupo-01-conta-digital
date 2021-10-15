package com.zupacademy.contadigital.contas.operacoes.credita;

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
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllerCreditaTest {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Test
    public void efetuaDepositoComSucesso() throws Exception{
        Conta conta = contaRepository.findById(1L).get();

        RequestOperacao requestOperacao = new RequestOperacao(new BigDecimal(2000));

        mockMvc.perform(put("/contas/" + conta.getId().toString() + "/credita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestOperacao)))
                .andExpect(status().isOk());
    }

    @Test
    public void naoEfetuaDepositoComValorNegativo() throws Exception{
        Conta conta = contaRepository.findById(1L).get();

        RequestOperacao requestOperacao = new RequestOperacao(new BigDecimal(-150));

        mockMvc.perform(put("/contas/" + conta.getId().toString() + "/credita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestOperacao)))
                .andExpect(status().isBadRequest());
    }






    @Test
    public void naoEfetuaDepositoEmContaInexistente() throws Exception{
        Conta conta = contaRepository.findById(1L).get();

        RequestOperacao requestOperacao = new RequestOperacao(new BigDecimal(-150));

        mockMvc.perform(put("/contas/" + ThreadLocalRandom.current().nextInt() + "/credita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestOperacao)))
                .andExpect(status().isNotFound());
    }


}