package com.banquito.marca.aplicacion.servicio;

import org.springframework.stereotype.Service;

@Service
public class ValidadorTarjetasService {
    public static Boolean esNumeroTarjetaValido(String numeroTarjeta) {
        int suma = 0;
        boolean alternar = false;

        for (int i = numeroTarjeta.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroTarjeta.charAt(i));

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            suma += digito;
            alternar = !alternar;
        }

        return suma % 10 == 0;
    }
}
