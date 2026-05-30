package org.banco.dto.clienteproductosucursal;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ClienteProductoSucursalActualizarDto(
        Integer idCliente,
        Integer idProductoFinanciero,
        Integer idSucursal,
        OffsetDateTime fechaAdquisicion,
        BigDecimal valorInicial,
        String estado
        ) {

}
