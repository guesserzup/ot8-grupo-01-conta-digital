package com.zupacademy.contadigital.contas.operacoes.credita;

import com.zupacademy.contadigital.contas.Conta;
import com.zupacademy.contadigital.contas.ContaRepository;
import com.zupacademy.contadigital.contas.operacoes.RequestOperacao;
import com.zupacademy.contadigital.contas.operacoes.ResponseOperacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ControllerCredita {

    @Autowired
    private ContaRepository contaRepository;

    @PutMapping("/{id}/credita")
    public ResponseOperacao deposito(@PathVariable("id") Long id, @RequestBody @Valid RequestOperacao requestCredita) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe conta com essa identificação.");
        });

        conta.deposita(requestCredita.getValor());
        contaRepository.save(conta);

        return new ResponseOperacao(conta);
    }

}
