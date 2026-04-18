package com.joaquimlg.locacaoveiculos.controller;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import com.joaquimlg.locacaoveiculos.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public List<Carro> listarCarros() {
        return carroService.listarCarros();
    }

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<Carro> cadastrarCarro (@RequestBody Carro carro) {
        Carro carroCriado = carroService.cadastrarCarro(carro);
        return new ResponseEntity<>(carroCriado, HttpStatus.CREATED);
    }
}
