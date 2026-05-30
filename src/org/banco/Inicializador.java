package org.banco;

import com.cleandev.webserver.CleanDevServer;
import org.banco.controlador.CajeroControlador;
import org.banco.controlador.ClienteControlador;
import org.banco.controlador.ClienteProductoSucursalControlador;
import org.banco.controlador.ConsumoTarjetaControlador;
import org.banco.controlador.CuentaBancariaControlador;
import org.banco.controlador.CuotaPrestamoControlador;
import org.banco.controlador.MovimientoCuentaControlador;
import org.banco.controlador.PagoPrestamoControlador;
import org.banco.controlador.PrestamoControlador;
import org.banco.controlador.ProductoFinancieroControlador;
import org.banco.controlador.SucursalControlador;
import org.banco.controlador.TarjetaControlador;
import org.banco.controlador.TransaccionCajeroControlador;
import org.banco.controlador.TransferenciaControlador;
import org.banco.repositorio.CajeroRepositorio;
import org.banco.repositorio.CajeroRepositorioImpl;
import org.banco.repositorio.ClienteProductoSucursalRepositorio;
import org.banco.repositorio.ClienteProductoSucursalRepositorioImpl;
import org.banco.repositorio.ClienteRepositorio;
import org.banco.repositorio.ClienteRepositorioImpl;
import org.banco.repositorio.ConsumoTarjetaRepositorio;
import org.banco.repositorio.ConsumoTarjetaRepositorioImpl;
import org.banco.repositorio.CuentaBancariaRepositorio;
import org.banco.repositorio.CuentaBancariaRepositorioImpl;
import org.banco.repositorio.CuotaPrestamoRepositorio;
import org.banco.repositorio.CuotaPrestamoRepositorioImpl;
import org.banco.repositorio.MovimientoCuentaRepositorio;
import org.banco.repositorio.MovimientoCuentaRepositorioImpl;
import org.banco.repositorio.PagoPrestamoRepositorio;
import org.banco.repositorio.PagoPrestamoRepositorioImpl;
import org.banco.repositorio.PrestamoRepositorio;
import org.banco.repositorio.PrestamoRepositorioImpl;
import org.banco.repositorio.ProductoFinacieroRepositorio;
import org.banco.repositorio.ProductoFinacieroRepositorioImpl;
import org.banco.repositorio.SucursalRepositorio;
import org.banco.repositorio.SucursalRepositorioImpl;
import org.banco.repositorio.TarjetaRepositorio;
import org.banco.repositorio.TarjetaRepositorioImpl;
import org.banco.repositorio.TransaccionCajeroRepositorio;
import org.banco.repositorio.TransaccionCajeroRepositorioImpl;
import org.banco.repositorio.TransferenciaRepositorio;
import org.banco.repositorio.TransferenciaRepositorioImpl;
import org.banco.servicio.CajeroServicio;
import org.banco.servicio.ClienteProductoSucursalServicio;
import org.banco.servicio.ClienteServicio;
import org.banco.servicio.ConsumoTarjetaServicio;
import org.banco.servicio.CuentaBancariaServicio;
import org.banco.servicio.CuotaPrestamoServicio;
import org.banco.servicio.MovimientoCuentaServicio;
import org.banco.servicio.PagoPrestamoServicio;
import org.banco.servicio.PrestamoServicio;
import org.banco.servicio.ProductoFinancieroServicio;
import org.banco.servicio.SucursalServicio;
import org.banco.servicio.TarjetaServicio;
import org.banco.servicio.TransaccionCajeroServicio;
import org.banco.servicio.TransferenciaServicio;
import org.banco.webconfig.EnsambladorWeb;
import org.banco.webconfig.Ruteo;

public class Inicializador {

    //Repositorios
    private CajeroRepositorio cajeroRepositorio;
    private ClienteRepositorio clienteRepositorio;
    private ClienteProductoSucursalRepositorio clienteProductoSucursalRepositorio;
    private ConsumoTarjetaRepositorio consumoTarjetaRepositorio;
    private CuentaBancariaRepositorio cuentaBancariaRepositorio;
    private CuotaPrestamoRepositorio cuotaPrestamoRepositorio;
    private MovimientoCuentaRepositorio movimientoCuentaRepositorio;
    private PagoPrestamoRepositorio pagoPrestamoRepositorio;
    private PrestamoRepositorio prestamoRepositorio;
    private ProductoFinacieroRepositorio productoFinacieroRepositorio;
    private SucursalRepositorio sucursalRepositorio;
    private TarjetaRepositorio tarjetaRepositorio;
    private TransaccionCajeroRepositorio transaccionCajeroRepositorio;
    private TransferenciaRepositorio transferenciaRepositorio;

    //Servicios
    private CajeroServicio cajeroServicio;
    private ClienteServicio clienteServicio;
    private ClienteProductoSucursalServicio clienteProductoSucursalServicio;
    private ConsumoTarjetaServicio consumoTarjetaServicio;
    private CuentaBancariaServicio cuentaBancariaServicio;
    private CuotaPrestamoServicio cuotaPrestamoServicio;
    private MovimientoCuentaServicio movimientoCuentaServicio;
    private PagoPrestamoServicio pagoPrestamoServicio;
    private PrestamoServicio prestamoServicio;
    private ProductoFinancieroServicio productoFinacieroServicio;
    private SucursalServicio sucursalServicio;
    private TarjetaServicio tarjetaServicio;
    private TransaccionCajeroServicio transaccionCajeroServicio;
    private TransferenciaServicio transferenciaServicio;

