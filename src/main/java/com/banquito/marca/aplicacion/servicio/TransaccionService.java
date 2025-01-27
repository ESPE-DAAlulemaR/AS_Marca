package com.banquito.marca.aplicacion.servicio;

import com.banquito.marca.aplicacion.modelo.Tarjeta;
import com.banquito.marca.aplicacion.modelo.Transaccion;
import com.banquito.marca.aplicacion.repositorio.ITransaccionRepository;
import com.banquito.marca.compartido.excepciones.OperacionInvalidaExcepcion;
import com.banquito.marca.compartido.utilidades.UtilidadHash;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionService {
    private final ITransaccionRepository repositorio;

    private final ValidadorTarjetasService validadorTarjetasService;

    private static final String ESTADO_PENDIENTE = "PEN";
    private static final String ESTADO_APROVADA = "APR";
    private static final String ESTADO_RECHAZADA = "REC";
    private static final String ESTADO_REVERSADA = "REV";

    private static final BigDecimal PORCENTAJE_COMISION = BigDecimal.valueOf(0.001);

    public TransaccionService(
            ITransaccionRepository repositorio,
            ValidadorTarjetasService validadorTarjetasService) {
        this.repositorio = repositorio;
        this.validadorTarjetasService = validadorTarjetasService;
    }

    public void registrarTransaccion(Transaccion transaccion, Tarjeta tarjeta, String cvv, String fechaCaducidad) {
        if (!UtilidadHash.verificarString(cvv, tarjeta.getCvv()))
            throw new OperacionInvalidaExcepcion("Código de seguridad de la tarjeta incorrecto");

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth fechaEntrada = YearMonth.parse(fechaCaducidad, inputFormatter);
        ;
        YearMonth fechaBaseDatos = YearMonth.from(tarjeta.getFechaCaducidad());

        if (!fechaEntrada.equals(fechaBaseDatos))
            throw new OperacionInvalidaExcepcion("La fecha de caducidad de la tarjeta incorrecta");

        transaccion.setTarjetaId(tarjeta.getId());
        transaccion.setEstado(TransaccionService.ESTADO_PENDIENTE);
        transaccion.setFechaHora(LocalDateTime.now());
        transaccion.setComision(this.calcularComision(transaccion.getValor()));
        transaccion.setFechaActualizacion(LocalDateTime.now());

        this.repositorio.save(transaccion);
    }

    private BigDecimal calcularComision(BigDecimal valor) {
        return valor.multiply(PORCENTAJE_COMISION);
    }

    public Page<Transaccion> listarTransacciones(
            String estado,
            LocalDateTime fechaDesde,
            LocalDateTime fechaHasta,
            String numeroTarjeta,
            Pageable pageable) {
        return repositorio.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por estado
            if (estado != null && !estado.isEmpty()) {
                predicates.add(cb.equal(root.get("estado"), estado));
            }

            // Filtro por fecha desde
            if (fechaDesde != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("fechaHora"), fechaDesde));
            }

            // Filtro por fecha hasta
            if (fechaHasta != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("fechaHora"), fechaHasta));
            }

            // Filtro por número de tarjeta
            if (numeroTarjeta != null && !numeroTarjeta.isEmpty()) {
                Join<Transaccion, Tarjeta> tarjetaJoin = root.join("tarjeta");
                predicates.add(cb.equal(tarjetaJoin.get("numero"), numeroTarjeta));
            }

            // Agregar ordenamiento por fechaHora descendente
            query.orderBy(cb.desc(root.get("fechaHora")));

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

}
