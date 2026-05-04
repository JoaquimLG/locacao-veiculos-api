package com.joaquimlg.locacaoveiculos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteSearchDto {
    @NotBlank
    private String cpf;
}
