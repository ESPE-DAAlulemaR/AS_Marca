package com.banquito.marca.aplicacion.servicio;

import com.banquito.marca.aplicacion.modelo.Cliente;
import com.banquito.marca.aplicacion.modelo.Tarjeta;
import com.banquito.marca.aplicacion.repositorio.ITarjetaRepository;
import com.banquito.marca.compartido.excepciones.OperacionInvalidaExcepcion;
import com.banquito.marca.compartido.utilidades.UtilidadHash;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TarjetaService {
    private final ITarjetaRepository repositorio;

    private final GeneradorTarjetaService generadorTarjetaService;
    private final ValidadorTarjetasService validadorTarjetasService;

    private static String TIPO_CREDITO = "CRD";
    private static String TIPO_DEBITO = "DEB";

    public TarjetaService(ITarjetaRepository repositorio) {
        this.repositorio = repositorio;

        this.generadorTarjetaService = new GeneradorTarjetaService();
        this.validadorTarjetasService = new ValidadorTarjetasService();
    }

    public void crearTarjeta(Tarjeta tarjeta, Cliente cliente) {
        tarjeta.setNumero(this.generadorTarjetaService.generarNumeroTarjetaVisaValido());
        tarjeta.setCvv(UtilidadHash.hashString(tarjeta.getCvv()));
        tarjeta.setFechaCaducidad(LocalDate.now().plusYears(5));
        tarjeta.setTipo(TarjetaService.TIPO_CREDITO);

        tarjeta.setClienteId(cliente.getId());

        repositorio.save(tarjeta);
    }
}
