package com.banquito.marca.aplicacion.controlador.dto.peticion;

import com.banquito.marca.aplicacion.controlador.dto.DetalleJsonDTO;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransaccionPeticionDTO {
    @NotBlank
    @Size(min =0, max=16, message = "Numero de la tarjeta difiere del tamaño permitido")
    private String numeroTarjeta;
    @NotBlank
    @Size(min = 0, max = 3, message = "El CVV tiene que ser de un maximo de 3 caracteres")
    private String cvv;
    @NotEmpty
    private String fechaCaducidad;
    @NotEmpty
    private BigDecimal valor;
    @NotBlank
    @Size(min = 0, max = 100)
    private String descripcion;
    @NotBlank
    @Size(min = 0, max = 100)
    private String beneficiario;
    @NotBlank
    private String numeroCuenta;
    @NotEmpty
    private Boolean esDiferido;
    @NotEmpty
    @Positive
    private Integer cuotas;
    @NotEmpty
    @Size(min = 0, max = 100)
    private DetalleJsonDTO detalle;
}
