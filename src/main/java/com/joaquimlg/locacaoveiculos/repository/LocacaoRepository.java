package com.joaquimlg.locacaoveiculos.repository;

import com.joaquimlg.locacaoveiculos.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
