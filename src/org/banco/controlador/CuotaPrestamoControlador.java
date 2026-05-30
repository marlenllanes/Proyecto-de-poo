package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.cuotaprestamo.CuotaPrestamoActualizarDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoCrearDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoDto;
import org.banco.servicio.CuotaPrestamoServicio;

public class CuotaPrestamoControlador {
    
    private final CuotaPrestamoServicio servicio;
    
    public CuotaPrestamoControlador(CuotaPrestamoServicio servicio) {
        this.servicio = servicio;
    }
    
   public Optional<CuotaPrestamoDto> crear(CuotaPrestamoCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<CuotaPrestamoDto> actualizar(Integer id, CuotaPrestamoActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<CuotaPrestamoDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<CuotaPrestamoDto> obtenerUno(Integer id) {
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
