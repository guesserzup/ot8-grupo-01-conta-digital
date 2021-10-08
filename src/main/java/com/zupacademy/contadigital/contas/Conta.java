package com.zupacademy.contadigital.contas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String numeroConta;
    @NotNull
    private Long idCliente;
    @PositiveOrZero
    private BigDecimal saldo;

    @Deprecated
    public Conta() {
    }

    public Conta(String numeroConta, Long idCliente, BigDecimal saldo) {
        this.numeroConta = numeroConta;
        this.idCliente = idCliente;
        this.saldo = saldo;
    }


}
