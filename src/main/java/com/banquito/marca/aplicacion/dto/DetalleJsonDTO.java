package com.banquito.marca.aplicacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "Detalle Comisiones", description = "Representa detalle de las comisiones")
public class DetalleJsonDTO {
    @NotEmpty(message = "El objeto 'gtw' no debe ser vacío ni nulo")
    @Schema(description = "Comisión de Gateway (Gtw)", example = "Comisión asociada al Gateway")
    private ItemComisionDTO gtw;
    @NotEmpty(message = "El objeto 'processor' no debe ser vacío ni nulo")
    @Schema(description = "Comisión de Processor", example = "Comisión asociada al Processor")
    private ItemComisionDTO processor;
    @Schema(description = "Comisión de la Marca", example = "Comisión asociada a la Marca")
    private ItemComisionDTO marca;
}
