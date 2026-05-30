package org.banco.dto.transaccioncajero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cajero.CajeroDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;

public record TransaccionCajeroDto(
        Integer idTransaccionCajero,
        CajeroDto cajero,
        CuentaBancariaDto cuenta,
        String tipoTransaccionCajero,
        BigDecimal valorTransaccionCajero,
        OffsetDateTime fechaTransaccionCajero
        ) {

}
