package org.banco.webconfig.ruta.prestamo;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPrestamoListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/prestamos/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Préstamos");
            modelo.put("listaPrestamos", EnsambladorWeb.prestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("prestamos/listar.html", modelo);
        });
    }
}
