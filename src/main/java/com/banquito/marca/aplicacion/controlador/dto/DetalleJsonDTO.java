package com.banquito.marca.aplicacion.controlador.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalleJsonDTO {
    @NotEmpty(message = "El objeto 'gtw' no debe ser vacío ni nulo")
    private ItemComisionDTO gtw;
    @NotEmpty(message = "El objeto 'processor' no debe ser vacío ni nulo")
    private ItemComisionDTO processor;
    @NotEmpty(message = "El objeto 'marca' no debe ser vacío ni nulo")
    private ItemComisionDTO marca;
}
