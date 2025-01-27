package com.banquito.marca.aplicacion.controlador.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemComisionDTO {
    @NotBlank(message = "La referencia no debe ser vacía ni nula")
    @Size(min = 1, max = 100, message = "La referencia debe tener un máximo de 100 caracteres")
    private String referencia;
    @NotNull(message = "La comisión no debe ser nula")
    @PositiveOrZero(message = "La comisión debe ser un valor positivo o cero")
    private BigDecimal comision;
    @NotBlank(message = "El número de cuenta no debe ser vacío ni nulo")
    @Size(min = 1, max = 50, message = "El número de cuenta debe tener un máximo de 50 caracteres")
    private String numeroCuenta;
}
