package com.banquito.marca.aplicacion.servicio;

import com.banquito.marca.aplicacion.repositorio.ITransaccionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {
    private final ITransaccionRepository repositorio;

    public TransaccionService(ITransaccionRepository repositorio) {
        this.repositorio = repositorio;
    }
}
