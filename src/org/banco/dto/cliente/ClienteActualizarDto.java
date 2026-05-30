package org.banco.dto.cliente;

import java.time.OffsetDateTime;

public record ClienteActualizarDto(
        Integer idCliente,
        String nombreCliente,
        String documentoCliente,
        String correoCliente,
        String celularCliente,
        OffsetDateTime fechaNacimientoCliente,
        String direccionCliente
        ) {

}
