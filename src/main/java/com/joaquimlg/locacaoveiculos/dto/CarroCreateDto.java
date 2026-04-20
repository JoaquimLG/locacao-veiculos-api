package com.joaquimlg.locacaoveiculos.dto;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarroCreateDto {
    @NotBlank
    private String placa;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @Positive
    private double valorCarro;

}


