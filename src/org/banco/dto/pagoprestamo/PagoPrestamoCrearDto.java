package org.banco.dto.pagoprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PagoPrestamoCrearDto(
        Integer idPrestamo,
        OffsetDateTime fechaPagoPrestamo,
        BigDecimal valorPagoPrestamo,
        String metodoPagoPrestamo
        ) {

}
