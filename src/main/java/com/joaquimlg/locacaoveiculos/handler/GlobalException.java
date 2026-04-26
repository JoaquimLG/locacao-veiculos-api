package com.joaquimlg.locacaoveiculos.handler;

import com.joaquimlg.locacaoveiculos.exception.ErrorResponse;
import com.joaquimlg.locacaoveiculos.exception.NaoEncontradoException;
import com.joaquimlg.locacaoveiculos.exception.PlacaDuplicadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(PlacaDuplicadaException.class)
    public ResponseEntity<ErrorResponse> handlerPlacaDuplicadaException(PlacaDuplicadaException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerNaoEncontradoException(NaoEncontradoException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
