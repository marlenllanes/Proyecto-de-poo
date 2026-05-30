package org.banco.dto.consumotarjeta;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.tarjeta.TarjetaDto;

public record ConsumoTarjetaDto(
        Integer idConsumoTarjeta,
        TarjetaDto tarjeta,        
        OffsetDateTime fechaConsumoTarjeta,
        String establecimientoConsumoTarjeta,
        BigDecimal valorConsumoTarjeta,
        Integer cuotaConsumoTarjeta
        ) {

}
