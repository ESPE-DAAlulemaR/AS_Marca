package com.banquito.marca.aplicacion.controlador.dto.peticion;

import com.banquito.marca.aplicacion.controlador.dto.DetalleJsonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Schema(name = "Peticiones Transacciones")
public class TransaccionPeticionDTO {

    @Schema(description = "Número de tarjeta de crédito o débito", example = "1234567890123456")
    private String numeroTarjeta;

    @Schema(description = "Código de seguridad (CVV) de la tarjeta", example = "123")
    private String cvv;

    @Schema(description = "Fecha de caducidad de la tarjeta en formato MM/AA", example = "12/25")
    private String fechaCaducidad;

    @Schema(description = "Monto de la transacción", example = "100.50", type = "number", format = "decimal")
    private BigDecimal valor;

    @Schema(description = "Descripción de la transacción", example = "Compra de equipo de oficina")
    private String descripcion;

    @Schema(description = "Nombre del beneficiario de la transacción", example = "Juan Pérez")
    private String beneficiario;

    @Schema(description = "Número de cuenta asociada a la transacción", example = "1234567890")
    private String numeroCuenta;

    @Schema(description = "Indica si la transacción es diferida", example = "true")
    private Boolean esDiferido;

    @Schema(description = "Número de cuotas en las que se divide el pago", example = "12")
    private Integer cuotas;

    @Schema(description = "Detalles adicionales de la transacción", implementation = DetalleJsonDTO.class)
    private DetalleJsonDTO detalle;
}
