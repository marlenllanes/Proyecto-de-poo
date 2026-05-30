package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cajero.CajeroActualizarDto;
import org.banco.dto.cajero.CajeroCrearDto;
import org.banco.dto.cajero.CajeroDto;
import org.banco.dto.sucursal.SucursalDto;
import org.banco.entidad.Cajero;
import org.banco.mapeador.api.ApiMapeador;

public final class CajeroMapeador implements
        ApiMapeador<Cajero, CajeroCrearDto, CajeroDto, CajeroActualizarDto> {

    public static final CajeroMapeador SINGLETON = new CajeroMapeador();

    private CajeroMapeador() {

    }

    @Override
    public CajeroDto toDto(Cajero entidad) {
        if (entidad == null) {
            return null;
        }

        SucursalDto sucursalDto = SucursalMapeador.SINGLETON.toDto(entidad.getSucursalCajero());

        CajeroDto dto = new CajeroDto(
                entidad.getIdCajero(),
                sucursalDto,
                entidad.getNombreCajero(),
                entidad.getTurnoCajero()
        );
        return dto;
    }

    @Override
    public Cajero toEntityFromCrear(CajeroCrearDto dto) {
        if (dto == null) {
            return null;
        }

        Cajero entidad = new Cajero();
        entidad.setNombreCajero(dto.nombreCajero());
        entidad.setTurnoCajero(dto.turnoCajero());

        return entidad;
    }

    @Override
    public Cajero toEntityFromActualizar(CajeroActualizarDto dto) {
        if (dto == null) {
            return null;
        }

        Cajero entidad = new Cajero();
        entidad.setIdCajero(dto.idCajero());
        entidad.setNombreCajero(dto.nombreCajero());
        entidad.setTurnoCajero(dto.turnoCajero());

        return entidad;
    }

    @Override
    public List<CajeroDto> toDtoList(List<Cajero> entidades) {
        List<CajeroDto> arreglo = new ArrayList<>();

        if (entidades == null) {
            return arreglo;
        }

        for (Cajero entidad : entidades) {
            CajeroDto dto = toDto(entidad);
            arreglo.add(dto);
        }
        return arreglo;
    }

}
