package org.banco.dto.tarjeta;

import java.time.OffsetDateTime;
import org.banco.dto.cliente.ClienteDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;

public record TarjetaDto(
        Integer idTarjeta,
        String numeroTarjeta,
        String tipoTarjeta,
        OffsetDateTime fechaExpedicionTarjeta,
        OffsetDateTime fechaVencimientoTarjeta,
        String estadoTarjeta,
        ClienteDto cliente,
        CuentaBancariaDto cuenta
        ) {

}
