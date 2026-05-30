package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalActualizarDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalCrearDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalIdentificadorDto;
import org.banco.entidad.Cliente;
import org.banco.entidad.ClienteProductoSucursal;
import org.banco.entidad.ProductoFinanciero;
import org.banco.entidad.Sucursal;
import org.banco.entidad.pk.ClienteProductoSucursalPk;
import org.banco.mapeador.ClienteMapeador;
import org.banco.mapeador.ClienteProductoSucursalMapeador;
import org.banco.mapeador.ProductoFinancieroMapeador;
import org.banco.mapeador.SucursalMapeador;
import org.banco.repositorio.ClienteProductoSucursalRepositorio;
import org.banco.repositorio.ClienteRepositorio;
import org.banco.repositorio.ProductoFinacieroRepositorio;
import org.banco.repositorio.SucursalRepositorio;
import org.banco.servicio.api.ApiOperacionCompuestaServicio;

public class ClienteProductoSucursalServicio implements ApiOperacionCompuestaServicio<
        ClienteProductoSucursalCrearDto, 
        ClienteProductoSucursalDto, 
        ClienteProductoSucursalActualizarDto, 
        ClienteProductoSucursalIdentificadorDto> {

    private final ClienteProductoSucursalRepositorio clienteProductoSucursalRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoFinacieroRepositorio productoFinancieroRepositorio;
    private final SucursalRepositorio sucursalRepositorio;

    public ClienteProductoSucursalServicio(
            ClienteProductoSucursalRepositorio clienteProductoSucursalRepositorio,
            ClienteRepositorio clienteRepositorio,
            ProductoFinacieroRepositorio productoFinancieroRepositorio,
            SucursalRepositorio sucursalRepositorio) {
        this.clienteProductoSucursalRepositorio = clienteProductoSucursalRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.productoFinancieroRepositorio = productoFinancieroRepositorio;
        this.sucursalRepositorio = sucursalRepositorio;
    }

    private ClienteProductoSucursalPk toPk(ClienteProductoSucursalIdentificadorDto idDto) {
        return ClienteProductoSucursalPk.conIds(
                idDto.idCliente(), idDto.idProductoFinanciero(), idDto.idSucursal());
    }

    private ClienteProductoSucursal buscarEntidadPorPk(ClienteProductoSucursalPk codigo) {
        int codCliente = codigo.getCliente().getIdCliente();
        int codProducto = codigo.getProductoFinanciero().getIdProductoFinanciero();
        int codSucursal = codigo.getSucursal().getIdSucursal();

        for (ClienteProductoSucursal reg : clienteProductoSucursalRepositorio.findAll()) {
            ClienteProductoSucursalPk pkTemp = reg.getIdClienteProductoSucursalPk();
            if (pkTemp.getCliente().getIdCliente() == codCliente
                    && pkTemp.getProductoFinanciero().getIdProductoFinanciero() == codProducto
                    && pkTemp.getSucursal().getIdSucursal() == codSucursal) {
                return reg;
            }
        }
        return null;
    }

    @Override
    public ClienteProductoSucursalCrearDto insertInto(ClienteProductoSucursalCrearDto creacionDTO) {
        Cliente cliente = clienteRepositorio.findById(creacionDTO.idCliente());
        if (cliente == null) {
            return null;
        }
        ProductoFinanciero producto = productoFinancieroRepositorio.findById(creacionDTO.idProductoFinanciero());
        if (producto == null) {
            return null;
        }
        Sucursal sucursal = sucursalRepositorio.findById(creacionDTO.idSucursal());
        if (sucursal == null) {
            return null;
        }
        ClienteProductoSucursal entidad = ClienteProductoSucursalMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        if (buscarEntidadPorPk(entidad.getIdClienteProductoSucursalPk()) != null) {
            return null;
        }
        ClienteProductoSucursal nuevo = clienteProductoSucursalRepositorio.save(entidad);
        return ClienteProductoSucursalMapeador.SINGLETON.toCrearDto(nuevo);
    }

    @Override
    public ClienteProductoSucursalActualizarDto updateSet(
            ClienteProductoSucursalIdentificadorDto id,
            ClienteProductoSucursalActualizarDto actualizarDto) {
        ClienteProductoSucursalPk pk = toPk(id);
        ClienteProductoSucursal existente = buscarEntidadPorPk(pk);
        if (existente == null) {
            return null;
        }
        clienteProductoSucursalRepositorio.delete(existente);
        ClienteProductoSucursal actualizado = new ClienteProductoSucursal(
                id.idCliente(), id.idProductoFinanciero(), id.idSucursal());
        actualizado.setFechaAdquisicion(actualizarDto.fechaAdquisicion());
        actualizado.setValorInicial(actualizarDto.valorInicial());
        actualizado.setEstado(actualizarDto.estado());
        ClienteProductoSucursal grabado = clienteProductoSucursalRepositorio.save(actualizado);
        return ClienteProductoSucursalMapeador.SINGLETON.toActualizarDto(grabado);
    }

    @Override
    public Boolean deleteFrom(ClienteProductoSucursalIdentificadorDto codigo) {
        ClienteProductoSucursalPk pk = toPk(codigo);
        ClienteProductoSucursal existente = buscarEntidadPorPk(pk);
        if (existente == null) {
            return false;
        }
        return clienteProductoSucursalRepositorio.delete(existente);
    }

    @Override
    public int countRows() {
        return clienteProductoSucursalRepositorio.findAll().size();
    }

    @Override
    public List<ClienteProductoSucursalDto> selectFrom() {
        List<ClienteProductoSucursal> registros = clienteProductoSucursalRepositorio.findAll();

        List<Cliente> clientes = clienteRepositorio.findAll();
        List<ProductoFinanciero> productos = productoFinancieroRepositorio.findAll();
        List<Sucursal> sucursales = sucursalRepositorio.findAll();

        Map<Integer, Cliente> mapaClientes = mapaClientes(clientes);
        Map<Integer, ProductoFinanciero> mapaProductos = mapaProductos(productos);
        Map<Integer, Sucursal> mapaSucursales = mapaSucursales(sucursales);

        registros = sinClienteNulo(registros);
        registros = sinClienteInexistente(registros, mapaClientes);
        registros = sinProductoNulo(registros);
        registros = sinProductoInexistente(registros, mapaProductos);
        registros = sinSucursalNula(registros);
        registros = sinSucursalInexistente(registros, mapaSucursales);

        hidratarRelaciones(registros, mapaClientes, mapaProductos, mapaSucursales);

        List<ClienteProductoSucursalDto> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            Cliente clienteReal = mapaClientes.get(reg.getIdClienteProductoSucursalPk().getCliente().getIdCliente());
            ProductoFinanciero productoReal = mapaProductos.get(reg.getIdClienteProductoSucursalPk().getProductoFinanciero().getIdProductoFinanciero());
            Sucursal sucursalReal = mapaSucursales.get(reg.getIdClienteProductoSucursalPk().getSucursal().getIdSucursal());

            resultado.add(new ClienteProductoSucursalDto(
                    ClienteMapeador.SINGLETON.toDto(clienteReal),
                    ProductoFinancieroMapeador.SINGLETON.toDto(productoReal),
                    SucursalMapeador.SINGLETON.toDto(sucursalReal),
                    reg.getFechaAdquisicion(),
                    reg.getValorInicial(),
                    reg.getEstado()
            ));
        }
        return resultado;
    }

    @Override
    public ClienteProductoSucursalDto selectOne(ClienteProductoSucursalIdentificadorDto codigo) {
        ClienteProductoSucursalPk pk = toPk(codigo);
        ClienteProductoSucursal entidad = buscarEntidadPorPk(pk);
        if (entidad == null) {
            return null;
        }
        Cliente cliente = clienteRepositorio.findById(pk.getCliente().getIdCliente());
        if (cliente == null) {
            return null;
        }
        ProductoFinanciero producto = productoFinancieroRepositorio.findById(pk.getProductoFinanciero().getIdProductoFinanciero());
        if (producto == null) {
            return null;
        }
        Sucursal sucursal = sucursalRepositorio.findById(pk.getSucursal().getIdSucursal());
        if (sucursal == null) {
            return null;
        }
        return new ClienteProductoSucursalDto(
                ClienteMapeador.SINGLETON.toDto(cliente),
                ProductoFinancieroMapeador.SINGLETON.toDto(producto),
                SucursalMapeador.SINGLETON.toDto(sucursal),
                entidad.getFechaAdquisicion(),
                entidad.getValorInicial(),
                entidad.getEstado()
        );
    }

    private Map<Integer, Cliente> mapaClientes(List<Cliente> clientes) {
        Map<Integer, Cliente> mapa = new HashMap<>();
        for (Cliente c : clientes) {
            mapa.put(c.getIdCliente(), c);
        }
        return mapa;
    }

    private Map<Integer, ProductoFinanciero> mapaProductos(List<ProductoFinanciero> productos) {
        Map<Integer, ProductoFinanciero> mapa = new HashMap<>();
        for (ProductoFinanciero p : productos) {
            mapa.put(p.getIdProductoFinanciero(), p);
        }
        return mapa;
    }

    private Map<Integer, Sucursal> mapaSucursales(List<Sucursal> sucursales) {
        Map<Integer, Sucursal> mapa = new HashMap<>();
        for (Sucursal s : sucursales) {
            mapa.put(s.getIdSucursal(), s);
        }
        return mapa;
    }

    private List<ClienteProductoSucursal> sinClienteNulo(List<ClienteProductoSucursal> registros) {
        List<ClienteProductoSucursal> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            if (reg.getIdClienteProductoSucursalPk().getCliente() == null) {
                System.out.println("ERROR: ClienteProductoSucursal con cliente null, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<ClienteProductoSucursal> sinClienteInexistente(
            List<ClienteProductoSucursal> registros,
            Map<Integer, Cliente> mapaClientes) {
        List<ClienteProductoSucursal> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            int idCliente = reg.getIdClienteProductoSucursalPk().getCliente().getIdCliente();
            if (!mapaClientes.containsKey(idCliente)) {
                System.out.println("ERROR: ClienteProductoSucursal cliente " + idCliente + " no existe, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<ClienteProductoSucursal> sinProductoNulo(List<ClienteProductoSucursal> registros) {
        List<ClienteProductoSucursal> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            if (reg.getIdClienteProductoSucursalPk().getProductoFinanciero() == null) {
                System.out.println("ERROR: ClienteProductoSucursal con producto null, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<ClienteProductoSucursal> sinProductoInexistente(
            List<ClienteProductoSucursal> registros,
            Map<Integer, ProductoFinanciero> mapaProductos) {
        List<ClienteProductoSucursal> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            int idProducto = reg.getIdClienteProductoSucursalPk().getProductoFinanciero().getIdProductoFinanciero();
            if (!mapaProductos.containsKey(idProducto)) {
                System.out.println("ERROR: ClienteProductoSucursal producto " + idProducto + " no existe, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<ClienteProductoSucursal> sinSucursalNula(List<ClienteProductoSucursal> registros) {
        List<ClienteProductoSucursal> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            if (reg.getIdClienteProductoSucursalPk().getSucursal() == null) {
                System.out.println("ERROR: ClienteProductoSucursal con sucursal null, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<ClienteProductoSucursal> sinSucursalInexistente(
            List<ClienteProductoSucursal> registros,
            Map<Integer, Sucursal> mapaSucursales) {
        List<ClienteProductoSucursal> resultado = new ArrayList<>();
        for (ClienteProductoSucursal reg : registros) {
            int idSucursal = reg.getIdClienteProductoSucursalPk().getSucursal().getIdSucursal();
            if (!mapaSucursales.containsKey(idSucursal)) {
                System.out.println("ERROR: ClienteProductoSucursal sucursal " + idSucursal + " no existe, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private void hidratarRelaciones(
            List<ClienteProductoSucursal> registros,
            Map<Integer, Cliente> mapaClientes,
            Map<Integer, ProductoFinanciero> mapaProductos,
            Map<Integer, Sucursal> mapaSucursales) {
        for (ClienteProductoSucursal reg : registros) {
            int idCliente = reg.getIdClienteProductoSucursalPk().getCliente().getIdCliente();
            int idProducto = reg.getIdClienteProductoSucursalPk().getProductoFinanciero().getIdProductoFinanciero();
            int idSucursal = reg.getIdClienteProductoSucursalPk().getSucursal().getIdSucursal();

            reg.getIdClienteProductoSucursalPk().getCliente().setIdCliente(mapaClientes.get(idCliente).getIdCliente());
            reg.getIdClienteProductoSucursalPk().getCliente().setNombreCliente(mapaClientes.get(idCliente).getNombreCliente());
            reg.getIdClienteProductoSucursalPk().getProductoFinanciero().setIdProductoFinanciero(mapaProductos.get(idProducto).getIdProductoFinanciero());
            reg.getIdClienteProductoSucursalPk().getProductoFinanciero().setNombreProductoFinanciero(mapaProductos.get(idProducto).getNombreProductoFinanciero());
            reg.getIdClienteProductoSucursalPk().getSucursal().setIdSucursal(mapaSucursales.get(idSucursal).getIdSucursal());
            reg.getIdClienteProductoSucursalPk().getSucursal().setNombreSucursal(mapaSucursales.get(idSucursal).getNombreSucursal());
        }
    }

}