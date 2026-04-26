package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import com.joaquimlg.locacaoveiculos.database.repository.CarroRepository;
import com.joaquimlg.locacaoveiculos.dto.CarroCreateDto;
import com.joaquimlg.locacaoveiculos.dto.CarroUpdateDto;
import com.joaquimlg.locacaoveiculos.exception.PlacaDuplicadaException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.joaquimlg.locacaoveiculos.database.model.StatusCarro.DISPONIVEL;

@Service
public class CarroService {
    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> listarCarros(StatusCarro status) {
        //Verifica status para definir o filtro da lista
        if (status != null) {
            return carroRepository.findByStatus(status);
        }

        return carroRepository.findByStatusNot(StatusCarro.INATIVO);
    }

    public Carro buscarCarroPlaca(String placa) {
        Optional<Carro> carroBuscadoPlaca = carroRepository.findByPlaca(placa);

        if (carroBuscadoPlaca.isPresent()) {
            return carroBuscadoPlaca.get();
        }

        throw new RuntimeException("Veículo não encontrado");
    }

    public Carro cadastrarCarro(CarroCreateDto carro) {

        boolean placaExistente = existePlacaCadastrada(carro.getPlaca());

        if (!placaExistente) {
            Carro carroNovo = Carro.builder()
                    .placa(carro.getPlaca())
                    .marca(carro.getMarca())
                    .modelo(carro.getModelo())
                    .status(DISPONIVEL) //Status do carro é inicializado como disponível
                    .valorCarro(carro.getValorCarro())
                    .build();

            return carroRepository.save(carroNovo);
        }

        throw new PlacaDuplicadaException("Placa já cadastrada");
    }

    public Carro atualizarParcialCarro(Long id, CarroUpdateDto carroAtualizacoes) {
        Optional<Carro> carroBuscadoId = carroRepository.findById(id);

        if (carroBuscadoId.isPresent()) {
            Carro carroAtualizado = carroBuscadoId.get();

            if (carroAtualizacoes.getStatus() != null) {
                carroAtualizado.setStatus(carroAtualizacoes.getStatus());
            }

            if (carroAtualizacoes.getValorCarro() != null) {
                carroAtualizado.setValorCarro(carroAtualizacoes.getValorCarro());
            }

            //Salva as novas alterações
            carroRepository.save(carroAtualizado);

            return carroAtualizado;
        }

        throw new RuntimeException("Veículo não encontrado");
    }

    public Carro inativarCarro(Long id) {
        Optional<Carro> carroBuscado = carroRepository.findById(id);

        if (carroBuscado.isPresent()) {
            Carro carroRemovido = carroBuscado.get();

            if (carroRemovido.getStatus() != StatusCarro.ALUGADO) {
                carroRemovido.setStatus(StatusCarro.INATIVO);

                //Salva as novas alterações
                carroRepository.save(carroRemovido);

                return carroRemovido;
            }

            throw new RuntimeException("Não é possível remover carro alugado!");
        }

        throw new RuntimeException("Veículo não encontrado");
    }

    private boolean existePlacaCadastrada(String placa) {
        return carroRepository.existsByPlaca(placa);
    }
}
