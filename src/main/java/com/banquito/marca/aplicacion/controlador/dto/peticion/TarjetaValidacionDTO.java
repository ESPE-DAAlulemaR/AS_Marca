package com.banquito.marca.aplicacion.controlador.dto.peticion;

import lombok.Data;

@Data
public class TarjetaValidacionDTO {
    String numero;
    String fechaCaducidad;
    String cvv;
}
