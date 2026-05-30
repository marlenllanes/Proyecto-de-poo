package org.banco.dto.prestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cliente.ClienteDto;

public record PrestamoDto(
        Integer idPrestamo,
        ClienteDto cliente,
        BigDecimal montoPrestamo,
        BigDecimal tasaInteresPrestamo,
        OffsetDateTime fechaDesembolsoPrestamo,
        String estadoPrestamo
        ) {

}
