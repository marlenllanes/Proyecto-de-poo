package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.transaccioncajero.TransaccionCajeroActualizarDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroCrearDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroDto;
import org.banco.entidad.Cajero;
import org.banco.entidad.CuentaBancaria;
import org.banco.entidad.TransaccionCajero;
import org.banco.mapeador.TransaccionCajeroMapeador;
import org.banco.repositorio.CajeroRepositorio;
import org.banco.repositorio.CuentaBancariaRepositorio;
import org.banco.repositorio.TransaccionCajeroRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class TransaccionCajeroServicio implements ApiOperacionServicio
        <TransaccionCajeroCrearDto, TransaccionCajeroDto, TransaccionCajeroActualizarDto, Integer> {

    private final TransaccionCajeroRepositorio repositorio;
    private final CajeroRepositorio cajeroRepositorio;
    private final CuentaBancariaRepositorio cuentaBancariaRepositorio;

    public TransaccionCajeroServicio(
            TransaccionCajeroRepositorio repo,
            CajeroRepositorio cajeroRepo,
            CuentaBancariaRepositorio cuentaRepo) {
        this.repositorio = repo;
        this.cajeroRepositorio = cajeroRepo;
        this.cuentaBancariaRepositorio = cuentaRepo;
    }

    @Override
    public TransaccionCajeroDto insertInto(TransaccionCajeroCrearDto creacionDTO) {
        TransaccionCajero entidad = TransaccionCajeroMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Cajero cajero = cajeroRepositorio.findById(creacionDTO.idCajero());
        if (cajero == null) {
            return null;
        }
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(creacionDTO.idCuenta());
        if (cuenta == null) {
            return null;
        }
        entidad.setCajeroTransaccionCajero(cajero);
        entidad.setCuentaTransaccionCajero(cuenta);
        TransaccionCajero nueva = repositorio.save(entidad);
        return TransaccionCajeroMapeador.SINGLETON.toDto(nueva);
    }

    @Override
    public TransaccionCajeroDto updateSet(Integer id, TransaccionCajeroActualizarDto actualizarDto) {
        TransaccionCajero entidad = TransaccionCajeroMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdTransaccionCajero(id);
        Cajero cajero = cajeroRepositorio.findById(actualizarDto.idCajero());
        if (cajero == null) {
            return null;
        }
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(actualizarDto.idCuenta());
        if (cuenta == null) {
            return null;
        }
        entidad.setCajeroTransaccionCajero(cajero);
        entidad.setCuentaTransaccionCajero(cuenta);
        TransaccionCajero actualizada = repositorio.update(entidad);
        return TransaccionCajeroMapeador.SINGLETON.toDto(actualizada);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<TransaccionCajeroDto> selectFrom() {
        List<TransaccionCajero> transacciones = repositorio.findAll();
        List<Cajero> cajeros = cajeroRepositorio.findAll();
        List<CuentaBancaria> cuentas = cuentaBancariaRepositorio.findAll();

        Map<Integer, Cajero> mapaCajeros = mapaCajeros(cajeros);
        Map<Integer, CuentaBancaria> mapaCuentas = mapaCuentas(cuentas);

        transacciones = sinCajeroNulo(transacciones);
        transacciones = sinCajeroInexistente(transacciones, mapaCajeros);
        transacciones = sinCuentaNula(transacciones);
        transacciones = sinCuentaInexistente(transacciones, mapaCuentas);
        hidratarRelaciones(transacciones, mapaCajeros, mapaCuentas);

        return TransaccionCajeroMapeador.SINGLETON.toDtoList(transacciones);
    }

    @Override
    public TransaccionCajeroDto selectOne(Integer codigo) {
        TransaccionCajero entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return TransaccionCajeroMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }

    private Map<Integer, Cajero> mapaCajeros(List<Cajero> cajeros) {
        Map<Integer, Cajero> mapa = new HashMap<>();
        for (Cajero c : cajeros) {
            mapa.put(c.getIdCajero(), c);
        }
        return mapa;
    }

    private Map<Integer, CuentaBancaria> mapaCuentas(List<CuentaBancaria> cuentas) {
        Map<Integer, CuentaBancaria> mapa = new HashMap<>();
        for (CuentaBancaria c : cuentas) {
            mapa.put(c.getIdCuentaBancaria(), c);
        }
        return mapa;
    }

    private List<TransaccionCajero> sinCajeroNulo(List<TransaccionCajero> transacciones) {
        List<TransaccionCajero> resultado = new ArrayList<>();
        for (TransaccionCajero t : transacciones) {
            if (t.getCajeroTransaccionCajero() == null) {
                System.out.print("ERROR: TransaccionCajero " + t.getIdTransaccionCajero());
                System.out.println(" cajero nulo, registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private List<TransaccionCajero> sinCajeroInexistente(
            List<TransaccionCajero> transacciones,
            Map<Integer, Cajero> mapaCajeros) {
        List<TransaccionCajero> resultado = new ArrayList<>();
        for (TransaccionCajero t : transacciones) {
            int idCajero = t.getCajeroTransaccionCajero().getIdCajero();
            if (!mapaCajeros.containsKey(idCajero)) {
                System.out.print("ERROR: TransaccionCajero " + t.getIdTransaccionCajero());
                System.out.println(" cajero " + idCajero + " no existe, registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private List<TransaccionCajero> sinCuentaNula(List<TransaccionCajero> transacciones) {
        List<TransaccionCajero> resultado = new ArrayList<>();
        for (TransaccionCajero t : transacciones) {
            if (t.getCuentaTransaccionCajero() == null) {
                System.out.print("ERROR: TransaccionCajero " + t.getIdTransaccionCajero());
                System.out.println(" cuenta nula, registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private List<TransaccionCajero> sinCuentaInexistente(
            List<TransaccionCajero> transacciones,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        List<TransaccionCajero> resultado = new ArrayList<>();
        for (TransaccionCajero t : transacciones) {
            int idCuenta = t.getCuentaTransaccionCajero().getIdCuentaBancaria();
            if (!mapaCuentas.containsKey(idCuenta)) {
                System.out.print("ERROR: TransaccionCajero " + t.getIdTransaccionCajero());
                System.out.println(" cuenta " + idCuenta + " no existe, registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private void hidratarRelaciones(
            List<TransaccionCajero> transacciones,
            Map<Integer, Cajero> mapaCajeros,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        for (TransaccionCajero t : transacciones) {
            t.setCajeroTransaccionCajero(mapaCajeros.get(t.getCajeroTransaccionCajero().getIdCajero()));
            t.setCuentaTransaccionCajero(mapaCuentas.get(t.getCuentaTransaccionCajero().getIdCuentaBancaria()));
        }
    }

    private boolean hidratarSelectOne(TransaccionCajero entidad) {
        if (entidad.getCajeroTransaccionCajero() == null || entidad.getCuentaTransaccionCajero() == null) {
            return false;
        }
        Cajero cajero = cajeroRepositorio.findById(entidad.getCajeroTransaccionCajero().getIdCajero());
        if (cajero == null) {
            return false;
        }
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(entidad.getCuentaTransaccionCajero().getIdCuentaBancaria());
        if (cuenta == null) {
            return false;
        }
        entidad.setCajeroTransaccionCajero(cajero);
        entidad.setCuentaTransaccionCajero(cuenta);
        return true;
    }
}
