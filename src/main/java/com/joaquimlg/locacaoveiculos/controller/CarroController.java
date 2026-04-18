package com.joaquimlg.locacaoveiculos.controller;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import com.joaquimlg.locacaoveiculos.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Carro> buscarCarroPlaca(@PathVariable String placa) {
        Carro carroPlaca = carroService.buscarCarroPlaca(placa);

        return new ResponseEntity<>(carroPlaca, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Carro> cadastrarCarro (@RequestBody Carro carro) {
        Carro carroCriado = carroService.cadastrarCarro(carro);

        return new ResponseEntity<>(carroCriado, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carro> atualizarParcialCarro (@PathVariable Long id, @RequestBody Carro carro) {
        Carro carroAtualizado = carroService.atualizarParcialCarro(id, carro);

        return new ResponseEntity<>(carroAtualizado, HttpStatus.OK);
    }
}
