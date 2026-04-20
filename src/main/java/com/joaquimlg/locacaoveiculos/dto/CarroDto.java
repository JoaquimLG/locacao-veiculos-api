package com.joaquimlg.locacaoveiculos.dto;

import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarroDto {
    private String placa;
    private String marca;
    private String modelo;
    private StatusCarro status;
    private double valorCarro;

}


