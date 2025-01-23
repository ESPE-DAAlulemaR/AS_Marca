package com.banquito.marca.aplicacion.controlador.dto.peticion;

import com.banquito.marca.aplicacion.controlador.dto.DetalleJsonDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransaccionPeticionDTO {
    private String numeroTarjeta;
    private String cvv;
    private String fechaCaducidad;
    private BigDecimal valor;
    private String descripcion;
    private String canal;
    private String beneficiario;
    private String numeroCuenta;
    private String binBanco;
    private Boolean esDiferido;
    private Integer cuotas;
    private DetalleJsonDTO detalle;
}
