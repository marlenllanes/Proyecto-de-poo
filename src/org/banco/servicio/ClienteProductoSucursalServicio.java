package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalActualizarDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalCrearDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalIdentificadorDto;
import org.banco.repositorio.ClienteProductoSucursalRepositorio;
import org.banco.repositorio.ClienteRepositorio;
import org.banco.repositorio.ProductoFinacieroRepositorio;
import org.banco.repositorio.SucursalRepositorio;
import org.banco.servicio.api.ApiOperacionCompuestaServicio;

public class ClienteProductoSucursalServicio implements ApiOperacionCompuestaServicio<
        ClienteProductoSucursalCrearDto, ClienteProductoSucursalDto, ClienteProductoSucursalActualizarDto, ClienteProductoSucursalIdentificadorDto> {

    private final ClienteProductoSucursalRepositorio clienteProductoSucursalRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoFinacieroRepositorio pructoFinancieroRepositorio;
    private final SucursalRepositorio sucursalRepositorio;

    public VentaFormaPagoServicio(
            VentaFormaPagoRepositorio ventaFormaPagoRepositorio,
            VentaRepositorio ventaRepositorio,
            FormaPagoRepositorio formaPagoRepositorio,
            ClienteRepositorio clienteRepositorio
    ) {
        this.ventaFormaPagoRepositorio = ventaFormaPagoRepositorio;
        this.ventaRepositorio = ventaRepositorio;
        this.formaPagoRepositorio = formaPagoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    private VentaFormaPagoPk toPk(VentaFormaPagoIdentificadorDto idDto) {
        return VentaFormaPagoPk.conIds(idDto.idVenta(), idDto.idFormaPago());
    }

    private VentaFormaPago buscarEntidadPorPk(VentaFormaPagoPk codigo) {
        int codVenta = codigo.getVenta().getIdVenta();
        int codFormaPago = codigo.getFormaPago().getIdFormaPago();

        for (VentaFormaPago reg : ventaFormaPagoRepositorio.findAll()) {
            VentaFormaPagoPk pkTemp = reg.getIdVentaFormaPagoPk();

            if (pkTemp.getVenta().getIdVenta() == codVenta
                    && pkTemp.getFormaPago().getIdFormaPago() == codFormaPago) {
                return reg;
            }
        }
        return null;
    }

    @Override
    public VentaFormaPagoCrearDto insertInto(VentaFormaPagoCrearDto creacionDTO) {
        Venta venta = ventaRepositorio.findById(creacionDTO.idVenta());
        if (venta == null) {
            return null;
        }

        FormaPago formaPago = formaPagoRepositorio.findById(creacionDTO.idFormaPago());
        if (formaPago == null) {
            return null;
        }

        VentaFormaPago entidad = VentaFormaPagoMapeador.SINGLETON.toEntityFromCrear(creacionDTO);

        VentaFormaPagoPk pk = entidad.getIdVentaFormaPagoPk();
        if (buscarEntidadPorPk(pk) != null) {
            return null;
        }

        VentaFormaPago nuevo = ventaFormaPagoRepositorio.save(entidad);
        return VentaFormaPagoMapeador.SINGLETON.toCrearDto(nuevo);
    }

    @Override
    public VentaFormaPagoActualizarDto updateSet(
            VentaFormaPagoIdentificadorDto id,
            VentaFormaPagoActualizarDto actualizarDto
    ) {
        VentaFormaPagoPk pk = toPk(id);
        VentaFormaPago existente = buscarEntidadPorPk(pk);
        if (existente == null) {
            return null;
        }

        ventaFormaPagoRepositorio.delete(existente);

        VentaFormaPago actualizado = new VentaFormaPago(id.idVenta(), id.idFormaPago());
        actualizado.setValorVentaFormaPago(actualizarDto.valorVentaFormaPago());

        VentaFormaPago grabado = ventaFormaPagoRepositorio.save(actualizado);
        return VentaFormaPagoMapeador.SINGLETON.toActualizarDto(grabado);
    }

    @Override
    public Boolean deleteFrom(VentaFormaPagoIdentificadorDto codigo) {
        VentaFormaPagoPk pk = toPk(codigo);
        VentaFormaPago existente = buscarEntidadPorPk(pk);
        if (existente == null) {
            return false;
        }
        return ventaFormaPagoRepositorio.delete(existente);
    }

    @Override
    public int countRows() {
        return ventaFormaPagoRepositorio.findAll().size();
    }

    @Override
    public List<VentaFormaPagoDto> selectFrom() {
        List<VentaFormaPago> registros = ventaFormaPagoRepositorio.findAll();

        registros = sinVentaNula(registros);
        registros = sinFormaPagoNula(registros);

        List<Venta> ventas = ventaRepositorio.findAll();
        List<FormaPago> formas = formaPagoRepositorio.findAll();
        List<Cliente> clientes = clienteRepositorio.findAll();

        Map<Integer, Venta> mapaVentas = mapaVentas(ventas);
        Map<Integer, FormaPago> mapaFormas = mapaFormasPago(formas);
        Map<Integer, Cliente> mapaClientes = mapaClientes(clientes);

        registros = sinVentaInexistente(registros, mapaVentas);
        registros = sinFormaPagoInexistente(registros, mapaFormas);

        hidratarPadresVentaFormaPago(registros, mapaVentas, mapaFormas);

        registros = sinClienteNulo(registros);
        registros = sinClienteInexistente(registros, mapaClientes);

        hidratarHijoCliente(registros, mapaClientes);

        List<VentaFormaPagoDto> resultado = new ArrayList<>();
        for (VentaFormaPago reg : registros) {
            int idVenta = reg.getIdVentaFormaPagoPk().getVenta().getIdVenta();
            int idForma = reg.getIdVentaFormaPagoPk().getFormaPago().getIdFormaPago();

            Venta ventaReal = mapaVentas.get(idVenta);
            Cliente clienteReal = mapaClientes.get(ventaReal.getClienteVenta().getIdCliente());
            FormaPago formaReal = mapaFormas.get(idForma);

            VentaDto ventaDto = new VentaDto(
                    ventaReal.getIdVenta(),
                    ClienteMapeador.SINGLETON.toDto(clienteReal),
                    ventaReal.getFechaVenta(),
                    ventaReal.getTotalVenta(),
                    ventaReal.getIvaVenta()
            );

            FormaPagoDto formaDto = FormaPagoMapeador.SINGLETON.toDto(formaReal);

            resultado.add(new VentaFormaPagoDto(
                    ventaDto,
                    formaDto,
                    reg.getValorVentaFormaPago()
            ));
        }
        return resultado;
    }

    @Override
    public VentaFormaPagoDto selectOne(VentaFormaPagoIdentificadorDto codigo) {
        VentaFormaPagoPk pk = toPk(codigo);
        VentaFormaPago entidad = buscarEntidadPorPk(pk);

        if (entidad == null) {
            return null;
        }

        if (!hidratarSelectOne(entidad)) {
            return null;
        }

        int idVenta = entidad.getIdVentaFormaPagoPk().getVenta().getIdVenta();
        int idForma = entidad.getIdVentaFormaPagoPk().getFormaPago().getIdFormaPago();

        Venta ventaReal = ventaRepositorio.findById(idVenta);
        Cliente clienteReal = clienteRepositorio.findById(ventaReal.getClienteVenta().getIdCliente());
        FormaPago formaReal = formaPagoRepositorio.findById(idForma);

        VentaDto ventaDto = new VentaDto(
                ventaReal.getIdVenta(),
                ClienteMapeador.SINGLETON.toDto(clienteReal),
                ventaReal.getFechaVenta(),
                ventaReal.getTotalVenta(),
                ventaReal.getIvaVenta()
        );

        FormaPagoDto formaDto = FormaPagoMapeador.SINGLETON.toDto(formaReal);

        return new VentaFormaPagoDto(
                ventaDto,
                formaDto,
                entidad.getValorVentaFormaPago()
        );
    }

    private Map<Integer, Venta> mapaVentas(List<Venta> ventas) {
        Map<Integer, Venta> mapa = new HashMap<>();
        for (Venta v : ventas) {
            mapa.put(v.getIdVenta(), v);
        }
        return mapa;
    }

    private Map<Integer, FormaPago> mapaFormasPago(List<FormaPago> formas) {
        Map<Integer, FormaPago> mapa = new HashMap<>();
        for (FormaPago f : formas) {
            mapa.put(f.getIdFormaPago(), f);
        }
        return mapa;
    }

    private Map<Integer, Cliente> mapaClientes(List<Cliente> clientes) {
        Map<Integer, Cliente> mapa = new HashMap<>();
        for (Cliente c : clientes) {
            mapa.put(c.getIdCliente(), c);
        }
        return mapa;
    }

    private List<VentaFormaPago> sinVentaNula(List<VentaFormaPago> registros) {
        List<VentaFormaPago> resultado = new ArrayList<>();
        for (VentaFormaPago reg : registros) {
            if (reg.getIdVentaFormaPagoPk().getVenta() == null) {
                System.out.println("ERROR: VentaFormaPago con venta null, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<VentaFormaPago> sinFormaPagoNula(List<VentaFormaPago> registros) {
        List<VentaFormaPago> resultado = new ArrayList<>();
        for (VentaFormaPago reg : registros) {
            if (reg.getIdVentaFormaPagoPk().getFormaPago() == null) {
                System.out.println("ERROR: VentaFormaPago con formaPago null, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<VentaFormaPago> sinClienteNulo(List<VentaFormaPago> registros) {
        List<VentaFormaPago> resultado = new ArrayList<>();
        for (VentaFormaPago reg : registros) {
            Venta ventaTemp = reg.getIdVentaFormaPagoPk().getVenta();
            if (ventaTemp == null || ventaTemp.getClienteVenta() == null) {
                System.out.println("ERROR: VentaFormaPago con cliente null, registro descartado");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private List<VentaFormaPago> sinVentaInexistente(
            List<VentaFormaPago> registros,
            Map<Integer, Venta> mapaVentas) {

        List<VentaFormaPago> resultado = new ArrayList<>();

        for (VentaFormaPago reg : registros) {
            int idVenta = reg.getIdVentaFormaPagoPk().getVenta().getIdVenta();

            if (!mapaVentas.containsKey(idVenta)) {
                System.out.print("ERROR: VentaFormaPago ");
                System.out.println("venta " + idVenta + " no existe");
                continue;
            }

            resultado.add(reg);
        }
        return resultado;
    }

    private List<VentaFormaPago> sinFormaPagoInexistente(
            List<VentaFormaPago> registros,
            Map<Integer, FormaPago> mapaFormas
    ) {
        List<VentaFormaPago> resultado = new ArrayList<>();
        for (VentaFormaPago reg : registros) {
            int idForma = reg.getIdVentaFormaPagoPk().getFormaPago().getIdFormaPago();

            if (!mapaFormas.containsKey(idForma)) {
                System.out.print("ERROR: VentaFormaPago ");
                System.out.println("formaPago " + idForma + " no existe");
                continue;
            }

            resultado.add(reg);
        }

        return resultado;
    }

    private List<VentaFormaPago> sinClienteInexistente(
            List<VentaFormaPago> registros,
            Map<Integer, Cliente> mapaClientes) {

        List<VentaFormaPago> resultado = new ArrayList<>();

        for (VentaFormaPago reg : registros) {
            int idCliente = reg.getIdVentaFormaPagoPk()
                    .getVenta()
                    .getClienteVenta()
                    .getIdCliente();

            if (!mapaClientes.containsKey(idCliente)) {
                System.out.print("ERROR: VentaFormaPago ");
                System.out.println("cliente " + idCliente + " no existe");
                continue;
            }
            resultado.add(reg);
        }
        return resultado;
    }

    private void hidratarPadresVentaFormaPago(
            List<VentaFormaPago> registros,
            Map<Integer, Venta> mapaVentas,
            Map<Integer, FormaPago> mapaFormas
    ) {

        for (VentaFormaPago miRegistro : registros) {
            int idVenta = miRegistro.getIdVentaFormaPagoPk().getVenta().getIdVenta();
            Venta ventaReal = mapaVentas.get(idVenta);

            miRegistro.getIdVentaFormaPagoPk().getVenta().setIdVenta(ventaReal.getIdVenta());
            miRegistro.getIdVentaFormaPagoPk().getVenta().setClienteVenta(ventaReal.getClienteVenta());
            miRegistro.getIdVentaFormaPagoPk().getVenta().setFechaVenta(ventaReal.getFechaVenta());
            miRegistro.getIdVentaFormaPagoPk().getVenta().setTotalVenta(ventaReal.getTotalVenta());
            miRegistro.getIdVentaFormaPagoPk().getVenta().setIvaVenta(ventaReal.getIvaVenta());

            int idForma = miRegistro.getIdVentaFormaPagoPk().getFormaPago().getIdFormaPago();
            FormaPago formaReal = mapaFormas.get(idForma);

            miRegistro.getIdVentaFormaPagoPk().getFormaPago().setIdFormaPago(formaReal.getIdFormaPago());
            miRegistro.getIdVentaFormaPagoPk().getFormaPago().setNombreFormaPago(formaReal.getNombreFormaPago());
        }
    }

    private void hidratarHijoCliente(
            List<VentaFormaPago> registros,
            Map<Integer, Cliente> mapaClientes
    ) {

        for (VentaFormaPago miRegistro : registros) {
            Venta ventaReal = miRegistro.getIdVentaFormaPagoPk().getVenta();

            int idCliente = ventaReal.getClienteVenta().getIdCliente();
            Cliente clienteReal = mapaClientes.get(idCliente);
            ventaReal.setClienteVenta(clienteReal);
        }
    }

    private boolean hidratarSelectOne(VentaFormaPago registro) {
        int idVenta = registro.getIdVentaFormaPagoPk().getVenta().getIdVenta();
        Venta ventaReal = ventaRepositorio.findById(idVenta);
        if (ventaReal == null) {
            return false;
        }

        int idCliente = ventaReal.getClienteVenta().getIdCliente();
        Cliente cliReal = clienteRepositorio.findById(idCliente);
        if (cliReal == null) {
            return false;
        }
        ventaReal.setClienteVenta(cliReal);

        int idForma = registro.getIdVentaFormaPagoPk().getFormaPago().getIdFormaPago();
        FormaPago formaReal = formaPagoRepositorio.findById(idForma);
        if (formaReal == null) {
            return false;
        }

        registro.getIdVentaFormaPagoPk().getVenta().setIdVenta(ventaReal.getIdVenta());
        registro.getIdVentaFormaPagoPk().getVenta().setClienteVenta(cliReal);
        registro.getIdVentaFormaPagoPk().getVenta().setFechaVenta(ventaReal.getFechaVenta());
        registro.getIdVentaFormaPagoPk().getVenta().setTotalVenta(ventaReal.getTotalVenta());
        registro.getIdVentaFormaPagoPk().getVenta().setIvaVenta(ventaReal.getIvaVenta());

        registro.getIdVentaFormaPagoPk().getFormaPago().setIdFormaPago(formaReal.getIdFormaPago());
        registro.getIdVentaFormaPagoPk().getFormaPago().setNombreFormaPago(formaReal.getNombreFormaPago());
        return true;
    }

}

}
