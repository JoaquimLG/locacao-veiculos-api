package com.joaquimlg.locacaoveiculos.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ClienteUpdateDto {
    private String nome;
    @Email
    private String email;
}
