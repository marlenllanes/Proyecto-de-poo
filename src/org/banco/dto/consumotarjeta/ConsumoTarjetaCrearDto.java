package org.banco.dto.consumotarjeta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ConsumoTarjetaCrearDto(
        Integer idTarjeta,
        OffsetDateTime fechaConsumoTarjeta,
        String establecimientoConsumoTarjeta,
        BigDecimal valorConsumoTarjeta,
        Integer cuotaConsumoTarjeta
        ) {

}
