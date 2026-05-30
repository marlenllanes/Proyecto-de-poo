package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cliente.ClienteActualizarDto;
import org.banco.dto.cliente.ClienteCrearDto;
import org.banco.dto.cliente.ClienteDto;
import org.banco.entidad.Cliente;
import org.banco.mapeador.api.ApiMapeador;

public final class ClienteMapeador implements
        ApiMapeador<Cliente, ClienteCrearDto, ClienteDto, ClienteActualizarDto> {

    public static final ClienteMapeador SINGLETON = new ClienteMapeador();

    private ClienteMapeador() {
    }

    @Override
    public ClienteDto toDto(Cliente entidad) {
        if (entidad == null) {
            return null;
        }

        ClienteDto dto = new ClienteDto(
                entidad.getIdCliente(),
                entidad.getNombreCliente(),
                entidad.getDocumentoCliente(),
                entidad.getCorreoCliente(),
                entidad.getCelularCliente(),
                entidad.getFechaNacimientoCliente(),
                entidad.getDireccionCliente()
        );

        return dto;
    }

    @Override
    public Cliente toEntityFromCrear(ClienteCrearDto dto) {
        if (dto == null) {
            return null;
        }
        Cliente entidad = new Cliente();
        entidad.setNombreCliente(dto.nombreCliente());
        entidad.setDocumentoCliente(dto.documentoCliente());
        entidad.setCorreoCliente(dto.correoCliente());
        entidad.setCelularCliente(dto.celularCliente());
        entidad.setFechaNacimientoCliente(dto.fechaNacimientoCliente());
        entidad.setDireccionCliente(dto.direccionCliente());

        return entidad;
    }

    @Override
    public Cliente toEntityFromActualizar(ClienteActualizarDto dto) {
        if (dto == null) {
            return null;
        }

        Cliente entidad = new Cliente();
        entidad.setIdCliente(dto.idCliente());
        entidad.setNombreCliente(dto.nombreCliente());
        entidad.setDocumentoCliente(dto.documentoCliente());
        entidad.setCorreoCliente(dto.correoCliente());
        entidad.setCelularCliente(dto.celularCliente());
        entidad.setFechaNacimientoCliente(dto.fechaNacimientoCliente());
        entidad.setDireccionCliente(dto.direccionCliente());

        return entidad;

    }

    @Override
    public List<ClienteDto> toDtoList(List<Cliente> entidades) {
        List<ClienteDto> arreglo = new ArrayList<>();

        if (entidades == null) {
            return arreglo;
        }
        for (Cliente entidad : entidades) {
            ClienteDto dto = toDto(entidad);
            arreglo.add(dto);
        }

        return arreglo;
    }
}
