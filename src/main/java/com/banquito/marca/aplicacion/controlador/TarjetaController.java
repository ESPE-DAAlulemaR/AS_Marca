package com.banquito.marca.aplicacion.controlador;

import com.banquito.marca.aplicacion.controlador.dto.peticion.TarjetaPeticionDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/tarjetas")
public class TarjetaController {
    @PostMapping
    public ResponseEntity<?> almacenar(@Valid @RequestBody TarjetaPeticionDTO tarjetaPeticionDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaPeticionDTO);
    }
}
