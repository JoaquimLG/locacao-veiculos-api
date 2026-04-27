package com.joaquimlg.locacaoveiculos.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private String mensagem;
    private HttpStatus status;
}
