package org.banco.dto.cliente;

import java.time.OffsetDateTime;

public record ClienteDto(
        Integer idCliente,
        String nombreCliente,
        String documentoCliente,
        String correoCliente,
        String celularCliente,
        OffsetDateTime fechaNacimientoCliente,
        String direccionCliente
        ) {

}
