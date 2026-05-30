package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.tarjeta.TarjetaActualizarDto;
import org.banco.dto.tarjeta.TarjetaCrearDto;
import org.banco.dto.tarjeta.TarjetaDto;
import org.banco.servicio.TarjetaServicio;

public class TarjetaControlador {
    
    private final TarjetaServicio servicio;
    
    public TarjetaControlador(TarjetaServicio servicio) {
        this.servicio = servicio;
    }
    
     public Optional<TarjetaDto> crear(TarjetaCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<TarjetaDto> actualizar(Integer id, TarjetaActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<TarjetaDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<TarjetaDto> obtenerUno(Integer id) {
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
