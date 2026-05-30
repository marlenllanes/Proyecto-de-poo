package org.banco.dto.cajero;

public record CajeroDto(
        Integer idCajero,
        Integer idSucursalCajero,    
        String nombreCajero,
        String turnoCajero

) {

}