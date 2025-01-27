package com.banquito.marca.aplicacion.controlador.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalleJsonDTO {

    @Schema(description = "Comisión de Gateway (Gtw)", example = "Comisión asociada al Gateway")
    private ItemComisionDTO gtw;

    @Schema(description = "Comisión de Processor", example = "Comisión asociada al Processor")
    private ItemComisionDTO processor;

    @Schema(description = "Comisión de la Marca", example = "Comisión asociada a la Marca")
    private ItemComisionDTO marca;
}
