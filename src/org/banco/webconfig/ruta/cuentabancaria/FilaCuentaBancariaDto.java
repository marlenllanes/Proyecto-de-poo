package org.banco.webconfig.ruta.cuentabancaria;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record FilaCuentaBancariaDto(
        Integer idCuentaBancaria,
        String numeroCuentaBancaria,
        String tipoCuentaBancaria,
        BigDecimal saldoCuentaBancaria,
        OffsetDateTime fechaAperturaCuentaBancaria,
        String estadoCuentaBancaria,
        Integer idCliente,
        String nombreCliente
        ) {

}
