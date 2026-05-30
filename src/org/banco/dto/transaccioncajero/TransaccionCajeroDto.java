package org.banco.dto.transaccioncajero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransaccionCajeroDto(
        Integer idTransaccionCajero,        
        String tipoTransaccionCajero,
        BigDecimal valorTransaccionCajero,
        OffsetDateTime fechaTransaccionCajero
        ) {

}
