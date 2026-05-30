package org.banco.dto.tarjeta;

import java.time.OffsetDateTime;

public record TarjetaCrearDto(
        String numeroTarjeta,
        String tipoTarjeta,
        OffsetDateTime fechaExpedicionTarjeta,
        OffsetDateTime fechaVencimientoTarjeta,
        String estadoTarjeta,
        Integer idCliente,
        Integer idCuenta
        ) {

}
