package com.zupacademy.contadigital.contas.debita;

import com.zupacademy.contadigital.contas.Conta;
import com.zupacademy.contadigital.contas.ContaRepository;
import com.zupacademy.contadigital.contas.credita.RequestOperacao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/contas")
public class ControllerDebita {

    private final ContaRepository contaRepository;

    public ControllerDebita(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PutMapping("/{id}/debita")
    public void saque(@RequestBody RequestOperacao requestOperacao, @PathVariable Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe conta com essa identificação.");
        });

        conta.saca(requestOperacao.getValor());
    }
}
