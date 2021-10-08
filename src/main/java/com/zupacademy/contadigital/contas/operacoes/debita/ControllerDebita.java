package com.zupacademy.contadigital.contas.operacoes.debita;

import com.zupacademy.contadigital.contas.Conta;
import com.zupacademy.contadigital.contas.ContaRepository;
import com.zupacademy.contadigital.contas.operacoes.RequestOperacao;
import com.zupacademy.contadigital.contas.operacoes.ResponseOperacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/contas")
public class ControllerDebita {

    @Autowired
    private ContaRepository contaRepository;


    @PutMapping("/{id}/debita")
    public ResponseOperacao saque(@RequestBody RequestOperacao requestOperacao, @PathVariable Long id) {

        Conta conta = contaRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe conta com essa identificação.");
        });

        conta.saca(requestOperacao.getValor());
        contaRepository.save(conta);

        return new ResponseOperacao(conta);
    }
}