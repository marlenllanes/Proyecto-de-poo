package org.banco.dto.cajero;

public record CajeroActualizarDto(
        Integer idCajero,
        Integer idSucursal,    
        String nombreCajero,
        String turnoCajero
) {

}