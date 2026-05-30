package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.movimientocuenta.MovimientoCuentaActualizarDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaCrearDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaDto;
import org.banco.entidad.CuentaBancaria;
import org.banco.entidad.MovimientoCuenta;
import org.banco.mapeador.MovimientoCuentaMapeador;
import org.banco.repositorio.CuentaBancariaRepositorio;
import org.banco.repositorio.MovimientoCuentaRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class MovimientoCuentaServicio implements ApiOperacionServicio
        <MovimientoCuentaCrearDto, MovimientoCuentaDto, MovimientoCuentaActualizarDto, Integer> {

    private final MovimientoCuentaRepositorio repositorio;
    private final CuentaBancariaRepositorio cuentaBancariaRepositorio;

    public MovimientoCuentaServicio(MovimientoCuentaRepositorio repo, CuentaBancariaRepositorio cuentaRepo) {
        this.repositorio = repo;
        this.cuentaBancariaRepositorio = cuentaRepo;
    }

    @Override
    public MovimientoCuentaDto insertInto(MovimientoCuentaCrearDto creacionDTO) {
        MovimientoCuenta entidad = MovimientoCuentaMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(creacionDTO.idCuenta());
        if (cuenta == null) {
            return null;
        }
        entidad.setCuentaMovimientoCuenta(cuenta);
        MovimientoCuenta nuevo = repositorio.save(entidad);
        return MovimientoCuentaMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public MovimientoCuentaDto updateSet(Integer id, MovimientoCuentaActualizarDto actualizarDto) {
        MovimientoCuenta entidad = MovimientoCuentaMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdMovimientoCuenta(id);
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(actualizarDto.idCuenta());
        if (cuenta == null) {
            return null;
        }
        entidad.setCuentaMovimientoCuenta(cuenta);
        MovimientoCuenta actualizado = repositorio.update(entidad);
        return MovimientoCuentaMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<MovimientoCuentaDto> selectFrom() {
        List<MovimientoCuenta> movimientos = repositorio.findAll();
        List<CuentaBancaria> cuentas = cuentaBancariaRepositorio.findAll();

        Map<Integer, CuentaBancaria> mapaCuentas = mapaCuentas(cuentas);

        movimientos = sinCuentaNula(movimientos);
        movimientos = sinCuentaInexistente(movimientos, mapaCuentas);
        hidratarCuenta(movimientos, mapaCuentas);

        return MovimientoCuentaMapeador.SINGLETON.toDtoList(movimientos);
    }

    @Override
    public MovimientoCuentaDto selectOne(Integer codigo) {
        MovimientoCuenta entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return MovimientoCuentaMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }

    private Map<Integer, CuentaBancaria> mapaCuentas(List<CuentaBancaria> cuentas) {
        Map<Integer, CuentaBancaria> mapa = new HashMap<>();
        for (CuentaBancaria c : cuentas) {
            mapa.put(c.getIdCuentaBancaria(), c);
        }
        return mapa;
    }

    private List<MovimientoCuenta> sinCuentaNula(List<MovimientoCuenta> movimientos) {
        List<MovimientoCuenta> resultado = new ArrayList<>();
        for (MovimientoCuenta movimiento : movimientos) {
            if (movimiento.getCuentaMovimientoCuenta() == null) {
                System.out.print("ERROR: MovimientoCuenta " + movimiento.getIdMovimientoCuenta());
                System.out.println(" cuenta nula, registro descartado");
                continue;
            }
            resultado.add(movimiento);
        }
        return resultado;
    }

    private List<MovimientoCuenta> sinCuentaInexistente(
            List<MovimientoCuenta> movimientos,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        List<MovimientoCuenta> resultado = new ArrayList<>();
        for (MovimientoCuenta movimiento : movimientos) {
            int idCuenta = movimiento.getCuentaMovimientoCuenta().getIdCuentaBancaria();
            if (!mapaCuentas.containsKey(idCuenta)) {
                System.out.print("ERROR: MovimientoCuenta " + movimiento.getIdMovimientoCuenta());
                System.out.println(" cuenta " + idCuenta + " no existe, registro descartado");
                continue;
            }
            resultado.add(movimiento);
        }
        return resultado;
    }

    private void hidratarCuenta(List<MovimientoCuenta> movimientos, Map<Integer, CuentaBancaria> mapaCuentas) {
        for (MovimientoCuenta movimiento : movimientos) {
            int idCuenta = movimiento.getCuentaMovimientoCuenta().getIdCuentaBancaria();
            movimiento.setCuentaMovimientoCuenta(mapaCuentas.get(idCuenta));
        }
    }

    private boolean hidratarSelectOne(MovimientoCuenta entidad) {
        if (entidad.getCuentaMovimientoCuenta() == null) {
            return false;
        }
        int idCuenta = entidad.getCuentaMovimientoCuenta().getIdCuentaBancaria();
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(idCuenta);
        if (cuenta == null) {
            return false;
        }
        entidad.setCuentaMovimientoCuenta(cuenta);
        return true;
    }
}
