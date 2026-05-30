package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.transferencia.TransferenciaActualizarDto;
import org.banco.dto.transferencia.TransferenciaCrearDto;
import org.banco.dto.transferencia.TransferenciaDto;
import org.banco.dto.transferencia.TransferenciaIdentificadorDto;
import org.banco.servicio.TransferenciaServicio;

public class TransferenciaControlador {

    private final TransferenciaServicio servicio;

    public TransferenciaControlador(TransferenciaServicio servicio) {
        this.servicio = servicio;
    }

    public Optional<TransferenciaCrearDto> crear(TransferenciaCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<TransferenciaActualizarDto> actualizar(
            TransferenciaIdentificadorDto id,
            TransferenciaActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public boolean eliminar(TransferenciaIdentificadorDto id) {
        Boolean resultado = servicio.deleteFrom(id);
        return resultado != null && resultado;
    }

    public List<TransferenciaDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<TransferenciaDto> obtenerUno(TransferenciaIdentificadorDto id) {
        return Optional.ofNullable(servicio.selectOne(id));
    }

    public int contar() {
        return servicio.countRows();
    }
}
