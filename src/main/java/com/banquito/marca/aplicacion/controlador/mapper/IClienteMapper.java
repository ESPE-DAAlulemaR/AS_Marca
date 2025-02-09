package com.banquito.marca.aplicacion.controlador.mapper;

import com.banquito.marca.aplicacion.dto.peticion.TarjetaPeticionDTO;
import com.banquito.marca.aplicacion.modelo.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IClienteMapper {
    Cliente toPersistence(TarjetaPeticionDTO dto);
}
