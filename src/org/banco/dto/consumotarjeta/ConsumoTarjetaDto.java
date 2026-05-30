package org.banco.dto.consumotarjeta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ConsumoTarjetaDto(
        Integer idConsumoTarjeta,
        Integer idTarjetaConsumo,        
        OffsetDateTime fechaConsumoTarjeta,
        String establecimientoConsumoTarjeta,
        BigDecimal valorConsumoTarjeta,
        Integer cuotaConsumoTarjeta
        ) {

}
