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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/transacciones")
@Tag(name = "Transacciones", description = "Documentacion Transacciones")
public class TransaccionController {
    private final TransaccionService transaccionService;
    private final ITransaccionPeticionMapper transaccionPeticionMapper;
    private final ITransaccionRespuestaMapper transaccionRespuestaMapper;
    private final TarjetaService tarjetaService;
    private final ValidadorTarjetasService validadorTarjetasService;

    public TransaccionController(
            TransaccionService transaccionService,
            ITransaccionPeticionMapper transaccionPeticionMapper,
            ITransaccionRespuestaMapper transaccionRespuestaMapper,
            TarjetaService tarjetaService,
            ValidadorTarjetasService validadorTarjetasService
    ) {
        this.transaccionService = transaccionService;
        this.transaccionPeticionMapper = transaccionPeticionMapper;
        this.transaccionRespuestaMapper = transaccionRespuestaMapper;
        this.tarjetaService = tarjetaService;
        this.validadorTarjetasService = validadorTarjetasService;
    }

    @Operation(
            summary = "Registrar una transacción",
            description = "Registra una nueva transacción validando el número de tarjeta y su información asociada.",
            requestBody = @RequestBody(
                    description = "Información necesaria para registrar una transacción",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransaccionPeticionDTO.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transacción registrada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransaccionRespuestaDTO.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o tarjeta no válida",
                    content = @Content(mediaType = "application/json")),
            
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado", 
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransaccionRespuestaDTO.class)))
    })

    @PostMapping
    public ResponseEntity<?> almacenar(@Valid @RequestBody TransaccionPeticionDTO transaccionPeticionDTO) {
        if (this.validadorTarjetasService.esNumeroTarjetaValido(transaccionPeticionDTO.getNumeroTarjeta()))
            throw new OperacionInvalidaExcepcion("La tarjeta no es válida");

        Transaccion transaccion = this.transaccionPeticionMapper.toPersistence(transaccionPeticionDTO);
        Tarjeta tarjeta = this.tarjetaService.buscarPorNuemro(transaccionPeticionDTO.getNumeroTarjeta());

        this.transaccionService.registrarTransaccion(transaccion, tarjeta, transaccionPeticionDTO.getCvv(), transaccionPeticionDTO.getFechaCaducidad());
        TransaccionRespuestaDTO respuestaDTO = this.transaccionRespuestaMapper.toDto(transaccion);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaDTO);
    }
}
