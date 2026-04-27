package com.joaquimlg.locacaoveiculos.repository;

import com.joaquimlg.locacaoveiculos.entity.Carro;
import com.joaquimlg.locacaoveiculos.entity.StatusCarro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByStatusNot(StatusCarro statusCarro);

    List<Carro> findByStatus(StatusCarro statusCarro);

    Optional<Carro> findByPlaca(String placa);

    boolean existsByPlaca(String placa);
}
