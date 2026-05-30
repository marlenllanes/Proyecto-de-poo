package org.banco.dto.cuentabancaria;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cliente.ClienteDto;

public record CuentaBancariaDto(
        Integer idCuentaBancaria,
        String numeroCuentaBancaria,
        String tipoCuentaBancaria,
        BigDecimal saldoCuentaBancaria,
        OffsetDateTime fechaAperturaCuentaBancaria,
        String estadoCuentaBancaria,
        ClienteDto cliente
        ) {

}
