package org.banco.dto.cliente;

import java.time.OffsetDateTime;

public record ClienteCrearDto(
        String nombreCliente,
        String documentoCliente,
        String correoCliente,
        String celularCliente,
        OffsetDateTime fechaNacimientoCliente,
        String direccionCliente
        ) {

}
