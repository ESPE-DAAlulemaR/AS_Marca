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
    @Positive
    private BigDecimal valor;
    @NotBlank
    @Size(min = 0, max = 100, message = "La descripcion debe de tener un maximo de 100 caracteres")
    private String descripcion;
    @NotBlank
    @Size(min = 0, max = 100, message = "El beneficiario debe terne un maximo de 100 caracteres")
    private String beneficiario;
    @NotBlank
    private String numeroCuenta;
    @NotEmpty
    private Boolean esDiferido;
    @NotEmpty
    private Integer cuotas;
    @NotEmpty
    private DetalleJsonDTO detalle;
}
