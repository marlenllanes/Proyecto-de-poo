package org.banco.webconfig;

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

public class EnsambladorWeb {

    private static CajeroControlador cajeroControlador;
    private static ClienteControlador clienteControlador;
    private static ClienteProductoSucursalControlador clienteProductoSucursalControlador;
    private static ConsumoTarjetaControlador consumoTarjetaControlador;
    private static CuentaBancariaControlador cuentaBancariaControlador;
    private static CuotaPrestamoControlador cuotaPrestamoControlador;
    private static MovimientoCuentaControlador movimientoCuentaControlador;
    private static PagoPrestamoControlador pagoPrestamoControlador;
    private static PrestamoControlador prestamoControlador;
    private static ProductoFinancieroControlador productoFinancieroControlador;
    private static SucursalControlador sucursalControlador;
    private static TarjetaControlador tarjetaControlador;
    private static TransaccionCajeroControlador transaccionCajeroControlador;
    private static TransferenciaControlador transferenciaControlador;

    public static void inicializador(
            CajeroControlador caje,
            ClienteControlador clie,
            ClienteProductoSucursalControlador cps,
            ConsumoTarjetaControlador conta,
            CuentaBancariaControlador cuba,
            CuotaPrestamoControlador cupe,
            MovimientoCuentaControlador mocu,
            PagoPrestamoControlador papre,
            PrestamoControlador pres,
            ProductoFinancieroControlador profi,
            SucursalControlador sucu,
            TarjetaControlador tarj,
            TransaccionCajeroControlador tranC,
            TransferenciaControlador trans
    ) {
        cajeroControlador = caje;
        clienteControlador = clie;
        clienteProductoSucursalControlador = cps;
        consumoTarjetaControlador = conta;
        cuentaBancariaControlador = cuba;
        cuotaPrestamoControlador = cupe;
        movimientoCuentaControlador = mocu;
        pagoPrestamoControlador = papre;
        prestamoControlador = pres;
       productoFinancieroControlador = profi;
        sucursalControlador = sucu;
        tarjetaControlador = tarj;
        transaccionCajeroControlador = tranC;
        transferenciaControlador = trans;

    }

    public static CajeroControlador cajeroControlador() {
        return cajeroControlador;
    }

    public static ClienteControlador clienteControlador() {
        return clienteControlador;
    }

    public static ClienteProductoSucursalControlador clienteProductoSucursalControlador() {
        return clienteProductoSucursalControlador;
    }

    public static ConsumoTarjetaControlador consumoTarjetaControlador() {
        return consumoTarjetaControlador;
    }

    public static CuentaBancariaControlador cuentaBancariaControlador() {
        return cuentaBancariaControlador;
    }

    public static CuotaPrestamoControlador cuotaPrestamoControlador() {
        return cuotaPrestamoControlador;
    }

    public static MovimientoCuentaControlador movimientoCuentaControlador() {
        return movimientoCuentaControlador;
    }

    public static PagoPrestamoControlador pagoPrestamoControlador() {
        return pagoPrestamoControlador;
    }

    public static PrestamoControlador prestamoControlador() {
        return prestamoControlador;
    }

  public static ProductoFinancieroControlador productoFinancieroControlador() {
    return productoFinancieroControlador;
}

    public static SucursalControlador sucursalControlador() {
        return sucursalControlador;
    }

    public static TarjetaControlador tarjetaControlador() {
        return tarjetaControlador;
    }

    public static TransaccionCajeroControlador transaccionCajeroControlador() {
        return transaccionCajeroControlador;
    }

    public static TransferenciaControlador transferenciaControlador() {
        return transferenciaControlador;
    }






}
