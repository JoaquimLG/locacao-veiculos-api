package com.joaquimlg.locacaoveiculos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteCreateDto {
    private String nome;
    private String cpf;
    private String email;
}
