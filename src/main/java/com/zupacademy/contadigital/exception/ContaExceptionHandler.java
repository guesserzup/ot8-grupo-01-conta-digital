package com.zupacademy.contadigital.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ContaExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ContaExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(ResponseStatusException ex) {

        logger.warn("Mensagem: " + ex.getMessage() + "\nCausa: " + ex.getReason());
        return ResponseEntity.status(ex.getStatus())
                .body(new StandardError(LocalDateTime.now(), ex.getStatus().value(), ex.getStatus().toString(), ex.getReason()));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<StandardError> handleRegraNegocioException(RegraNegocioException ex) {
        logger.warn("Mensagem: " + ex.getMensagem() + "\nCausa: " + ex.getCampo() + "\nValor: " + ex.getValue());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.toString(), ex.getMensagem()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorsFields> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorsFields> listValidationErros = new ArrayList<>();

        List<FieldError> listErrors = ex.getBindingResult().getFieldErrors();

        listErrors.forEach(fieldError -> {
            final String messageSourceMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            final ErrorsFields errorsFields = new ErrorsFields(fieldError.getField(), messageSourceMessage);
            listValidationErros.add(errorsFields);
        });

        return listValidationErros;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleException(Exception ex) {
        logger.error("Mensagem: " + ex.getMessage() + "\nCausa:" + ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()));
    }
}
