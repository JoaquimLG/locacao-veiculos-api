package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.dto.ClienteCreateDto;
import com.joaquimlg.locacaoveiculos.dto.ClienteSearchDto;
import com.joaquimlg.locacaoveiculos.entity.Cliente;
import com.joaquimlg.locacaoveiculos.exception.CpfDuplicadoException;
import com.joaquimlg.locacaoveiculos.exception.NaoEncontradoException;
import com.joaquimlg.locacaoveiculos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClienteCpf(ClienteSearchDto clienteBusca) {
        Optional<Cliente> clienteBuscado = clienteRepository.findByCpf(clienteBusca.getCpf());

        if (clienteBuscado.isPresent()) {
            return clienteBuscado.get();
        }

        throw new NaoEncontradoException("Cliente não encontrado");
    }

    public Cliente cadastrarCliente(ClienteCreateDto cliente) {
        boolean existeCpf = existeCpfCadastrado(cliente.getCpf());

        if (!existeCpf) {
            Cliente clienteNovo = Cliente.builder()
                    .nome(cliente.getNome())
                    .cpf(cliente.getCpf())
                    .email(cliente.getEmail())
                    .build();

            return clienteRepository.save(clienteNovo);
        }

        throw new CpfDuplicadoException("Cpf já está cadastrado");
    }

    private boolean existeCpfCadastrado(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }
}
