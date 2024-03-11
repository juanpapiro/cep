package com.api.cep.controller.handler;

import com.api.cep.exception.CepNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ErrorHandlerController {


    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<Void> handleCepNotFound(CepNotFoundException e) {
        log.info(e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

}
