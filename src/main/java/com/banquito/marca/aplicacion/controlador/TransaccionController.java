package com.banquito.marca.aplicacion.controlador;

import com.banquito.marca.aplicacion.controlador.dto.peticion.TransaccionPeticionDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/transacciones")
public class TransaccionController {
    @PostMapping
    public ResponseEntity<?> almacenar(@Valid @RequestBody TransaccionPeticionDTO transaccionPeticionDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccionPeticionDTO);
    }
}
