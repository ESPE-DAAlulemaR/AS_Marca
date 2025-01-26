package com.banquito.marca.aplicacion.controlador;

import com.banquito.marca.aplicacion.controlador.dto.peticion.TransaccionPeticionDTO;
import com.banquito.marca.aplicacion.controlador.dto.respuesta.TarjetaRespuestaDTO;
import com.banquito.marca.aplicacion.controlador.dto.respuesta.TransaccionRespuestaDTO;
import com.banquito.marca.aplicacion.controlador.mapper.ITransaccionPeticionMapper;
import com.banquito.marca.aplicacion.controlador.mapper.ITransaccionRespuestaMapper;
import com.banquito.marca.aplicacion.modelo.Tarjeta;
import com.banquito.marca.aplicacion.modelo.Transaccion;
import com.banquito.marca.aplicacion.servicio.TarjetaService;
import com.banquito.marca.aplicacion.servicio.TransaccionService;
import com.banquito.marca.aplicacion.servicio.ValidadorTarjetasService;
import com.banquito.marca.compartido.excepciones.OperacionInvalidaExcepcion;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import java.util.UUID;

@RestController
@RequestMapping("/v1/transacciones")
public class TransaccionController {
    private final TransaccionService transaccionService;
    private final ITransaccionPeticionMapper transaccionPeticionMapper;
    private final ITransaccionRespuestaMapper transaccionRespuestaMapper;
    private final Cache<String, Page<TransaccionRespuestaDTO>> responseCache;

    private final TarjetaService tarjetaService;

    private final ValidadorTarjetasService validadorTarjetasService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public TransaccionController(
            TransaccionService transaccionService,
            ITransaccionPeticionMapper transaccionPeticionMapper,
            ITransaccionRespuestaMapper transaccionRespuestaMapper,
            TarjetaService tarjetaService,
            ValidadorTarjetasService validadorTarjetasService) {
        this.transaccionService = transaccionService;
        this.transaccionPeticionMapper = transaccionPeticionMapper;
        this.transaccionRespuestaMapper = transaccionRespuestaMapper;
        this.tarjetaService = tarjetaService;
        this.validadorTarjetasService = validadorTarjetasService;
        this.responseCache = Caffeine.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS)
                .maximumSize(10_000)
                .build();

    }

    @PostMapping
    public ResponseEntity<?> almacenar(@Valid @RequestBody TransaccionPeticionDTO transaccionPeticionDTO) {
        if (this.validadorTarjetasService.esNumeroTarjetaValido(transaccionPeticionDTO.getNumeroTarjeta()))
            throw new OperacionInvalidaExcepcion("La tarjeta no es válida");

        Transaccion transaccion = this.transaccionPeticionMapper.toPersistence(transaccionPeticionDTO);
        Tarjeta tarjeta = this.tarjetaService.buscarPorNuemro(transaccionPeticionDTO.getNumeroTarjeta());

        this.transaccionService.registrarTransaccion(transaccion, tarjeta, transaccionPeticionDTO.getCvv(),
                transaccionPeticionDTO.getFechaCaducidad());
        TransaccionRespuestaDTO respuestaDTO = this.transaccionRespuestaMapper.toDto(transaccion);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<TransaccionRespuestaDTO>> listar(
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey, 
            @PageableDefault(size = 20, sort = "fechaHora", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            @RequestParam(required = false) String numeroTarjeta) {
        // Si el header no está presente, genera un ID aleatorio
        if (idempotencyKey == null || idempotencyKey.isEmpty()) {
            idempotencyKey = UUID.randomUUID().toString();
        }

        // Verificar si la respuesta ya está en caché
        Page<TransaccionRespuestaDTO> cachedResponse = responseCache.getIfPresent(idempotencyKey);
        if (cachedResponse != null) {
            return ResponseEntity.ok(cachedResponse);
        }

        // Obtener transacciones
        Page<Transaccion> transacciones = transaccionService.listarTransacciones(
                estado, fechaDesde, fechaHasta, numeroTarjeta, pageable);

        // Convertir a DTO
        Page<TransaccionRespuestaDTO> respuesta = transacciones.map(transaccionRespuestaMapper::toDto);

        // Guardar en caché con un idempotencyKey válido
        responseCache.put(idempotencyKey, respuesta);

        return ResponseEntity.ok(respuesta);
    }
}
