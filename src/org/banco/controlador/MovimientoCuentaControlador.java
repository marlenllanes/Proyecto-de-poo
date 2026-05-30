
package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.movimientocuenta.MovimientoCuentaActualizarDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaCrearDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaDto;
import org.banco.servicio.MovimientoCuentaServicio;

public class MovimientoCuentaControlador {
    
    private final MovimientoCuentaServicio servicio;
    
    public MovimientoCuentaControlador(MovimientoCuentaServicio servicio) {
        this.servicio = servicio;
    }
    public Optional<MovimientoCuentaDto> crear(MovimientoCuentaCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<MovimientoCuentaDto> actualizar(Integer id, MovimientoCuentaActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<MovimientoCuentaDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<MovimientoCuentaDto> obtenerUno(Integer id) {
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
