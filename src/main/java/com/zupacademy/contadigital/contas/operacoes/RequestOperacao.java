package com.zupacademy.contadigital.contas.operacoes;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RequestOperacao {

    @Positive
    private BigDecimal valor;

    @Deprecated
    public RequestOperacao() {
    }

    public RequestOperacao(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
