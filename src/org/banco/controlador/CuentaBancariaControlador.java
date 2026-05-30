package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.cuentabancaria.CuentaBancariaActualizarDto;
import org.banco.dto.cuentabancaria.CuentaBancariaCrearDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.servicio.CuentaBancariaServicio;

public class CuentaBancariaControlador {

    private final CuentaBancariaServicio servicio;

    public CuentaBancariaControlador(CuentaBancariaServicio servicio) {
        this.servicio = servicio;
    }

    public Optional<CuentaBancariaDto> crear(CuentaBancariaCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<CuentaBancariaDto> actualizar(Integer id, CuentaBancariaActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<CuentaBancariaDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<CuentaBancariaDto> obtenerUno(Integer id) {
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
