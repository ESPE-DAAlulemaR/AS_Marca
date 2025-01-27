package com.banquito.marca.aplicacion.controlador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemComisionDTO {
    @NotBlank
    private String referencia;
    @NotEmpty
    @Positive
    private BigDecimal comision;
    @NotEmpty
    @Size(min = 0, max = 8)
    private String numeroCuenta;
}
