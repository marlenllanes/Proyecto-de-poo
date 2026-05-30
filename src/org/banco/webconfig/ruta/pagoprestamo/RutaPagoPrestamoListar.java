package org.banco.webconfig.ruta.pagoprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPagoPrestamoListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/pagos/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Pagos de Préstamo");
            modelo.put("listaPagos", EnsambladorWeb.pagoPrestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("pagos/listar.html", modelo);
        });
    }
}
