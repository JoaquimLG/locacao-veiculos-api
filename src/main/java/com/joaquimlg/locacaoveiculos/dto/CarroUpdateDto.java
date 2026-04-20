package com.joaquimlg.locacaoveiculos.dto;

import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarroUpdateDto {
    private StatusCarro status;
    private double valorCarro;
}
