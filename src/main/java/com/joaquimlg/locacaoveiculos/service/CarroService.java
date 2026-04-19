package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import com.joaquimlg.locacaoveiculos.database.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Carro atualizarParcialCarro(Long id, Carro carroAtualizacoes) {
        Optional<Carro> carroBuscadoId = carroRepository.findById(id);

        if (carroBuscadoId.isPresent()) {
            Carro carroAtualizado = carroBuscadoId.get();

            if (carroAtualizacoes.getStatus() != null) {
                carroAtualizado.setStatus(carroAtualizacoes.getStatus());
            }

            if (carroAtualizacoes.getValorCarro() != 0.0) {
                carroAtualizado.setValorCarro(carroAtualizacoes.getValorCarro());
            }

            carroRepository.save(carroAtualizado);

            return carroAtualizado;
        }

        else {
            throw new RuntimeException("Veículo não encontrado");
        }
    }

    public Carro inativarCarro(Long id) {
        Optional<Carro> carroBuscado = carroRepository.findById(id);

        if (carroBuscado.isPresent()) {
            Carro carroRemovido = carroBuscado.get();

            carroRemovido.setStatus(StatusCarro.INATIVO);

            carroRepository.save(carroRemovido);

            return carroRemovido;
        }
        else {
            throw new RuntimeException("Veículo não encontrado");
        }
    }
}
