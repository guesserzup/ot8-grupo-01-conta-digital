package com.zupacademy.contadigital.exception;

import java.time.LocalDateTime;

public class ErrorsFields extends StandardError {
    private String campo;
    private String erro;

    public ErrorsFields(LocalDateTime timeError, Integer statusCode, String status, String details, String campo, String erro) {
        super(timeError, statusCode, status, details);
        this.campo = campo;
        this.erro = erro;
    }
}
