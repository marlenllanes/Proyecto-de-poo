package org.banco.webconfig.ruta.sucursal;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaSucursalListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/sucursales/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Sucursales");
            modelo.put("sucursal", EnsambladorWeb.sucursal().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("sucursal/listar.html", modelo);
        });
    }
}
