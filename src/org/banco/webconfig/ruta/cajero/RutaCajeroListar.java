package org.banco.webconfig.ruta.cajero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCajeroListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cajeros/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Cajeros");
            modelo.put("listaCajeros", EnsambladorWeb.cajero().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cajeros/listar.html", modelo);
        });

    }

}
