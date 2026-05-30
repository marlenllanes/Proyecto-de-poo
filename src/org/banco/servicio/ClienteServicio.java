package org.banco.servicio;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cliente.ClienteActualizarDto;
import org.banco.dto.cliente.ClienteCrearDto;
import org.banco.dto.cliente.ClienteDto;
import org.banco.entidad.Cliente;
import org.banco.mapeador.ClienteMapeador;
import org.banco.recurso.api.ApiOperacionServicio;
import org.banco.recurso.utilidad.ValidadorCorreo;
import org.banco.repositorio.ClienteRepositorio;

public class ClienteServicio implements ApiOperacionServicio
        <ClienteCrearDto, ClienteDto, ClienteActualizarDto, Integer> {
    
    private final ClienteRepositorio repositorio;
    
    public ClienteServicio(ClienteRepositorio repo) {
        this.repositorio = repo;
    }

    @Override
    public ClienteDto insertInto(ClienteCrearDto creacionDTO) {
        Cliente entidad = ClienteMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Cliente nuevo = repositorio.save(entidad);
        return ClienteMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public ClienteDto updateSet(Integer id, ClienteActualizarDto actualizarDto) {
        Cliente entidad = ClienteMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdCliente(id);
        Cliente actualizado = repositorio.update(entidad);
        return ClienteMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
       return repositorio.deleteById(codigo);
    }

    @Override
    public List<ClienteDto> selectFrom() {
        List<Cliente> clientes = repositorio.findAll();

        clientes = sinFechaNacimientoInvalida(clientes);
        clientes = sinCorreoValido(clientes);

        return ClienteMapeador.SINGLETON.toDtoList(clientes);
    }

    @Override
    public ClienteDto selectOne(Integer codigo) {
        Cliente entidad = repositorio.findById(codigo);
        return ClienteMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
         return repositorio.getLastId();
    }

private List<Cliente> sinFechaNacimientoInvalida(List<Cliente> clientes) {
        List<Cliente> resultado = new ArrayList<>();

        for (Cliente miCliente : clientes) {
            if (miCliente.getFechaNacimientoCliente() == null) {
                System.out.print("ERROR: Cliente " + miCliente.getIdCliente());
                System.out.println(" fecha nacimiento invalida, registro descartado");
                continue;
            }
            resultado.add(miCliente);
        }

        return resultado;
    }

    private List<Cliente> sinCorreoValido(List<Cliente> clientes) {
        List<Cliente> resultado = new ArrayList<>();

        for (Cliente miCliente : clientes) {
            String correo = miCliente.getCorreoCliente();

            if (!ValidadorCorreo.validarCorreo(correo)) {
                System.out.print("ERROR: Cliente " + miCliente.getIdCliente());
                System.out.println(" correo inválido, registro descartado");
                continue;
            }

            resultado.add(miCliente);
        }

        return resultado;
    }
}
