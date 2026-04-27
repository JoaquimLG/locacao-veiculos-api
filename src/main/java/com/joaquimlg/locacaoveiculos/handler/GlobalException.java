package com.joaquimlg.locacaoveiculos.handler;

import com.joaquimlg.locacaoveiculos.dto.ErrorResponseDto;
import com.joaquimlg.locacaoveiculos.exception.NaoEncontradoException;
import com.joaquimlg.locacaoveiculos.exception.OperacaoNaoPermitidaException;
import com.joaquimlg.locacaoveiculos.exception.PlacaDuplicadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(PlacaDuplicadaException.class)
    public ResponseEntity<ErrorResponseDto> handlerPlacaDuplicadaException(PlacaDuplicadaException ex) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .mensagem(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDto> handlerNaoEncontradoException(NaoEncontradoException ex) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .mensagem(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<ErrorResponseDto> handlerOperacaoNaoPermitidaException(OperacaoNaoPermitidaException ex) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .mensagem(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
