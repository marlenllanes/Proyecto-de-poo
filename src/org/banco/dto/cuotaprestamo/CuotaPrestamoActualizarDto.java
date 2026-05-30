package org.banco.dto.cuotaprestamo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CuotaPrestamoActualizarDto(
        Integer idCuotaPrestamo,
        Integer idPrestamo,
        Integer numeroCuotaPrestamo,
        OffsetDateTime fechaVencimientoCuotaPrestamo,
        BigDecimal valorCuotaPrestamo,
        String estadoCuotaPrestamo
        ) {

}
