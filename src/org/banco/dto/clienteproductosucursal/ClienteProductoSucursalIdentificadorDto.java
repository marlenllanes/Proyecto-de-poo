package org.banco.dto.clienteproductosucursal;


public record ClienteProductoSucursalIdentificadorDto(
        int idCliente,
        int idProductoFinanciero,
        int idSucursal
        ) {

}
