package com.banquito.marca.aplicacion.dto.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransaccionRespuestaDTO {
    private String estado;
    private LocalDateTime fechaHora;
    private BigDecimal valor;
    private BigDecimal comision;
}
