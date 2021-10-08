package com.zupacademy.contadigital.contas.credita;

import com.zupacademy.contadigital.contas.Conta;

import java.math.BigDecimal;

public class ResponseOperacao {


    private Long Id;
    private BigDecimal saldo;


    public ResponseOperacao(Conta conta) {
        this.Id = conta.getId();
        this.saldo = conta.getSaldo();
    }

    public Long getId() {
        return Id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
