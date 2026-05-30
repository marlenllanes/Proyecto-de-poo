package org.banco.dto.productofinanciero;

import java.math.BigDecimal;

public record ProductoFinancieroActualizarDto(
        Integer idProductoFinanciero,
        String nombreProductoFinanciero,
        String tipoProductoFinanciero,
        BigDecimal tasaInteresProductoFinanciero,
        String descripcionProductoFinanciero
        ) {

}
