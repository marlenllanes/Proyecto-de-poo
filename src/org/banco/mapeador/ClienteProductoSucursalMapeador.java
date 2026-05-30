package org.banco.mapeador;

import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalActualizarDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalCrearDto;
import org.banco.entidad.ClienteProductoSucursal;
import org.banco.mapeador.api.ApiMapeadorCompuesto;

public final class ClienteProductoSucursalMapeador implements
        ApiMapeadorCompuesto<ClienteProductoSucursal, ClienteProductoSucursalCrearDto, ClienteProductoSucursalActualizarDto> {

    public static final ClienteProductoSucursalMapeador SINGLETON = new ClienteProductoSucursalMapeador();

    private ClienteProductoSucursalMapeador() {
    }

    @Override
    public ClienteProductoSucursal toEntityFromCrear(ClienteProductoSucursalCrearDto dto) {
        if (dto == null) {
            return null;
        }
        ClienteProductoSucursal entidad = new ClienteProductoSucursal(
                dto.idCliente(),
                dto.idProductoFinanciero(),
                dto.idSucursal()
        );
        entidad.setFechaAdquisicion(dto.fechaAdquisicion());
        entidad.setValorInicial(dto.valorInicial());
        entidad.setEstado(dto.estado());
        return entidad;
    }

    @Override
    public ClienteProductoSucursal toEntityFromActualizar(ClienteProductoSucursalActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        ClienteProductoSucursal entidad = new ClienteProductoSucursal(
                dto.idCliente(),
                dto.idProductoFinanciero(),
                dto.idSucursal()
        );
        entidad.setFechaAdquisicion(dto.fechaAdquisicion());
        entidad.setValorInicial(dto.valorInicial());
        entidad.setEstado(dto.estado());
        return entidad;
    }

    @Override
    public ClienteProductoSucursalCrearDto toCrearDto(ClienteProductoSucursal entidad) {
        if (entidad == null) {
            return null;
        }
        return new ClienteProductoSucursalCrearDto(
                entidad.getIdClienteProductoSucursalPk().getCliente().getIdCliente(),
                entidad.getIdClienteProductoSucursalPk().getProductoFinanciero().getIdProductoFinanciero(),
                entidad.getIdClienteProductoSucursalPk().getSucursal().getIdSucursal(),
                entidad.getFechaAdquisicion(),
                entidad.getValorInicial(),
                entidad.getEstado()
        );
    }

    @Override
    public ClienteProductoSucursalActualizarDto toActualizarDto(ClienteProductoSucursal entidad) {
        if (entidad == null) {
            return null;
        }
        return new ClienteProductoSucursalActualizarDto(
                entidad.getIdClienteProductoSucursalPk().getCliente().getIdCliente(),
                entidad.getIdClienteProductoSucursalPk().getProductoFinanciero().getIdProductoFinanciero(),
                entidad.getIdClienteProductoSucursalPk().getSucursal().getIdSucursal(),
                entidad.getFechaAdquisicion(),
                entidad.getValorInicial(),
                entidad.getEstado()
        );
    }
}