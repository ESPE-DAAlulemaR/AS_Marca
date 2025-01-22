package com.banquito.marca.aplicacion.repositorio;

import com.banquito.marca.aplicacion.modelo.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta, Integer> {
}
