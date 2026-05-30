package org.banco.dto.movimientocuenta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;

public record MovimientoCuentaDto(
        Integer idMovimientoCuenta,
        CuentaBancariaDto cuenta,
        String tipoMovimientoCuenta,
        BigDecimal valorMovimientoCuenta,
        OffsetDateTime fechaMovimientoCuenta,
        BigDecimal saldoPosteriorMovimientoCuenta
        ) {

}
