package org.banco.dto.transferencia;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransferenciaActualizarDto(
        Integer idTransferencia,
        Integer idCuentaOrigen,
        Integer idCuentaDestino,
        OffsetDateTime fechaTransferencia,
        BigDecimal valorTransferencia,
        String descripcionTransferencia
        ) {

}
