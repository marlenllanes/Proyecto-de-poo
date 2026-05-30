package org.banco.dto.consumotarjeta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ConsumoTarjetaActualizarDto(
        Integer idConsumoTarjeta,
        Integer idTarjeta,
        OffsetDateTime fechaConsumoTarjeta,
        String establecimientoConsumoTarjeta,
        BigDecimal valorConsumoTarjeta,
        Integer cuotaConsumoTarjeta
        ) {

}
