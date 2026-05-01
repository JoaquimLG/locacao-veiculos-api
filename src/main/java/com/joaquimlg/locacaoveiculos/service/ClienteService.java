package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.dto.ClienteCreateDto;
import com.joaquimlg.locacaoveiculos.entity.Cliente;
import com.joaquimlg.locacaoveiculos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(ClienteCreateDto cliente) {
        Cliente clienteNovo = Cliente.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getEmail())
                .email(cliente.getEmail())
                .build();

        return clienteRepository.save(clienteNovo);
    }
}
