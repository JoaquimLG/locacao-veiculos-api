package com.joaquimlg.locacaoveiculos.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String mensagem;
    private HttpStatus status;
}
