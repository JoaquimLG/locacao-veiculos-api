package com.joaquimlg.locacaoveiculos.controller;

import com.joaquimlg.locacaoveiculos.dto.LocacaoCreateDto;
import com.joaquimlg.locacaoveiculos.entity.Locacao;
import com.joaquimlg.locacaoveiculos.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @GetMapping
    public List<Locacao> listarLocacoes() {
        return locacaoService.listarLocacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locacao> buscarLocacaoPorId(@PathVariable Long id) {
        Locacao locacaoBuscada = locacaoService.buscarLocacaoPorId(id);

        return new ResponseEntity<>(locacaoBuscada, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Locacao> alugarVeiculo(@RequestBody @Valid LocacaoCreateDto dto) {
        Locacao novaLocacao = locacaoService.alugarVeiculo(dto);
        return new ResponseEntity<>(novaLocacao, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<Locacao> encerrarLocacao(@PathVariable Long id) {
        Locacao locacaoEncerrada = locacaoService.encerrarLocacao(id);
        return new ResponseEntity<>(locacaoEncerrada, HttpStatus.OK);
    }
}
