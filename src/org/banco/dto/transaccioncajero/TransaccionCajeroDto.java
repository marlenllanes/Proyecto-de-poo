package org.banco.dto.transaccioncajero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cajero.CajeroDto;

public record TransaccionCajeroDto(
        Integer idTransaccionCajero,
        CajeroDto cajero,
        String tipoTransaccionCajero,
        BigDecimal valorTransaccionCajero,
        OffsetDateTime fechaTransaccionCajero
        ) {

}
