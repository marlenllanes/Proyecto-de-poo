package org.banco.dto.movimientocuenta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MovimientoCuentaCrearDto(    
        Integer idCuenta,
        String tipoMovimientoCuenta,
        BigDecimal valorMovimientoCuenta,
        OffsetDateTime fechaMovimientoCuenta,
        BigDecimal saldoPosteriorMovimientoCuenta
        ) {

}
