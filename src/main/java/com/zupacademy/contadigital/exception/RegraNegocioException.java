package com.zupacademy.contadigital.exception;

public class RegraNegocioException extends RuntimeException{
    private String mensagem;
    private String campo;
    private String value;

    public RegraNegocioException(String mensagem, String campo, String value) {
        this.mensagem = mensagem;
        this.campo = campo;
        this.value = value;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getValue() {
        return value;
    }
}
