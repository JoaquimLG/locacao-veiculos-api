package com.joaquimlg.locacaoveiculos.repository;

import com.joaquimlg.locacaoveiculos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
}
