package com.zupacademy.contadigital.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ContaExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ContaExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(ResponseStatusException ex) {

        logger.warn("Mensagem: "+ex.getMessage()+"\nCausa: "+ ex.getReason());
        return ResponseEntity.status(ex.getStatus())
                .body(new StandardError(LocalDateTime.now(), ex.getStatus().value(), ex.getStatus().toString(), ex.getReason()));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<StandardError> handleRegraNegocioException(RegraNegocioException ex) {
        logger.warn("Mensagem: "+ex.getMensagem()+"\nCausa: "+ ex.getCampo()+"\nValor: "+ ex.getValue());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.toString(), ex.getMensagem()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleException(Exception ex) {
        logger.error("Mensagem: "+ex.getMessage()+"\nCausa:"+ ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()));
    }
}
