package com.zupacademy.contadigital.contas;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Long getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void deposita(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
        }
    }

    public void saca(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) > 0
                && this.saldo.compareTo(BigDecimal.ZERO) > 0
                && this.saldo.compareTo(valor) > 0){
            final BigDecimal subtract = this.saldo.subtract(valor);
            this.saldo = subtract;
        }
    }
}