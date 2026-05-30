package org.banco.dto.sucursal;

public record SucursalActualizarDto(
        Integer idSucursal,
        String nombreSucursal,
        String direccionSucursal,
        String telefonoSucursal
        ) {

}
