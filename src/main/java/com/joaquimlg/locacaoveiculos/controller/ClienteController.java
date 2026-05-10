package com.joaquimlg.locacaoveiculos.controller;

import com.joaquimlg.locacaoveiculos.dto.ClienteCreateDto;
import com.joaquimlg.locacaoveiculos.dto.ClienteSearchDto;
import com.joaquimlg.locacaoveiculos.dto.ClienteUpdateDto;
import com.joaquimlg.locacaoveiculos.entity.Cliente;
import com.joaquimlg.locacaoveiculos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid ClienteCreateDto cliente) {
        Cliente clienteCriado = clienteService.cadastrarCliente(cliente);

        return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    }

    @PostMapping("/busca")
    public ResponseEntity<Cliente> buscarClienteCpf(@RequestBody ClienteSearchDto clienteBusca) {
        Cliente clienteBuscado = clienteService.buscarClienteCpf(clienteBusca);

        return new ResponseEntity<>(clienteBuscado, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizarParcialCliente(@PathVariable Long id, @RequestBody ClienteUpdateDto cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);

        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }
}
