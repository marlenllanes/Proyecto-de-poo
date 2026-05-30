package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.transaccioncajero.TransaccionCajeroActualizarDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroCrearDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroDto;
import org.banco.entidad.TransaccionCajero;
import org.banco.mapeador.api.ApiMapeador;

public final class TransaccionCajeroMapeador implements
        ApiMapeador<TransaccionCajero, TransaccionCajeroCrearDto, TransaccionCajeroDto, TransaccionCajeroActualizarDto> {

    public static final TransaccionCajeroMapeador SINGLETON = new TransaccionCajeroMapeador();

    private TransaccionCajeroMapeador() {
    }

    @Override
    public TransaccionCajeroDto toDto(TransaccionCajero entidad) {
        if (entidad == null) {
            return null;
        }
        return new TransaccionCajeroDto(
                entidad.getIdTransaccionCajero(),
                CajeroMapeador.SINGLETON.toDto(entidad.getCajeroTransaccionCajero()),
                CuentaBancariaMapeador.SINGLETON.toDto(entidad.getCuentaTransaccionCajero()),
                entidad.getTipoTransaccionCajero(),
                entidad.getValorTransaccionCajero(),
                entidad.getFechaTransaccionCajero()
        );
    }

    @Override
    public TransaccionCajero toEntityFromCrear(TransaccionCajeroCrearDto dto) {
        if (dto == null) {
            return null;
        }
        TransaccionCajero entidad = new TransaccionCajero();
        entidad.setTipoTransaccionCajero(dto.tipoTransaccionCajero());
        entidad.setValorTransaccionCajero(dto.valorTransaccionCajero());
        entidad.setFechaTransaccionCajero(dto.fechaTransaccionCajero());
        return entidad;
    }

    @Override
    public TransaccionCajero toEntityFromActualizar(TransaccionCajeroActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        TransaccionCajero entidad = new TransaccionCajero();
        entidad.setIdTransaccionCajero(dto.idTransaccionCajero());
        entidad.setTipoTransaccionCajero(dto.tipoTransaccionCajero());
        entidad.setValorTransaccionCajero(dto.valorTransaccionCajero());
        entidad.setFechaTransaccionCajero(dto.fechaTransaccionCajero());
        return entidad;
    }

    @Override
    public List<TransaccionCajeroDto> toDtoList(List<TransaccionCajero> entidades) {
        List<TransaccionCajeroDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (TransaccionCajero entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
