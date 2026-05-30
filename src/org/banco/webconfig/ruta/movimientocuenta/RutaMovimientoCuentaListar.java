package org.banco.webconfig.ruta.movimientocuenta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaMovimientoCuentaListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/movimientos/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Movimientos de Cuenta");
            modelo.put("listaMovimientos", EnsambladorWeb.movimientoCuenta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("movimientos/listar.html", modelo);
        });
    }
}
