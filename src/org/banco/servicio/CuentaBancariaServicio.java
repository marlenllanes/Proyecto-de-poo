package org.banco.servicio;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cuentabancaria.CuentaBancariaActualizarDto;
import org.banco.dto.cuentabancaria.CuentaBancariaCrearDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.entidad.Cliente;
import org.banco.entidad.CuentaBancaria;
import org.banco.mapeador.CuentaBancariaMapeador;
import org.banco.repositorio.ClienteRepositorio;
import org.banco.repositorio.CuentaBancariaRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

import java.util.HashMap;
import java.util.Map;

public class CuentaBancariaServicio implements ApiOperacionServicio
        <CuentaBancariaCrearDto, CuentaBancariaDto, CuentaBancariaActualizarDto, Integer> {

    private final CuentaBancariaRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;

    public CuentaBancariaServicio(CuentaBancariaRepositorio repo, ClienteRepositorio clienteRepo) {
        this.repositorio = repo;
        this.clienteRepositorio = clienteRepo;
    }

    @Override
    public CuentaBancariaDto insertInto(CuentaBancariaCrearDto creacionDTO) {
        CuentaBancaria entidad = CuentaBancariaMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Cliente cliente = clienteRepositorio.findById(creacionDTO.idCliente());
        if (cliente == null) {
            return null;
        }
        entidad.setClienteCuentaBancaria(cliente);
        CuentaBancaria nueva = repositorio.save(entidad);
        return CuentaBancariaMapeador.SINGLETON.toDto(nueva);
    }

    @Override
    public CuentaBancariaDto updateSet(Integer id, CuentaBancariaActualizarDto actualizarDto) {
        CuentaBancaria entidad = CuentaBancariaMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdCuentaBancaria(id);
        Cliente cliente = clienteRepositorio.findById(actualizarDto.idCliente());
        if (cliente == null) {
            return null;
        }
        entidad.setClienteCuentaBancaria(cliente);
        CuentaBancaria actualizada = repositorio.update(entidad);
        return CuentaBancariaMapeador.SINGLETON.toDto(actualizada);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<CuentaBancariaDto> selectFrom() {
        List<CuentaBancaria> cuentas = repositorio.findAll();
        List<Cliente> clientes = clienteRepositorio.findAll();

        Map<Integer, Cliente> mapaClientes = mapaClientes(clientes);

        cuentas = sinClienteNulo(cuentas);
        cuentas = sinClienteInexistente(cuentas, mapaClientes);
        hidratarCliente(cuentas, mapaClientes);

        return CuentaBancariaMapeador.SINGLETON.toDtoList(cuentas);
    }

    @Override
    public CuentaBancariaDto selectOne(Integer codigo) {
        CuentaBancaria entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return CuentaBancariaMapeador.SINGLETON.toDto(entidad);
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

    private List<CuentaBancaria> sinClienteNulo(List<CuentaBancaria> cuentas) {
        List<CuentaBancaria> resultado = new ArrayList<>();
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getClienteCuentaBancaria() == null) {
                System.out.print("ERROR: CuentaBancaria " + cuenta.getIdCuentaBancaria());
                System.out.println(" cliente nulo, registro descartado");
                continue;
            }
            resultado.add(cuenta);
        }
        return resultado;
    }

    private List<CuentaBancaria> sinClienteInexistente(
            List<CuentaBancaria> cuentas,
            Map<Integer, Cliente> mapaClientes) {
        List<CuentaBancaria> resultado = new ArrayList<>();
        for (CuentaBancaria cuenta : cuentas) {
            int idCliente = cuenta.getClienteCuentaBancaria().getIdCliente();
            if (!mapaClientes.containsKey(idCliente)) {
                System.out.print("ERROR: CuentaBancaria " + cuenta.getIdCuentaBancaria());
                System.out.println(" cliente " + idCliente + " no existe, registro descartado");
                continue;
            }
            resultado.add(cuenta);
        }
        return resultado;
    }

    private void hidratarCliente(List<CuentaBancaria> cuentas, Map<Integer, Cliente> mapaClientes) {
        for (CuentaBancaria cuenta : cuentas) {
            int idCliente = cuenta.getClienteCuentaBancaria().getIdCliente();
            cuenta.setClienteCuentaBancaria(mapaClientes.get(idCliente));
        }
    }

    private boolean hidratarSelectOne(CuentaBancaria entidad) {
        if (entidad.getClienteCuentaBancaria() == null) {
            return false;
        }
        int idCliente = entidad.getClienteCuentaBancaria().getIdCliente();
        Cliente cliente = clienteRepositorio.findById(idCliente);
        if (cliente == null) {
            return false;
        }
        entidad.setClienteCuentaBancaria(cliente);
        return true;
    }
}
