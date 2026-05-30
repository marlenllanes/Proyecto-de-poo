package org.banco.dto.productofinanciero;

import java.math.BigDecimal;

public record ProductoFinancieroDto(
        Integer idProductoFinanciero,
        String nombreProductoFinanciero,
        String tipoProductoFinanciero,
        BigDecimal tasaInteresProductoFinanciero,
        String descripcionProductoFinanciero
        ) {

}
