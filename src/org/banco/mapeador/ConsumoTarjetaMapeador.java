package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.consumotarjeta.ConsumoTarjetaActualizarDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaCrearDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaDto;
import org.banco.entidad.ConsumoTarjeta;
import org.banco.mapeador.api.ApiMapeador;

public final class ConsumoTarjetaMapeador implements
        ApiMapeador<ConsumoTarjeta, ConsumoTarjetaCrearDto, ConsumoTarjetaDto, ConsumoTarjetaActualizarDto> {

    public static final ConsumoTarjetaMapeador SINGLETON = new ConsumoTarjetaMapeador();

    private ConsumoTarjetaMapeador() {
    }

    @Override
    public ConsumoTarjetaDto toDto(ConsumoTarjeta entidad) {
        if (entidad == null) {
            return null;
        }
        return new ConsumoTarjetaDto(
                entidad.getIdConsumoTarjeta(),
                TarjetaMapeador.SINGLETON.toDto(entidad.getTarjetaConsumoTarjeta()),
                entidad.getFechaConsumoTarjeta(),
                entidad.getEstablecimientoConsumoTarjeta(),
                entidad.getValorConsumoTarjeta(),
                entidad.getCuotaConsumoTarjeta()
        );
    }

    @Override
    public ConsumoTarjeta toEntityFromCrear(ConsumoTarjetaCrearDto dto) {
        if (dto == null) {
            return null;
        }
        ConsumoTarjeta entidad = new ConsumoTarjeta();
        entidad.setFechaConsumoTarjeta(dto.fechaConsumoTarjeta());
        entidad.setEstablecimientoConsumoTarjeta(dto.establecimientoConsumoTarjeta());
        entidad.setValorConsumoTarjeta(dto.valorConsumoTarjeta());
        entidad.setCuotaConsumoTarjeta(dto.cuotaConsumoTarjeta());
        return entidad;
    }

    @Override
    public ConsumoTarjeta toEntityFromActualizar(ConsumoTarjetaActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        ConsumoTarjeta entidad = new ConsumoTarjeta();
        entidad.setIdConsumoTarjeta(dto.idConsumoTarjeta());
        entidad.setFechaConsumoTarjeta(dto.fechaConsumoTarjeta());
        entidad.setEstablecimientoConsumoTarjeta(dto.establecimientoConsumoTarjeta());
        entidad.setValorConsumoTarjeta(dto.valorConsumoTarjeta());
        entidad.setCuotaConsumoTarjeta(dto.cuotaConsumoTarjeta());
        return entidad;
    }

    @Override
    public List<ConsumoTarjetaDto> toDtoList(List<ConsumoTarjeta> entidades) {
        List<ConsumoTarjetaDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (ConsumoTarjeta entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}