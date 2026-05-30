package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.cliente.ClienteActualizarDto;
import org.banco.dto.cliente.ClienteCrearDto;
import org.banco.dto.cliente.ClienteDto;
import org.banco.servicio.ClienteServicio;

public class ClienteControlador {

    private final ClienteServicio servicio;

    public ClienteControlador(ClienteServicio servicio) {
        this.servicio = servicio;
    }

    public Optional<ClienteDto> crear(ClienteCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<ClienteDto> actualizar(Integer id, ClienteActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<ClienteDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<ClienteDto> obtenerUno(Integer id) {
        return Optional.ofNullable(servicio.selectOne(id));
    }

    public boolean eliminar(Integer id) {
        Boolean resultado = servicio.deleteFrom(id);
        return resultado != null && resultado;
    }

    public int contar() {
        return servicio.countRows();
    }

    public int ultimoSerial() {
        return servicio.lastSerial();
    }
}
