package com.banquito.marca.aplicacion.repositorio;

import com.banquito.marca.aplicacion.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
