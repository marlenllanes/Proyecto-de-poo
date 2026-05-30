package org.banco.dto.cuotaprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.prestamo.PrestamoDto;

public record CuotaPrestamoDto(
        Integer idCuotaPrestamo,
        PrestamoDto prestamo,
        Integer numeroCuotaPrestamo,
        OffsetDateTime fechaVencimientoCuotaPrestamo,
        BigDecimal valorCuotaPrestamo,
        String estadoCuotaPrestamo
        ) {

}
