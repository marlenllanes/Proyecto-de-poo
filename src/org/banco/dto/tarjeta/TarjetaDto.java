package org.banco.dto.tarjeta;

import java.time.OffsetDateTime;

public record TarjetaDto(
        Integer idTarjeta,
        String numeroTarjeta,
        String tipoTarjeta,
        OffsetDateTime fechaExpedicionTarjeta,
        OffsetDateTime fechaVencimientoTarjeta,
        String estadoTarjeta        
        ) {

}