    //Controladores
    private CajeroControlador cajeroControlador;
    private ClienteControlador clienteControlador;
    private ClienteProductoSucursalControlador clienteProductoSucursalControlador;
    private ConsumoTarjetaControlador consumoTarjetaControlador;
    private CuentaBancariaControlador cuentaBancariaControlador;
    private CuotaPrestamoControlador cuotaPrestamoControlador;
    private MovimientoCuentaControlador movimientoCuentaControlador;
    private PagoPrestamoControlador pagoPrestamoControlador;
    private PrestamoControlador prestamoControlador;
    private ProductoFinancieroControlador productoFinacieroControlador;
    private SucursalControlador sucursalControlador;
    private TarjetaControlador tarjetaControlador;
    private TransaccionCajeroControlador transaccionCajeroControlador;
    private TransferenciaControlador transferenciaControlador;

    public void ejecutar() {
        try {
            inicializarRepositorios();
            inicializarServicios();
            inicializarControladores();

            EnsambladorWeb.inicializador(cajeroControlador, clienteControlador, clienteProductoSucursalControlador, consumoTarjetaControlador, cuentaBancariaControlador, cuotaPrestamoControlador, movimientoCuentaControlador, pagoPrestamoControlador, prestamoControlador, productoFinacieroControlador, sucursalControlador, tarjetaControlador, transaccionCajeroControlador, transferenciaControlador);
            CleanDevServer.iniciar(new Ruteo());

        } catch (Exception ex) {
            System.getLogger(Inicializador.class.getName())
                    .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void inicializarRepositorios() throws Exception {
        cajeroRepositorio = new CajeroRepositorioImpl(true);
        clienteRepositorio = new ClienteRepositorioImpl(true);
        clienteProductoSucursalRepositorio = new ClienteProductoSucursalRepositorioImpl();
        consumoTarjetaRepositorio = new ConsumoTarjetaRepositorioImpl(true);
        cuentaBancariaRepositorio = new CuentaBancariaRepositorioImpl(true);
        cuotaPrestamoRepositorio = new CuotaPrestamoRepositorioImpl(true);
        movimientoCuentaRepositorio = new MovimientoCuentaRepositorioImpl(true);
        pagoPrestamoRepositorio = new PagoPrestamoRepositorioImpl(true);
        prestamoRepositorio = new PrestamoRepositorioImpl(true);
        productoFinacieroRepositorio = new ProductoFinacieroRepositorioImpl(true);
        sucursalRepositorio = new SucursalRepositorioImpl(true);
        tarjetaRepositorio = new TarjetaRepositorioImpl(true);
        transaccionCajeroRepositorio = new TransaccionCajeroRepositorioImpl(true);
        transferenciaRepositorio = new TransferenciaRepositorioImpl();
    }

    private void inicializarServicios() {
        cajeroServicio = new CajeroServicio(cajeroRepositorio, sucursalRepositorio);
        clienteServicio = new ClienteServicio(clienteRepositorio);
        clienteProductoSucursalServicio = new ClienteProductoSucursalServicio(clienteProductoSucursalRepositorio, clienteRepositorio, productoFinacieroRepositorio, sucursalRepositorio);
        consumoTarjetaServicio = new ConsumoTarjetaServicio(consumoTarjetaRepositorio, tarjetaRepositorio);
        cuentaBancariaServicio = new CuentaBancariaServicio(cuentaBancariaRepositorio, clienteRepositorio);
        cuotaPrestamoServicio = new CuotaPrestamoServicio(cuotaPrestamoRepositorio, prestamoRepositorio);
        movimientoCuentaServicio = new MovimientoCuentaServicio(movimientoCuentaRepositorio, cuentaBancariaRepositorio);
        pagoPrestamoServicio = new PagoPrestamoServicio(pagoPrestamoRepositorio, prestamoRepositorio);
        prestamoServicio = new PrestamoServicio(prestamoRepositorio, clienteRepositorio);
        productoFinacieroServicio = new ProductoFinancieroServicio(productoFinacieroRepositorio);
        sucursalServicio = new SucursalServicio(sucursalRepositorio);
        tarjetaServicio = new TarjetaServicio(tarjetaRepositorio, clienteRepositorio, cuentaBancariaRepositorio);
        transaccionCajeroServicio = new TransaccionCajeroServicio(transaccionCajeroRepositorio, cajeroRepositorio, cuentaBancariaRepositorio);
        transferenciaServicio = new TransferenciaServicio(transferenciaRepositorio, cuentaBancariaRepositorio);

    }

    private void inicializarControladores() {
        cajeroControlador = new CajeroControlador(cajeroServicio);
        clienteControlador = new ClienteControlador(clienteServicio);
        clienteProductoSucursalControlador = new ClienteProductoSucursalControlador(clienteProductoSucursalServicio);
        consumoTarjetaControlador = new ConsumoTarjetaControlador(consumoTarjetaServicio);
        cuentaBancariaControlador = new CuentaBancariaControlador(cuentaBancariaServicio);
        cuotaPrestamoControlador = new CuotaPrestamoControlador(cuotaPrestamoServicio);
        movimientoCuentaControlador = new MovimientoCuentaControlador(movimientoCuentaServicio);
        pagoPrestamoControlador = new PagoPrestamoControlador(pagoPrestamoServicio);
        prestamoControlador = new PrestamoControlador(prestamoServicio);
        productoFinacieroControlador = new ProductoFinancieroControlador(productoFinacieroServicio);
        sucursalControlador = new SucursalControlador(sucursalServicio);
        tarjetaControlador = new TarjetaControlador(tarjetaServicio);
        transaccionCajeroControlador = new TransaccionCajeroControlador(transaccionCajeroServicio);
        transferenciaControlador = new TransferenciaControlador(transferenciaServicio);
    }

}
