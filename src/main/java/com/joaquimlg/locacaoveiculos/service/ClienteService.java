package com.joaquimlg.locacaoveiculos.service;

import com.joaquimlg.locacaoveiculos.dto.ClienteCreateDto;
import com.joaquimlg.locacaoveiculos.dto.ClienteSearchDto;
import com.joaquimlg.locacaoveiculos.dto.ClienteUpdateDto;
import com.joaquimlg.locacaoveiculos.entity.Cliente;
import com.joaquimlg.locacaoveiculos.exception.CampoDuplicadoException;
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
        //Verifica se dados já foram cadastrados

        boolean existeCpf = existeCpfCadastrado(cliente.getCpf());
        if (existeCpf) {
            throw new CampoDuplicadoException("Cpf já está cadastrado");
        }

        boolean existeEmail = existeEmailCadastrado(cliente.getEmail());
        if (existeEmail) {
            throw new CampoDuplicadoException("Email já está cadastrado");
        }

        Cliente clienteNovo = Cliente.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .build();

        return clienteRepository.save(clienteNovo);
    }

    public Cliente atualizarCliente(Long id, ClienteUpdateDto clienteAtualizacoes) {
        Optional<Cliente> clienteBuscadoId = clienteRepository.findById(id);

        if (clienteBuscadoId.isPresent()) {
            Cliente clienteAtualizado = clienteBuscadoId.get();

            if (clienteAtualizacoes.getNome() != null) {
                clienteAtualizado.setNome(clienteAtualizacoes.getNome());
            }

            if (clienteAtualizacoes.getEmail() != null) {
                clienteAtualizado.setEmail(clienteAtualizacoes.getEmail());
            }

            clienteRepository.save(clienteAtualizado);

            return clienteAtualizado;
        }

        throw new NaoEncontradoException("Cliente não encontrado");
    }

    private boolean existeCpfCadastrado(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    private boolean existeEmailCadastrado(String email) {
        return clienteRepository.existsByEmail(email);
    }
}
