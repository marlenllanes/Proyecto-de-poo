package org.banco.dto.transaccioncajero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransaccionCajeroCrearDto(              
        String tipoTransaccionCajero,
        BigDecimal valorTransaccionCajero,
        OffsetDateTime fechaTransaccionCajero
        ) {

}
