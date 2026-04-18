package com.joaquimlg.locacaoveiculos.database.repository;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
