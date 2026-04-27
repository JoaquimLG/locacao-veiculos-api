package com.joaquimlg.locacaoveiculos.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "carros")

public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String placa;
    private String marca;
    private String modelo;
    private StatusCarro status;
    private double valorCarro;
}
