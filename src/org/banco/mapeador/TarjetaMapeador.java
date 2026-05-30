package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.tarjeta.TarjetaActualizarDto;
import org.banco.dto.tarjeta.TarjetaCrearDto;
import org.banco.dto.tarjeta.TarjetaDto;
import org.banco.entidad.Tarjeta;
import org.banco.mapeador.api.ApiMapeador;

public final class TarjetaMapeador implements
        ApiMapeador<Tarjeta, TarjetaCrearDto, TarjetaDto, TarjetaActualizarDto> {

    public static final TarjetaMapeador SINGLETON = new TarjetaMapeador();

    private TarjetaMapeador() {
    }

@Override
public TarjetaDto toDto(Tarjeta entidad) {
    if (entidad == null) {
        return null;
    }
    return new TarjetaDto(
            entidad.getIdTarjeta(),
            entidad.getNumeroTarjeta(),
            entidad.getTipoTarjeta(),
            entidad.getFechaExpedicionTarjeta(),
            entidad.getFechaVencimientoTarjeta(),
            entidad.getEstadoTarjeta(),
            ClienteMapeador.SINGLETON.toDto(entidad.getClienteTarjeta()),
            CuentaBancariaMapeador.SINGLETON.toDto(entidad.getCuentaTarjeta())
    );
}

    @Override
    public Tarjeta toEntityFromCrear(TarjetaCrearDto dto) {
        if (dto == null) {
            return null;
        }
        Tarjeta entidad = new Tarjeta();
        entidad.setNumeroTarjeta(dto.numeroTarjeta());
        entidad.setTipoTarjeta(dto.tipoTarjeta());
        entidad.setFechaExpedicionTarjeta(dto.fechaExpedicionTarjeta());
        entidad.setFechaVencimientoTarjeta(dto.fechaVencimientoTarjeta());
        entidad.setEstadoTarjeta(dto.estadoTarjeta());
        return entidad;
    }

    @Override
    public Tarjeta toEntityFromActualizar(TarjetaActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        Tarjeta entidad = new Tarjeta();
        entidad.setIdTarjeta(dto.idTarjeta());
        entidad.setNumeroTarjeta(dto.numeroTarjeta());
        entidad.setTipoTarjeta(dto.tipoTarjeta());
        entidad.setFechaExpedicionTarjeta(dto.fechaExpedicionTarjeta());
        entidad.setFechaVencimientoTarjeta(dto.fechaVencimientoTarjeta());
        entidad.setEstadoTarjeta(dto.estadoTarjeta());
        return entidad;
    }

    @Override
    public List<TarjetaDto> toDtoList(List<Tarjeta> entidades) {
        List<TarjetaDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (Tarjeta entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
