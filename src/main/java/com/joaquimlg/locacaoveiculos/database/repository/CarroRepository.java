package com.joaquimlg.locacaoveiculos.database.repository;

import com.joaquimlg.locacaoveiculos.database.model.Carro;
import com.joaquimlg.locacaoveiculos.database.model.StatusCarro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByStatusNot(StatusCarro statusCarro);

    List<Carro> findByStatus(StatusCarro statusCarro);

    Carro findByPlaca(String placa);
}
