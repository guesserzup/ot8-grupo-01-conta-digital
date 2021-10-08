package com.zupacademy.contadigital.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ContaExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new StandardError(LocalDateTime.now(), ex.getStatus().value(), ex.getStatus().toString(), ex.getReason()));
    }

}
