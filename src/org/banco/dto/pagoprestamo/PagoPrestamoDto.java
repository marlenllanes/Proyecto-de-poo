package org.banco.dto.pagoprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PagoPrestamoDto(
        Integer idPagoPrestamo,
        OffsetDateTime fechaPagoPrestamo,
        BigDecimal valorPagoPrestamo,
        String metodoPagoPrestamo
        ) {

}
