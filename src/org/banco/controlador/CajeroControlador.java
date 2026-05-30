package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.cajero.CajeroActualizarDto;
import org.banco.dto.cajero.CajeroCrearDto;
import org.banco.dto.cajero.CajeroDto;
import org.banco.servicio.CajeroServicio;

public class CajeroControlador {
    
    private final CajeroServicio servicio;
    
    public CajeroControlador(CajeroServicio servicio) {
        this.servicio = servicio;
    }
public Optional<CajeroDto> crear(CajeroCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<CajeroDto> actualizar(Integer id, CajeroActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<CajeroDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<CajeroDto> obtenerUno(Integer id) {
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
