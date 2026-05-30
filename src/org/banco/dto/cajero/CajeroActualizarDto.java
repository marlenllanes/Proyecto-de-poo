package org.banco.dto.cajero;

public record CajeroActualizarDto(
        Integer idCajero,
        Integer idSucursalCajero,    
        String nombreCajero,
        String turnoCajero
) {

}