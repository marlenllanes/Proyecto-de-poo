package org.banco.webconfig.ruta.clienteproductosucursal;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteProductoSucursalListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/clienteproductosucursal/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Adquisiciones");
            modelo.put("listaAdquisiciones", EnsambladorWeb.clienteProductoSucursal().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("clienteproductosucursal/listar.html", modelo);
        });
    }
}
