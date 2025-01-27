package com.banquito.marca.aplicacion.controlador.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemComisionDTO {

    @Schema(description = "Referencia asociada a la comisión", example = "REF12345")
    private String referencia;

    @Schema(description = "Valor de la comisión", example = "150.75", type = "number", format = "decimal")
    private BigDecimal comision;

    @Schema(description = "Número de cuenta relacionado con la comisión", example = "1234567890")
    private String numeroCuenta;
}
