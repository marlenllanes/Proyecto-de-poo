
package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.consumotarjeta.ConsumoTarjetaActualizarDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaCrearDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaDto;
import org.banco.servicio.ConsumoTarjetaServicio;


public class ConsumoTarjetaControlador {
    
    private final ConsumoTarjetaServicio servicio;
    
    public ConsumoTarjetaControlador(ConsumoTarjetaServicio servicio) {
        this.servicio = servicio;
    }
    
    public Optional<ConsumoTarjetaDto> crear(ConsumoTarjetaCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<ConsumoTarjetaDto> actualizar(Integer id, ConsumoTarjetaActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<ConsumoTarjetaDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<ConsumoTarjetaDto> obtenerUno(Integer id) {
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
