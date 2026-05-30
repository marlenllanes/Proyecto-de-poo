package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.tarjeta.TarjetaActualizarDto;
import org.banco.dto.tarjeta.TarjetaCrearDto;
import org.banco.dto.tarjeta.TarjetaDto;
import org.banco.entidad.Cliente;
import org.banco.entidad.CuentaBancaria;
import org.banco.entidad.Tarjeta;
import org.banco.mapeador.TarjetaMapeador;
import org.banco.repositorio.ClienteRepositorio;
import org.banco.repositorio.CuentaBancariaRepositorio;
import org.banco.repositorio.TarjetaRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class TarjetaServicio implements ApiOperacionServicio
        <TarjetaCrearDto, TarjetaDto, TarjetaActualizarDto, Integer> {

    private final TarjetaRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final CuentaBancariaRepositorio cuentaBancariaRepositorio;

    public TarjetaServicio(
            TarjetaRepositorio repo,
            ClienteRepositorio clienteRepo,
            CuentaBancariaRepositorio cuentaRepo) {
        this.repositorio = repo;
        this.clienteRepositorio = clienteRepo;
        this.cuentaBancariaRepositorio = cuentaRepo;
    }

    @Override
    public TarjetaDto insertInto(TarjetaCrearDto creacionDTO) {
        Tarjeta entidad = TarjetaMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Cliente cliente = clienteRepositorio.findById(creacionDTO.idCliente());
        if (cliente == null) {
            return null;
        }
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(creacionDTO.idCuenta());
        if (cuenta == null) {
            return null;
        }
        entidad.setClienteTarjeta(cliente);
        entidad.setCuentaTarjeta(cuenta);
        Tarjeta nueva = repositorio.save(entidad);
        return TarjetaMapeador.SINGLETON.toDto(nueva);
    }

    @Override
    public TarjetaDto updateSet(Integer id, TarjetaActualizarDto actualizarDto) {
        Tarjeta entidad = TarjetaMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdTarjeta(id);
        Cliente cliente = clienteRepositorio.findById(actualizarDto.idCliente());
        if (cliente == null) {
            return null;
        }
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(actualizarDto.idCuenta());
        if (cuenta == null) {
            return null;
        }
        entidad.setClienteTarjeta(cliente);
        entidad.setCuentaTarjeta(cuenta);
        Tarjeta actualizada = repositorio.update(entidad);
        return TarjetaMapeador.SINGLETON.toDto(actualizada);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<TarjetaDto> selectFrom() {
        List<Tarjeta> tarjetas = repositorio.findAll();
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<CuentaBancaria> cuentas = cuentaBancariaRepositorio.findAll();

        Map<Integer, Cliente> mapaClientes = mapaClientes(clientes);
        Map<Integer, CuentaBancaria> mapaCuentas = mapaCuentas(cuentas);

        tarjetas = sinClienteNulo(tarjetas);
        tarjetas = sinClienteInexistente(tarjetas, mapaClientes);
        tarjetas = sinCuentaNula(tarjetas);
        tarjetas = sinCuentaInexistente(tarjetas, mapaCuentas);
        hidratarRelaciones(tarjetas, mapaClientes, mapaCuentas);

        return TarjetaMapeador.SINGLETON.toDtoList(tarjetas);
    }

    @Override
    public TarjetaDto selectOne(Integer codigo) {
        Tarjeta entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return TarjetaMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }

    private Map<Integer, Cliente> mapaClientes(List<Cliente> clientes) {
        Map<Integer, Cliente> mapa = new HashMap<>();
        for (Cliente c : clientes) {
            mapa.put(c.getIdCliente(), c);
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

    private List<Tarjeta> sinClienteNulo(List<Tarjeta> tarjetas) {
        List<Tarjeta> resultado = new ArrayList<>();
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getClienteTarjeta() == null) {
                System.out.print("ERROR: Tarjeta " + tarjeta.getIdTarjeta());
                System.out.println(" cliente nulo, registro descartado");
                continue;
            }
            resultado.add(tarjeta);
        }
        return resultado;
    }

    private List<Tarjeta> sinClienteInexistente(
            List<Tarjeta> tarjetas,
            Map<Integer, Cliente> mapaClientes) {
        List<Tarjeta> resultado = new ArrayList<>();
        for (Tarjeta tarjeta : tarjetas) {
            int idCliente = tarjeta.getClienteTarjeta().getIdCliente();
            if (!mapaClientes.containsKey(idCliente)) {
                System.out.print("ERROR: Tarjeta " + tarjeta.getIdTarjeta());
                System.out.println(" cliente " + idCliente + " no existe, registro descartado");
                continue;
            }
            resultado.add(tarjeta);
        }
        return resultado;
    }

    private List<Tarjeta> sinCuentaNula(List<Tarjeta> tarjetas) {
        List<Tarjeta> resultado = new ArrayList<>();
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getCuentaTarjeta() == null) {
                System.out.print("ERROR: Tarjeta " + tarjeta.getIdTarjeta());
                System.out.println(" cuenta nula, registro descartado");
                continue;
            }
            resultado.add(tarjeta);
        }
        return resultado;
    }

    private List<Tarjeta> sinCuentaInexistente(
            List<Tarjeta> tarjetas,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        List<Tarjeta> resultado = new ArrayList<>();
        for (Tarjeta tarjeta : tarjetas) {
            int idCuenta = tarjeta.getCuentaTarjeta().getIdCuentaBancaria();
            if (!mapaCuentas.containsKey(idCuenta)) {
                System.out.print("ERROR: Tarjeta " + tarjeta.getIdTarjeta());
                System.out.println(" cuenta " + idCuenta + " no existe, registro descartado");
                continue;
            }
            resultado.add(tarjeta);
        }
        return resultado;
    }

    private void hidratarRelaciones(
            List<Tarjeta> tarjetas,
            Map<Integer, Cliente> mapaClientes,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        for (Tarjeta tarjeta : tarjetas) {
            tarjeta.setClienteTarjeta(mapaClientes.get(tarjeta.getClienteTarjeta().getIdCliente()));
            tarjeta.setCuentaTarjeta(mapaCuentas.get(tarjeta.getCuentaTarjeta().getIdCuentaBancaria()));
        }
    }

    private boolean hidratarSelectOne(Tarjeta entidad) {
        if (entidad.getClienteTarjeta() == null || entidad.getCuentaTarjeta() == null) {
            return false;
        }
        Cliente cliente = clienteRepositorio.findById(entidad.getClienteTarjeta().getIdCliente());
        if (cliente == null) {
            return false;
        }
        CuentaBancaria cuenta = cuentaBancariaRepositorio.findById(entidad.getCuentaTarjeta().getIdCuentaBancaria());
        if (cuenta == null) {
            return false;
        }
        entidad.setClienteTarjeta(cliente);
        entidad.setCuentaTarjeta(cuenta);
        return true;
    }
}
