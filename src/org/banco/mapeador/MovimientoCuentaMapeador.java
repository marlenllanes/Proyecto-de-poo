package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.movimientocuenta.MovimientoCuentaActualizarDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaCrearDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaDto;
import org.banco.entidad.MovimientoCuenta;
import org.banco.mapeador.api.ApiMapeador;

public final class MovimientoCuentaMapeador implements
        ApiMapeador<MovimientoCuenta, MovimientoCuentaCrearDto, MovimientoCuentaDto, MovimientoCuentaActualizarDto> {

    public static final MovimientoCuentaMapeador SINGLETON = new MovimientoCuentaMapeador();

    private MovimientoCuentaMapeador() {
    }

    @Override
    public MovimientoCuentaDto toDto(MovimientoCuenta entidad) {
        if (entidad == null) {
            return null;
        }
        return new MovimientoCuentaDto(
                entidad.getIdMovimientoCuenta(),
                CuentaBancariaMapeador.SINGLETON.toDto(entidad.getCuentaMovimientoCuenta()),
                entidad.getTipoMovimientoCuenta(),
                entidad.getValorMovimientoCuenta(),
                entidad.getFechaMovimientoCuenta(),
                entidad.getSaldoPosteriorMovimientoCuenta()
        );
    }

    @Override
    public MovimientoCuenta toEntityFromCrear(MovimientoCuentaCrearDto dto) {
        if (dto == null) {
            return null;
        }
        MovimientoCuenta entidad = new MovimientoCuenta();
        entidad.setTipoMovimientoCuenta(dto.tipoMovimientoCuenta());
        entidad.setValorMovimientoCuenta(dto.valorMovimientoCuenta());
        entidad.setFechaMovimientoCuenta(dto.fechaMovimientoCuenta());
        entidad.setSaldoPosteriorMovimientoCuenta(dto.saldoPosteriorMovimientoCuenta());
        return entidad;
    }

    @Override
    public MovimientoCuenta toEntityFromActualizar(MovimientoCuentaActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        MovimientoCuenta entidad = new MovimientoCuenta();
        entidad.setIdMovimientoCuenta(dto.idMovimientoCuenta());
        entidad.setTipoMovimientoCuenta(dto.tipoMovimientoCuenta());
        entidad.setValorMovimientoCuenta(dto.valorMovimientoCuenta());
        entidad.setFechaMovimientoCuenta(dto.fechaMovimientoCuenta());
        entidad.setSaldoPosteriorMovimientoCuenta(dto.saldoPosteriorMovimientoCuenta());
        return entidad;
    }

    @Override
    public List<MovimientoCuentaDto> toDtoList(List<MovimientoCuenta> entidades) {
        List<MovimientoCuentaDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (MovimientoCuenta entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
