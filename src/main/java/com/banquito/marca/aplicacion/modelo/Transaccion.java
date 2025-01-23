package com.banquito.marca.aplicacion.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer tarjetaId;
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_hora;

    private BigDecimal valor;
    private BigDecimal comision;
    private Boolean esCorriente;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_creacion;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_actualizacion;
}
