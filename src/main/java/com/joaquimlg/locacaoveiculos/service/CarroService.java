package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import com.joaquimlg.locacaoveiculos.database.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    public Carro buscarCarroPlaca(String placa) {
        return carroRepository.findByPlaca(placa);
    }

    public Carro cadastrarCarro(Carro carro) {
        carro.setStatus(StatusCarro.DISPONIVEL);
        return carroRepository.save(carro);
    }
}
