package org.banco.webconfig.ruta.cuentabancaria;
import com.cleandev.webserver.ruteo.Router;
import java.util.List;
import java.util.Map;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuentaBancariaListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuentas/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Cuentas Bancarias");
            // 1. Obtenemos la lista original del servicio
            List<CuentaBancariaDto> originales = EnsambladorWeb.cuentaBancaria().obtenerTodos();
            // 2. Usamos el mapeador para convertir a filas planas
            FilaCuentaBancariaMapeador maper = new FilaCuentaBancariaMapeador();
            List<FilaCuentaBancariaDto> filas = maper.listarCuenta(originales);
            // 3. Pasamos la lista al modelo
            modelo.put("cuentas", filas);
            cargarMensajes(req, modelo);
            return vista("cuentaBancaria/listar.html", modelo);
        });
    }
}
