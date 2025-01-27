package com.banquito.marca.aplicacion.dto.peticion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Validación de Tarjeta", description = "DTO para validar datos básicos de una tarjeta de crédito")
public class TarjetaValidacionDTO {
    @NotBlank(message = "El número de tarjeta no debe ser vacío ni nulo")
    @Size(max = 16, message = "El número de tarjeta debe tener un máximo de 16 caracteres")
    @Schema(description = "Número de tarjeta de crédito")
    String numero;
    @NotEmpty(message = "La fecha de caducidad no debe ser vacía ni nula")
    @Size(max = 5, message = "La fecha de caducidad debe tener un maximo de 5 caracteres")
    @Schema(description = "Fecha de caducidad de la tarjeta en formato MM/AA", example = "12/25")
    String fechaCaducidad;
    @NotBlank(message = "El CVV no debe ser vacío ni nulo")
    @Size(max = 3, message = "El CVV debe tener un máximo de 3 caracteres")
    @Schema(description = "Código de seguridad (CVV) de la tarjeta")
    String cvv;
}
