package org.banco.webconfig;

import com.cleandev.webserver.ruteo.RegistroRutas;
import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ruta.cajero.RutaCajeroAdmin;
import org.banco.webconfig.ruta.cajero.RutaCajeroCrear;
import org.banco.webconfig.ruta.cajero.RutaCajeroEditar;
import org.banco.webconfig.ruta.cajero.RutaCajeroListar;
import org.banco.webconfig.ruta.cliente.RutaClienteAdmin;
import org.banco.webconfig.ruta.cliente.RutaClienteCrear;
import org.banco.webconfig.ruta.cliente.RutaClienteEditar;
import org.banco.webconfig.ruta.cliente.RutaClienteListar;
import org.banco.webconfig.ruta.consumotarjeta.RutaConsumoTarjetaAdmin;
import org.banco.webconfig.ruta.consumotarjeta.RutaConsumoTarjetaCrear;
import org.banco.webconfig.ruta.consumotarjeta.RutaConsumoTarjetaEditar;
import org.banco.webconfig.ruta.consumotarjeta.RutaConsumoTarjetaListar;
import org.banco.webconfig.ruta.cuentabancaria.RutaCuentaBancariaAdmin;
import org.banco.webconfig.ruta.cuentabancaria.RutaCuentaBancariaCrear;
import org.banco.webconfig.ruta.cuentabancaria.RutaCuentaBancariaEditar;
import org.banco.webconfig.ruta.cuentabancaria.RutaCuentaBancariaListar;
import org.banco.webconfig.ruta.prestamo.RutaPrestamoAdmin;
import org.banco.webconfig.ruta.prestamo.RutaPrestamoCrear;
import org.banco.webconfig.ruta.prestamo.RutaPrestamoEditar;
import org.banco.webconfig.ruta.prestamo.RutaPrestamoListar;
import org.banco.webconfig.ruta.sucursal.RutaSucursalAdmin;
import org.banco.webconfig.ruta.sucursal.RutaSucursalCrear;
import org.banco.webconfig.ruta.sucursal.RutaSucursalEditar;
import org.banco.webconfig.ruta.sucursal.RutaSucursalListar;
import org.banco.webconfig.ruta.tarjeta.RutaTarjetaAdmin;
import org.banco.webconfig.ruta.tarjeta.RutaTarjetaCrear;
import org.banco.webconfig.ruta.tarjeta.RutaTarjetaEditar;
import org.banco.webconfig.ruta.tarjeta.RutaTarjetaListar;
import org.banco.webconfig.ruta.transaccioncajero.RutaTransaccionCajeroAdmin;
import org.banco.webconfig.ruta.transaccioncajero.RutaTransaccionCajeroCrear;
import org.banco.webconfig.ruta.transaccioncajero.RutaTransaccionCajeroEditar;
import org.banco.webconfig.ruta.transaccioncajero.RutaTransaccionCajeroListar;
import org.banco.webconfig.ruta.transferencias.RutaTransferenciaAdmin;
import org.banco.webconfig.ruta.transferencias.RutaTransferenciaCrear;
import org.banco.webconfig.ruta.transferencias.RutaTransferenciaEditar;
import org.banco.webconfig.ruta.transferencias.RutaTransferenciaListar;

public class Ruteo extends ControladorBancolombia
        implements RegistroRutas {

    private void registrarPaginasPrincipales(Router r) {
        r.get("/", req -> vista("index.html", modeloBase()));
        r.get("/acercade", req -> vista("acerca_de.html", modeloBase()));
    }

    private void registrarArchivosEstaticos(Router r) {
        r.get("/favicon.ico", req -> archivoEstatico("/assets/icon/favicon.ico"));
        r.get("/assets/*", req -> archivoEstatico(req.rutaSolicitada()));
        r.get("/public", req -> archivoEstatico(req.rutaSolicitada()));
    }

    @Override
    public void registrar(Router r) {
        registrarPaginasPrincipales(r);
        registrarArchivosEstaticos(r);

        new RutaClienteAdmin().registrar(r);
        new RutaClienteCrear().registrar(r);
        new RutaClienteEditar().registrar(r);
        new RutaClienteListar().registrar(r);

        new RutaCajeroAdmin().registrar(r);
        new RutaCajeroCrear().registrar(r);
        new RutaCajeroEditar().registrar(r);
        new RutaCajeroListar().registrar(r);

        new RutaConsumoTarjetaAdmin().registrar(r);
        new RutaConsumoTarjetaCrear().registrar(r);
        new RutaConsumoTarjetaEditar().registrar(r);
        new RutaConsumoTarjetaListar().registrar(r);

        new RutaCuentaBancariaAdmin().registrar(r);
        new RutaCuentaBancariaCrear().registrar(r);
        new RutaCuentaBancariaEditar().registrar(r);
        new RutaCuentaBancariaListar().registrar(r);

        new RutaPrestamoAdmin().registrar(r);
        new RutaPrestamoCrear().registrar(r);
        new RutaPrestamoEditar().registrar(r);
        new RutaPrestamoListar().registrar(r);

        new RutaTarjetaAdmin().registrar(r);
        new RutaTarjetaListar().registrar(r);
        new RutaTarjetaCrear().registrar(r);
        new RutaTarjetaEditar().registrar(r);

        new RutaSucursalAdmin().registrar(r);
        new RutaSucursalCrear().registrar(r);
        new RutaSucursalEditar().registrar(r);
        new RutaSucursalListar().registrar(r);

        new RutaTransaccionCajeroAdmin().registrar(r);
        new RutaTransaccionCajeroCrear().registrar(r);
        new RutaTransaccionCajeroEditar().registrar(r);
        new RutaTransaccionCajeroListar().registrar(r);
        
        new RutaTransferenciaAdmin().registrar(r);
        new RutaTransferenciaCrear().registrar(r);
        new RutaTransferenciaEditar().registrar(r);
        new RutaTransferenciaListar().registrar(r);

        setManejador404(req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Lo siento no se pudo encontrar");
            modelo.put("rutaSolicitada", req.rutaSolicitada());
            return vista("no_encontrada.html", modelo);
        });

    }

}
