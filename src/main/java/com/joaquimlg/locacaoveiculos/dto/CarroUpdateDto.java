package com.joaquimlg.locacaoveiculos.dto;

import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarroUpdateDto {
    private StatusCarro status;
    @Positive
    private Double valorCarro;
}
