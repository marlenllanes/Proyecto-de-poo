package org.banco.dto.cuentabancaria;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CuentaBancariaCrearDto(
        String numeroCuentaBancaria,
        String tipoCuentaBancaria,
        BigDecimal saldoCuentaBancaria,
        OffsetDateTime fechaAperturaCuentaBancaria,
        String estadoCuentaBancaria,
        Integer idCliente
        ) {

}
