package com.banquito.marca.aplicacion.dto.peticion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "Peticion Tarjeta")
public class TarjetaPeticionDTO {
    @NotBlank(message = "El número de identificación no debe ser vacío ni nulo")
    @Size(max = 13, message = "El número de identificación debe tener entre 10 y 13 caracteres")
    @Schema(description = "Número de identificación de la tarjeta)")
    private String identificacion;

    @NotBlank(message = "El nombre no debe ser vacío ni nulo")
    @Size(max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del titular de la tarjeta",
            example = "Juan Pérez")
    private String nombre;

    @NotBlank(message = "La dirección no debe ser vacía ni nula")
    @Size(max = 50, message = "La dirección debe tener un máximo de 50 caracteres")
    @Schema(description = "Dirección del titular de la tarjeta")
    private String direccion;

    @NotBlank(message = "El teléfono no debe ser vacío ni nulo")
    @Size(max = 13, message = "El teléfono debe tener 13 caracteres")
    @Schema(description = "Teléfono de contacto del titular",
            example = "+1234567890")
    private String telefono;

    @NotBlank(message = "El correo no debe ser vacío ni nulo")
    @Email(message = "El correo debe tener un formato válido (ejemplo@dominio.com)")
    @Schema(description = "Correo electrónico de contacto del titular")
    private String correo;

    @NotBlank(message = "El tipo de tarjeta no debe ser vacío ni nulo")
    @Size(max = 3, message = "El tipo debe tener un máximo de 3 caracteres")
    @Schema(description = "Tipo de tarjeta (por ejemplo, crédito, débito)",
            example = "Crédito")
    private String tipo;

    @NotBlank(message = "La franquicia no debe ser vacía ni nula")
    @Size(max = 10, message = "La franquicia debe tener entre 4 y 10 caracteres")
    @Schema(description = "Franquicia de la tarjeta (por ejemplo, Visa, MasterCard)",
            example = "Visa")
    private String franquicia;
}