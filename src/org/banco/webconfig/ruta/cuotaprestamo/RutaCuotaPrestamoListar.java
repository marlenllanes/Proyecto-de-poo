package org.banco.webconfig.ruta.cuotaprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuotaPrestamoListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuotas/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Cuotas de Préstamo");
            modelo.put("listaCuotas", EnsambladorWeb.cuotaPrestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cuotas/listar.html", modelo);
        });
    }
}
