package org.banco.dto.cuotaprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CuotaPrestamoCrearDto(
        Integer idPrestamoCuota,
        Integer numeroCuotaPrestamo,
        OffsetDateTime fechaVencimientoCuotaPrestamo,
        BigDecimal valorCuotaPrestamo,
        String estadoCuotaPrestamo
        ) {

}
