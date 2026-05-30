package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.consumotarjeta.ConsumoTarjetaActualizarDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaCrearDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaDto;
import org.banco.entidad.ConsumoTarjeta;
import org.banco.entidad.Tarjeta;
import org.banco.mapeador.ConsumoTarjetaMapeador;
import org.banco.repositorio.ConsumoTarjetaRepositorio;
import org.banco.repositorio.TarjetaRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class ConsumoTarjetaServicio implements ApiOperacionServicio
        <ConsumoTarjetaCrearDto, ConsumoTarjetaDto, ConsumoTarjetaActualizarDto, Integer> {

    private final ConsumoTarjetaRepositorio repositorio;
    private final TarjetaRepositorio tarjetaRepositorio;

    public ConsumoTarjetaServicio(ConsumoTarjetaRepositorio repo, TarjetaRepositorio tarjetaRepo) {
        this.repositorio = repo;
        this.tarjetaRepositorio = tarjetaRepo;
    }

    @Override
    public ConsumoTarjetaDto insertInto(ConsumoTarjetaCrearDto creacionDTO) {
        ConsumoTarjeta entidad = ConsumoTarjetaMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Tarjeta tarjeta = tarjetaRepositorio.findById(creacionDTO.idTarjeta());
        if (tarjeta == null) {
            return null;
        }
        entidad.setTarjetaConsumoTarjeta(tarjeta);
        ConsumoTarjeta nuevo = repositorio.save(entidad);
        return ConsumoTarjetaMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public ConsumoTarjetaDto updateSet(Integer id, ConsumoTarjetaActualizarDto actualizarDto) {
        ConsumoTarjeta entidad = ConsumoTarjetaMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdConsumoTarjeta(id);
        Tarjeta tarjeta = tarjetaRepositorio.findById(actualizarDto.idTarjeta());
        if (tarjeta == null) {
            return null;
        }
        entidad.setTarjetaConsumoTarjeta(tarjeta);
        ConsumoTarjeta actualizado = repositorio.update(entidad);
        return ConsumoTarjetaMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<ConsumoTarjetaDto> selectFrom() {
        List<ConsumoTarjeta> consumos = repositorio.findAll();
        List<Tarjeta> tarjetas = tarjetaRepositorio.findAll();

        Map<Integer, Tarjeta> mapaTarjetas = mapaTarjetas(tarjetas);

        consumos = sinTarjetaNula(consumos);
        consumos = sinTarjetaInexistente(consumos, mapaTarjetas);
        hidratarTarjeta(consumos, mapaTarjetas);

        return ConsumoTarjetaMapeador.SINGLETON.toDtoList(consumos);
    }

    @Override
    public ConsumoTarjetaDto selectOne(Integer codigo) {
        ConsumoTarjeta entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return ConsumoTarjetaMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }

    private Map<Integer, Tarjeta> mapaTarjetas(List<Tarjeta> tarjetas) {
        Map<Integer, Tarjeta> mapa = new HashMap<>();
        for (Tarjeta t : tarjetas) {
            mapa.put(t.getIdTarjeta(), t);
        }
        return mapa;
    }

    private List<ConsumoTarjeta> sinTarjetaNula(List<ConsumoTarjeta> consumos) {
        List<ConsumoTarjeta> resultado = new ArrayList<>();
        for (ConsumoTarjeta consumo : consumos) {
            if (consumo.getTarjetaConsumoTarjeta() == null) {
                System.out.print("ERROR: ConsumoTarjeta " + consumo.getIdConsumoTarjeta());
                System.out.println(" tarjeta nula, registro descartado");
                continue;
            }
            resultado.add(consumo);
        }
        return resultado;
    }

    private List<ConsumoTarjeta> sinTarjetaInexistente(
            List<ConsumoTarjeta> consumos,
            Map<Integer, Tarjeta> mapaTarjetas) {
        List<ConsumoTarjeta> resultado = new ArrayList<>();
        for (ConsumoTarjeta consumo : consumos) {
            int idTarjeta = consumo.getTarjetaConsumoTarjeta().getIdTarjeta();
            if (!mapaTarjetas.containsKey(idTarjeta)) {
                System.out.print("ERROR: ConsumoTarjeta " + consumo.getIdConsumoTarjeta());
                System.out.println(" tarjeta " + idTarjeta + " no existe, registro descartado");
                continue;
            }
            resultado.add(consumo);
        }
        return resultado;
    }

    private void hidratarTarjeta(List<ConsumoTarjeta> consumos, Map<Integer, Tarjeta> mapaTarjetas) {
        for (ConsumoTarjeta consumo : consumos) {
            int idTarjeta = consumo.getTarjetaConsumoTarjeta().getIdTarjeta();
            consumo.setTarjetaConsumoTarjeta(mapaTarjetas.get(idTarjeta));
        }
    }

    private boolean hidratarSelectOne(ConsumoTarjeta entidad) {
        if (entidad.getTarjetaConsumoTarjeta() == null) {
            return false;
        }
        int idTarjeta = entidad.getTarjetaConsumoTarjeta().getIdTarjeta();
        Tarjeta tarjeta = tarjetaRepositorio.findById(idTarjeta);
        if (tarjeta == null) {
            return false;
        }
        entidad.setTarjetaConsumoTarjeta(tarjeta);
        return true;
    }
}
