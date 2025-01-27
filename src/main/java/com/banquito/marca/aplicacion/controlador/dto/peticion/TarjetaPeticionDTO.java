package com.banquito.marca.aplicacion.controlador.dto.peticion;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TarjetaPeticionDTO {
    @NotBlank
    @Size(min = 10, max = 13, message = "El número de identificación tiene maximo 10 caracteres y minimo 10")
    private String identificacion;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;
    @NotBlank
    @Size(min = 0, max = 50)
    private String direccion;
    @NotBlank
    @Size(min = 3, max = 50)
    private String telefono;
    @NotEmpty
    @Email
    private String correo;
    @NotBlank
    @Size(min = 0, max = 3)
    private String tipo;
    @NotBlank
    @Size(min = 4, max = 10)
    private String franquicia;
}
