package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.cajero.CajeroActualizarDto;
import org.banco.dto.cajero.CajeroCrearDto;
import org.banco.dto.cajero.CajeroDto;
import org.banco.entidad.Cajero;
import org.banco.entidad.Sucursal;
import org.banco.mapeador.CajeroMapeador;
import org.banco.repositorio.CajeroRepositorio;
import org.banco.repositorio.SucursalRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class CajeroServicio implements ApiOperacionServicio
        <CajeroCrearDto, CajeroDto, CajeroActualizarDto, Integer> {

    private final CajeroRepositorio repositorio;
    private final SucursalRepositorio sucursalRepositorio;

    public CajeroServicio(CajeroRepositorio repo, SucursalRepositorio sucursalRepo) {
        this.repositorio = repo;
        this.sucursalRepositorio = sucursalRepo;
    }

    @Override
    public CajeroDto insertInto(CajeroCrearDto creacionDTO) {
        Cajero entidad = CajeroMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Sucursal sucursal = sucursalRepositorio.findById(creacionDTO.idSucursal());
        if (sucursal == null) {
            return null;
        }
        entidad.setSucursalCajero(sucursal);
        Cajero nuevo = repositorio.save(entidad);
        return CajeroMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public CajeroDto updateSet(Integer id, CajeroActualizarDto actualizarDto) {
        Cajero entidad = CajeroMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdCajero(id);
        Sucursal sucursal = sucursalRepositorio.findById(actualizarDto.idSucursal());
        if (sucursal == null) {
            return null;
        }
        entidad.setSucursalCajero(sucursal);
        Cajero actualizado = repositorio.update(entidad);
        return CajeroMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

   @Override
    public List<CajeroDto> selectFrom() {
        List<Cajero> cajeros = repositorio.findAll();
        List<Sucursal> sucursales = sucursalRepositorio.findAll();

        Map<Integer, Sucursal> mapaSucursales = mapaSucursales(sucursales);

        cajeros = sinSucursalNula(cajeros);
        cajeros = sinSucursalInexistente(cajeros, mapaSucursales);
        hidratarSucursal(cajeros, mapaSucursales);

        return CajeroMapeador.SINGLETON.toDtoList(cajeros);
    }

    @Override
    public CajeroDto selectOne(Integer codigo) {
        Cajero entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return CajeroMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }

    private Map<Integer, Sucursal> mapaSucursales(List<Sucursal> sucursales) {
        Map<Integer, Sucursal> mapa = new HashMap<>();
        for (Sucursal s : sucursales) {
            mapa.put(s.getIdSucursal(), s);
        }
        return mapa;
    }

    private List<Cajero> sinSucursalNula(List<Cajero> cajeros) {
        List<Cajero> resultado = new ArrayList<>();
        for (Cajero cajero : cajeros) {
            if (cajero.getSucursalCajero() == null) {
                System.out.print("ERROR: Cajero " + cajero.getIdCajero());
                System.out.println(" sucursal nula, registro descartado");
                continue;
            }
            resultado.add(cajero);
        }
        return resultado;
    }

    private List<Cajero> sinSucursalInexistente(
            List<Cajero> cajeros,
            Map<Integer, Sucursal> mapaSucursales) {
        List<Cajero> resultado = new ArrayList<>();
        for (Cajero cajero : cajeros) {
            int idSucursal = cajero.getSucursalCajero().getIdSucursal();
            if (!mapaSucursales.containsKey(idSucursal)) {
                System.out.print("ERROR: Cajero " + cajero.getIdCajero());
                System.out.println(" sucursal " + idSucursal + " no existe, registro descartado");
                continue;
            }
            resultado.add(cajero);
        }
        return resultado;
    }

    private void hidratarSucursal(List<Cajero> cajeros, Map<Integer, Sucursal> mapaSucursales) {
        for (Cajero cajero : cajeros) {
            int idSucursal = cajero.getSucursalCajero().getIdSucursal();
            cajero.setSucursalCajero(mapaSucursales.get(idSucursal));
        }
    }

    private boolean hidratarSelectOne(Cajero entidad) {
        if (entidad.getSucursalCajero() == null) {
            return false;
        }
        int idSucursal = entidad.getSucursalCajero().getIdSucursal();
        Sucursal sucursal = sucursalRepositorio.findById(idSucursal);
        if (sucursal == null) {
            return false;
        }
        entidad.setSucursalCajero(sucursal);
        return true;
    }
}
