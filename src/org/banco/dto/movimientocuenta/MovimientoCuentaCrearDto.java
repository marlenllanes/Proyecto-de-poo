package org.banco.dto.movimientocuenta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MovimientoCuentaCrearDto(        
        String tipoMovimientoCuenta,
        BigDecimal valorMovimientoCuenta,
        OffsetDateTime fechaMovimientoCuenta,
        BigDecimal saldoPosteriorMovimientoCuenta
        ) {

}
