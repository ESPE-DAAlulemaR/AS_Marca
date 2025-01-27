package com.banquito.marca.aplicacion.controlador.dto.peticion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "Peticiones Tarjeta")
public class TarjetaPeticionDTO {

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 13, message = "El número de identificación tiene máximo 13 caracteres y mínimo 10")
    @Schema(description = "Número de identificación de la tarjeta)",
            example = "1234567890",
            required = true,
            minLength = 10,
            maxLength = 13)
    private String identificacion;

    @Schema(description = "Nombre del titular de la tarjeta",
            example = "Juan Pérez")
    private String nombre;

    @Schema(description = "Dirección del titular de la tarjeta",
            example = "Calle Ficticia 123, Ciudad, País")
    private String direccion;

    @Schema(description = "Teléfono de contacto del titular",
            example = "+1234567890")
    private String telefono;

    @Schema(description = "Correo electrónico de contacto del titular",
            example = "juan.perez@correo.com")
    private String correo;

    @Schema(description = "Tipo de tarjeta (por ejemplo, crédito, débito)",
            example = "Crédito")
    private String tipo;

    @Schema(description = "Franquicia de la tarjeta (por ejemplo, Visa, MasterCard)",
            example = "Visa")
    private String franquicia;
}
