package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.dto.LocacaoCreateDto;
import com.joaquimlg.locacaoveiculos.entity.Carro;
import com.joaquimlg.locacaoveiculos.entity.Cliente;
import com.joaquimlg.locacaoveiculos.entity.Locacao;
import com.joaquimlg.locacaoveiculos.entity.StatusCarro;
import com.joaquimlg.locacaoveiculos.exception.NaoEncontradoException;
import com.joaquimlg.locacaoveiculos.exception.OperacaoNaoPermitidaException;
import com.joaquimlg.locacaoveiculos.repository.CarroRepository;
import com.joaquimlg.locacaoveiculos.repository.ClienteRepository;
import com.joaquimlg.locacaoveiculos.repository.LocacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;

    public LocacaoService(LocacaoRepository locacaoRepository,
                          CarroRepository carroRepository,
                          ClienteRepository clienteRepository) {
        this.locacaoRepository = locacaoRepository;
        this.carroRepository = carroRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Locacao> listarLocacoes() {
        return locacaoRepository.findAll();
    }

    public Locacao buscarLocacaoPorId(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Locação não encontrada com o ID: " + id));
    }

    @Transactional
    public Locacao alugarVeiculo(LocacaoCreateDto dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));

        Carro carro = carroRepository.findById(dto.getCarroId())
                .orElseThrow(() -> new NaoEncontradoException("Veículo não encontrado"));

        // Validar se o veículo está disponível
        if (carro.getStatus() != StatusCarro.DISPONIVEL) {
            throw new OperacaoNaoPermitidaException("Veículo não está disponível");
        }

        // Validar datas de aluguel
        if (dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new NaoEncontradoException("Datas inválidas");
        }

        long quantidadeDias = ChronoUnit.DAYS.between(dto.getDataInicio(), dto.getDataFim());

        if (quantidadeDias <= 0) {
            quantidadeDias = 1; // Garante cobrança de pelo menos 1 dia
        }

        double valorTotal = quantidadeDias * carro.getValorCarro();

        carro.setStatus(StatusCarro.ALUGADO);

        Locacao locacao = Locacao.builder()
                .cliente(cliente)
                .carro(carro)
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .valorTotal(valorTotal)
                .ativa(true)
                .build();

        return locacaoRepository.save(locacao);
    }

    @Transactional
    public Locacao encerrarLocacao(Long id) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Locação não encontrada"));

        if (!locacao.isAtiva()) {
            throw new OperacaoNaoPermitidaException("Locação não está ativa");
        }

        locacao.setAtiva(false);

        Carro carro = locacao.getCarro();
        carro.setStatus(StatusCarro.DISPONIVEL);
        carroRepository.save(carro);

        return locacaoRepository.save(locacao);
    }
}
