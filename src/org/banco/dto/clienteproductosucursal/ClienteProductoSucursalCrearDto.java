package org.banco.dto.clienteproductosucursal;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ClienteProductoSucursalCrearDto(
        Integer idCliente,
        Integer idProductoFinanciero,
        Integer idSucursal,
        OffsetDateTime fechaAdquisicion,
        BigDecimal valorInicial,
        String estado
        ) {

}
