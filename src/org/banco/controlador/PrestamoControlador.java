
package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.prestamo.PrestamoActualizarDto;
import org.banco.dto.prestamo.PrestamoCrearDto;
import org.banco.dto.prestamo.PrestamoDto;
import org.banco.servicio.PrestamoServicio;


public class PrestamoControlador {
    
    private final PrestamoServicio servicio;
    
    public PrestamoControlador(PrestamoServicio servicio) {
        this.servicio = servicio;
    }
    
    public Optional<PrestamoDto> crear(PrestamoCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<PrestamoDto> actualizar(Integer id, PrestamoActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<PrestamoDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<PrestamoDto> obtenerUno(Integer id) {
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
