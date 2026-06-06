package com.joaquimlg.locacaoveiculos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "locacoes")

public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Carro carro;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valorTotal;
    private boolean ativa;
}
