package org.banco.dto.cajero;

import org.banco.dto.sucursal.SucursalDto;

public record CajeroDto(
        Integer idCajero,
        SucursalDto idSucursalCajero,    
        String nombreCajero,
        String turnoCajero

) {

}