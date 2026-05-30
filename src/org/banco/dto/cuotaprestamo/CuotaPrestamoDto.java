package org.banco.dto.cuotaprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CuotaPrestamoDto(
        Integer idCuotaPrestamo,
        Integer idPrestamoCuota,
        Integer numeroCuotaPrestamo,
        OffsetDateTime fechaVencimientoCuotaPrestamo,
        BigDecimal valorCuotaPrestamo,
        String estadoCuotaPrestamo
        ) {

}
