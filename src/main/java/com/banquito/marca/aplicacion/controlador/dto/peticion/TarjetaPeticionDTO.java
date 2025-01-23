package com.banquito.marca.aplicacion.controlador.dto.peticion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarjetaPeticionDTO {
    private String identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String tipo;
    private String franquicia;
}
