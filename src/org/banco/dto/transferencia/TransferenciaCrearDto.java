package org.banco.dto.transferencia;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransferenciaCrearDto(
        Integer idCuentaOrigen,
        Integer idCuentaDestino,
        OffsetDateTime fechaTransferencia,
        BigDecimal valorTransferencia,
        String descripcionTransferencia
        ) {

}
