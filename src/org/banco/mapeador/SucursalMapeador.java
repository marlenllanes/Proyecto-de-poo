package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.sucursal.SucursalActualizarDto;
import org.banco.dto.sucursal.SucursalCrearDto;
import org.banco.dto.sucursal.SucursalDto;
import org.banco.entidad.Sucursal;
import org.banco.mapeador.api.ApiMapeador;

public final class SucursalMapeador implements
        ApiMapeador<Sucursal, SucursalCrearDto, SucursalDto, SucursalActualizarDto> {

    public static final SucursalMapeador SINGLETON = new SucursalMapeador();

    private SucursalMapeador() {
    }

    @Override
    public SucursalDto toDto(Sucursal entidad) {
        if (entidad == null) {
            return null;
        }

        SucursalDto dto = new SucursalDto(
                entidad.getIdSucursal(),
                entidad.getNombreSucursal(),
                entidad.getDireccionSucursal(),
                entidad.getTelefonoSucursal()
        );

        return dto;
    }

    @Override
    public Sucursal toEntityFromCrear(SucursalCrearDto dto) {
        if (dto == null) {
            return null;
        }
        Sucursal entidad = new Sucursal();
        entidad.setNombreSucursal(dto.nombreSucursal());
        entidad.setDireccionSucursal(dto.direccionSucursal());
        entidad.setTelefonoSucursal(dto.telefonoSucursal());

        return entidad;
    }

    @Override
    public Sucursal toEntityFromActualizar(SucursalActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        Sucursal entidad = new Sucursal();
        entidad.setIdSucursal(dto.idSucursal());
        entidad.setNombreSucursal(dto.nombreSucursal());
        entidad.setDireccionSucursal(dto.direccionSucursal());
        entidad.setTelefonoSucursal(dto.telefonoSucursal());

        return entidad;
    }

    @Override
    public List<SucursalDto> toDtoList(List<Sucursal> entidades) {
     List<SucursalDto> arreglo = new ArrayList<>();

        if (entidades == null) {
            return arreglo;
        }
        for (Sucursal entidad : entidades) {
            SucursalDto dto = toDto(entidad);
            arreglo.add(dto);
        }

        return arreglo;
    }
}
