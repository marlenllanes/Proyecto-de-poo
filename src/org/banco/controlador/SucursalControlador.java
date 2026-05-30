
package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.sucursal.SucursalActualizarDto;
import org.banco.dto.sucursal.SucursalCrearDto;
import org.banco.dto.sucursal.SucursalDto;
import org.banco.servicio.SucursalServicio;


public class SucursalControlador {
    
    private final SucursalServicio servicio;
    
    public SucursalControlador(SucursalServicio servicio) {
        this.servicio = servicio;
    }
    public Optional<SucursalDto> crear(SucursalCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<SucursalDto> actualizar(Integer id, SucursalActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<SucursalDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<SucursalDto> obtenerUno(Integer id) {
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
