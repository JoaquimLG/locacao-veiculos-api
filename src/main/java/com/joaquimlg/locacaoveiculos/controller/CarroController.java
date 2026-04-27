package com.joaquimlg.locacaoveiculos.controller;

import com.joaquimlg.locacaoveiculos.entity.Carro;
import com.joaquimlg.locacaoveiculos.entity.StatusCarro;
import com.joaquimlg.locacaoveiculos.dto.CarroCreateDto;
import com.joaquimlg.locacaoveiculos.dto.CarroUpdateDto;
import com.joaquimlg.locacaoveiculos.service.CarroService;
import jakarta.validation.Valid;
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
    public List<Carro> listarCarros(
            @RequestParam (required = false) StatusCarro status) {
        return carroService.listarCarros(status);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Carro> buscarCarroPlaca(@PathVariable String placa) {
        Carro carroPlaca = carroService.buscarCarroPlaca(placa);

        return new ResponseEntity<>(carroPlaca, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Carro> cadastrarCarro (@RequestBody @Valid CarroCreateDto carro) {
        Carro carroCriado = carroService.cadastrarCarro(carro);

        return new ResponseEntity<>(carroCriado, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carro> atualizarParcialCarro (@PathVariable Long id, @RequestBody @Valid CarroUpdateDto carro) {
        Carro carroAtualizado = carroService.atualizarParcialCarro(id, carro);

        return new ResponseEntity<>(carroAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carro> inativarCarro (@PathVariable Long id) {
        Carro carroRemovido = carroService.inativarCarro(id);

        return new ResponseEntity<>(carroRemovido, HttpStatus.OK);
    }
}
