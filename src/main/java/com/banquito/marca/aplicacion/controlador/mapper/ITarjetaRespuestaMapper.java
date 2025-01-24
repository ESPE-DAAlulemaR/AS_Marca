package com.banquito.marca.aplicacion.controlador.mapper;

import com.banquito.marca.aplicacion.controlador.dto.peticion.TarjetaPeticionDTO;
import com.banquito.marca.aplicacion.controlador.dto.respuesta.TarjetaRespuestaDTO;
import com.banquito.marca.aplicacion.modelo.Tarjeta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ITarjetaRespuestaMapper {
    TarjetaRespuestaDTO toDto(Tarjeta tarjeta);
    Tarjeta toPersistence(TarjetaRespuestaDTO dto);
}
