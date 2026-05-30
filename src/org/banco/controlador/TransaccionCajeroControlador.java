package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.transaccioncajero.TransaccionCajeroActualizarDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroCrearDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroDto;
import org.banco.servicio.TransaccionCajeroServicio;

public class TransaccionCajeroControlador {
    
    private final TransaccionCajeroServicio servicio;
    
    public TransaccionCajeroControlador(TransaccionCajeroServicio servicio) {
        this.servicio = servicio;
    }
    public Optional<TransaccionCajeroDto> crear(TransaccionCajeroCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<TransaccionCajeroDto> actualizar(Integer id, TransaccionCajeroActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<TransaccionCajeroDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<TransaccionCajeroDto> obtenerUno(Integer id) {
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

