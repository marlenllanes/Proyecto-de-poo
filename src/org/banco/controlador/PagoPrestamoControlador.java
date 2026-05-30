package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.pagoprestamo.PagoPrestamoActualizarDto;
import org.banco.dto.pagoprestamo.PagoPrestamoCrearDto;
import org.banco.dto.pagoprestamo.PagoPrestamoDto;
import org.banco.servicio.PagoPrestamoServicio;

public class PagoPrestamoControlador {
    
    private final PagoPrestamoServicio servicio;
    
    public PagoPrestamoControlador(PagoPrestamoServicio servicio) {
        this.servicio = servicio;
    }

 public Optional<PagoPrestamoDto> crear(PagoPrestamoCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<PagoPrestamoDto> actualizar(Integer id, PagoPrestamoActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<PagoPrestamoDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<PagoPrestamoDto> obtenerUno(Integer id) {
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
