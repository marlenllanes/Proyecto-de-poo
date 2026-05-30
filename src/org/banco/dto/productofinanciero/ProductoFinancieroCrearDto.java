package org.banco.dto.productofinanciero;

import java.math.BigDecimal;

public record ProductoFinancieroCrearDto(
        String nombreProductoFinanciero,
        String tipoProductoFinanciero,
        BigDecimal tasaInteresProductoFinanciero,
        String descripcionProductoFinanciero
        ) {

}
