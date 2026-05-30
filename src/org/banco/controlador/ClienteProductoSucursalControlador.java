package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalActualizarDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalCrearDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalIdentificadorDto;
import org.banco.servicio.ClienteProductoSucursalServicio;

public class ClienteProductoSucursalControlador {

    private final ClienteProductoSucursalServicio servicio;
    
    public ClienteProductoSucursalControlador(ClienteProductoSucursalServicio servicio) {
        this.servicio = servicio;
    }
   public Optional<ClienteProductoSucursalCrearDto> crear(ClienteProductoSucursalCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<ClienteProductoSucursalActualizarDto> actualizar(
            ClienteProductoSucursalIdentificadorDto id,
            ClienteProductoSucursalActualizarDto dto
    ) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public boolean eliminar(ClienteProductoSucursalIdentificadorDto id) {
        Boolean resultado = servicio.deleteFrom(id);
        return resultado != null && resultado;
    }

    public List<ClienteProductoSucursalDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<ClienteProductoSucursalDto> obtenerUno(ClienteProductoSucursalIdentificadorDto id) {
        return Optional.ofNullable(servicio.selectOne(id));
    }

    public int contar() {
        return servicio.countRows();
    }
}
