package org.banco.dto.pagoprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.prestamo.PrestamoDto;

public record PagoPrestamoDto(
        Integer idPagoPrestamo,
        PrestamoDto prestamo,
        OffsetDateTime fechaPagoPrestamo,
        BigDecimal valorPagoPrestamo,
        String metodoPagoPrestamo
        ) {

}
