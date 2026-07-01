package com.joaquimlg.locacaoveiculos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LocacaoCreateDto {

    private Long clienteId;
    private Long carroId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
