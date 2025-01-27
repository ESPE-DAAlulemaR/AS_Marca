package com.banquito.marca.aplicacion.controlador.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalleJsonDTO {
    @NotEmpty
    private ItemComisionDTO gtw;
    @NotEmpty
    private ItemComisionDTO processor;
    @NotEmpty
    private ItemComisionDTO marca;
}
