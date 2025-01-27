package com.banquito.marca.aplicacion.dto.peticion;

import com.banquito.marca.aplicacion.dto.DetalleJsonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Schema(name = "Peticion Transacciones")
public class TransaccionPeticionDTO {
    @NotNull(message = "El numero de la tarjeta no debe ser NULO")
    @NotEmpty(message = "EL numero no debe estar VACIO")
    @NotBlank(message = "El número de tarjeta no debe ser vacío")
    @Size(max = 16, message = "El número de tarjeta debe tener un máximo de 16 caracteres")
    @Schema(description = "Número de tarjeta de crédito", example = "1234567890123456")
    private String numeroTarjeta;
    
    @NotBlank(message = "El CVV no debe ser vacío ni nulo")
    @Size(max = 3, message = "El CVV debe tener un máximo de 3 caracteres")
    @Schema(description = "Código de seguridad (CVV) de la tarjeta", example = "123")
    private String cvv;
    
    @NotEmpty(message = "La fecha de caducidad no debe ser vacía ni nula")
    @Size(max = 5, message = "La fecha de caducidad debe tener un maximo de 5 caracteres")
    @Schema(description = "Fecha de caducidad de la tarjeta en formato MM/AA", example = "12/25")
    private String fechaCaducidad;
    
    @NotEmpty(message = "El valor no debe ser vacío ni nulo")
    @Positive(message = "El valor debe ser positivo")
    @Schema(description = "Monto de la transacción", example = "100.50", type = "number", format = "decimal")
    private BigDecimal valor;
    
    @NotBlank(message = "La descripción no debe ser vacía ni nula")
    @Size(max = 100, message = "La descripción debe tener un máximo de 100 caracteres")
    @Schema(description = "Descripción de la transacción", example = "Compra de equipo de oficina")
    private String descripcion;
    
    @NotBlank(message = "El beneficiario no debe ser vacío ni nulo")
    @Size(max = 100, message = "El beneficiario debe tener un máximo de 100 caracteres")
    @Schema(description = "Nombre del beneficiario de la transacción")
    private String beneficiario;
    
    @NotBlank(message = "El número de cuenta no debe ser vacío ni nulo")
    @Size(max = 15, message = "El número de cuenta no debe tener entre 8 y 15 caracteres")
    @Schema(description = "Número de cuenta asociada a la transacción", example = "1234567890")
    private String numeroCuenta;
    
    @NotEmpty(message = "El campo esDiferido no debe ser vacío ni nulo")
    @Schema(description = "Indica si la transacción es diferida", example = "true")
    private Boolean esDiferido;
    
    @NotEmpty(message = "El número de cuotas no debe ser vacío ni nulo")
    @Positive(message = "El valor debe ser positivo")
    @Schema(description = "Número de cuotas en las que se divide el pago")
    private Integer cuotas;
    
    @NotEmpty(message = "El detalle no debe ser vacío ni nulo")
    @Schema(description = "Detalles adicionales de la transacción", implementation = DetalleJsonDTO.class)
    private DetalleJsonDTO detalle;
}
