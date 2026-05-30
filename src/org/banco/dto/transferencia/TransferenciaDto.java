package org.banco.dto.transferencia;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;

public record TransferenciaDto(
        Integer idTransferencia,
        CuentaBancariaDto cuentaOrigen,
        CuentaBancariaDto cuentaDestino,
        OffsetDateTime fechaTransferencia,
        BigDecimal valorTransferencia,
        String descripcionTransferencia
        ) {

}
