
package org.banco.dto.clienteproductosucursal;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.dto.cliente.ClienteDto;
import org.banco.dto.productofinanciero.ProductoFinancieroDto;
import org.banco.dto.sucursal.SucursalDto;

public record ClienteProductoSucursalDto(
   ClienteDto cliente,
   ProductoFinancieroDto productoFinanciero,
    SucursalDto sucursal,
    OffsetDateTime fechaAdquisicion,
   BigDecimal valorInicial,
    String estado
        ) {

}
