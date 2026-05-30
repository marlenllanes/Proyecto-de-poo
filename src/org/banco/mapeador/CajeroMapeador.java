package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cajero.CajeroActualizarDto;
import org.banco.dto.cajero.CajeroCrearDto;
import org.banco.dto.cajero.CajeroDto;
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
    return new CajeroDto(
            entidad.getIdCajero(), 
            SucursalMapeador.SINGLETON.toDto(entidad.getSucursalCajero()),            
            entidad.getNombreCajero(),
            entidad.getTurnoCajero()
    );
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
