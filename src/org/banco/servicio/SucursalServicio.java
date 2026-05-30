package org.banco.servicio;

import java.util.List;
import org.banco.dto.sucursal.SucursalActualizarDto;
import org.banco.dto.sucursal.SucursalCrearDto;
import org.banco.dto.sucursal.SucursalDto;
import org.banco.entidad.Sucursal;
import org.banco.mapeador.SucursalMapeador;
import org.banco.repositorio.SucursalRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class SucursalServicio implements ApiOperacionServicio
        <SucursalCrearDto, SucursalDto, SucursalActualizarDto, Integer> {

    private final SucursalRepositorio repositorio;

    public SucursalServicio(SucursalRepositorio repo) {
        this.repositorio = repo;
    }

    @Override
    public SucursalDto insertInto(SucursalCrearDto creacionDTO) {
        Sucursal entidad = SucursalMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Sucursal nueva = repositorio.save(entidad);
        return SucursalMapeador.SINGLETON.toDto(nueva);
    }

    @Override
    public SucursalDto updateSet(Integer id, SucursalActualizarDto actualizarDto) {
        Sucursal entidad = SucursalMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdSucursal(id);
        Sucursal actualizada = repositorio.update(entidad);
        return SucursalMapeador.SINGLETON.toDto(actualizada);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<SucursalDto> selectFrom() {
        List<Sucursal> sucursales = repositorio.findAll();
        return SucursalMapeador.SINGLETON.toDtoList(sucursales);
    }

    @Override
    public SucursalDto selectOne(Integer codigo) {
        Sucursal entidad = repositorio.findById(codigo);
        return SucursalMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }
}
