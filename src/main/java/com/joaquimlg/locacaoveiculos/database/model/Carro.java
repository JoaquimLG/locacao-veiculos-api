package com.joaquimlg.locacaoveiculos.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carros")

public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String placa;
    private String modelo;
    private String marca;
    private double valorCarro;
    private StatusCarro status;

    public Carro (String placa, String modelo, String marca, double valorCarro) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.valorCarro = valorCarro;
        this.status = StatusCarro.DISPONIVEL;
    }
}
