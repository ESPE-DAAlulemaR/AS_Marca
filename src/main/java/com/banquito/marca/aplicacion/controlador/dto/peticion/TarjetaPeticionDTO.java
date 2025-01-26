package com.banquito.marca.aplicacion.controlador.dto.peticion;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarjetaPeticionDTO {
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 13, message = "El número de identificación tiene maximo 10 caracteres y minimo 10")
    private String identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String tipo;
    private String franquicia;
}
