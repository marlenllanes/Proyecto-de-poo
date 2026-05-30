package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.prestamo.PrestamoActualizarDto;
import org.banco.dto.prestamo.PrestamoCrearDto;
import org.banco.dto.prestamo.PrestamoDto;
import org.banco.entidad.Cliente;
import org.banco.entidad.Prestamo;
import org.banco.mapeador.PrestamoMapeador;
import org.banco.repositorio.ClienteRepositorio;
import org.banco.repositorio.PrestamoRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class PrestamoServicio implements ApiOperacionServicio
        <PrestamoCrearDto, PrestamoDto, PrestamoActualizarDto, Integer> {

    private final PrestamoRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;

    public PrestamoServicio(PrestamoRepositorio repo, ClienteRepositorio clienteRepo) {
        this.repositorio = repo;
        this.clienteRepositorio = clienteRepo;
    }

    @Override
    public PrestamoDto insertInto(PrestamoCrearDto creacionDTO) {
        Prestamo entidad = PrestamoMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Cliente cliente = clienteRepositorio.findById(creacionDTO.idCliente());
        if (cliente == null) {
            return null;
        }
        entidad.setClientePrestamo(cliente);
        Prestamo nuevo = repositorio.save(entidad);
        return PrestamoMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public PrestamoDto updateSet(Integer id, PrestamoActualizarDto actualizarDto) {
        Prestamo entidad = PrestamoMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdPrestamo(id);
        Cliente cliente = clienteRepositorio.findById(actualizarDto.idCliente());
        if (cliente == null) {
            return null;
        }
        entidad.setClientePrestamo(cliente);
        Prestamo actualizado = repositorio.update(entidad);
        return PrestamoMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<PrestamoDto> selectFrom() {
        List<Prestamo> prestamos = repositorio.findAll();
        List<Cliente> clientes = clienteRepositorio.findAll();

        Map<Integer, Cliente> mapaClientes = mapaClientes(clientes);

        prestamos = sinClienteNulo(prestamos);
        prestamos = sinClienteInexistente(prestamos, mapaClientes);
        hidratarCliente(prestamos, mapaClientes);

        return PrestamoMapeador.SINGLETON.toDtoList(prestamos);
    }

    @Override
    public PrestamoDto selectOne(Integer codigo) {
        Prestamo entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return PrestamoMapeador.SINGLETON.toDto(entidad);
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

    private List<Prestamo> sinClienteNulo(List<Prestamo> prestamos) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getClientePrestamo() == null) {
                System.out.print("ERROR: Prestamo " + prestamo.getIdPrestamo());
                System.out.println(" cliente nulo, registro descartado");
                continue;
            }
            resultado.add(prestamo);
        }
        return resultado;
    }

    private List<Prestamo> sinClienteInexistente(
            List<Prestamo> prestamos,
            Map<Integer, Cliente> mapaClientes) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            int idCliente = prestamo.getClientePrestamo().getIdCliente();
            if (!mapaClientes.containsKey(idCliente)) {
                System.out.print("ERROR: Prestamo " + prestamo.getIdPrestamo());
                System.out.println(" cliente " + idCliente + " no existe, registro descartado");
                continue;
            }
            resultado.add(prestamo);
        }
        return resultado;
    }

    private void hidratarCliente(List<Prestamo> prestamos, Map<Integer, Cliente> mapaClientes) {
        for (Prestamo prestamo : prestamos) {
            int idCliente = prestamo.getClientePrestamo().getIdCliente();
            prestamo.setClientePrestamo(mapaClientes.get(idCliente));
        }
    }

    private boolean hidratarSelectOne(Prestamo entidad) {
        if (entidad.getClientePrestamo() == null) {
            return false;
        }
        int idCliente = entidad.getClientePrestamo().getIdCliente();
        Cliente cliente = clienteRepositorio.findById(idCliente);
        if (cliente == null) {
            return false;
        }
        entidad.setClientePrestamo(cliente);
        return true;
    }
}
