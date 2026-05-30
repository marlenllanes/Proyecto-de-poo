package org.banco.dto.prestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PrestamoCrearDto( 
        Integer idCliente,
        BigDecimal montoPrestamo,
        BigDecimal tasaInteresPrestamo,
        OffsetDateTime fechaDesembolsoPrestamo,
        String estadoPrestamo

) {

}
