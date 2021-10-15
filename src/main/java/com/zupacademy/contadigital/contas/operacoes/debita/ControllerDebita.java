package com.zupacademy.contadigital.contas.operacoes.debita;

import com.zupacademy.contadigital.contas.Conta;
import com.zupacademy.contadigital.contas.ContaRepository;
import com.zupacademy.contadigital.contas.operacoes.RequestOperacao;
import com.zupacademy.contadigital.contas.operacoes.ResponseOperacao;
import com.zupacademy.contadigital.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/contas")
public class ControllerDebita {

    @Autowired
    private ContaRepository contaRepository;


    @PutMapping("/{id}/debita")
    @Transactional
    public ResponseOperacao saque(@RequestBody RequestOperacao requestOperacao, @PathVariable Long id) {

        Conta conta = contaRepository.findById(id).orElseThrow(() -> {
            throw new RegraNegocioException("Não existe conta com o id recebido", "conta", id.toString());
        });

        conta.saca(requestOperacao.getValor());
        contaRepository.save(conta);

        return new ResponseOperacao(conta);
    }
}
