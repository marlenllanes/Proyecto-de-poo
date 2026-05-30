package org.banco.webconfig.ruta.productofinanciero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaProductoFinancieroListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/productos/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Productos Financieros");
            modelo.put("listaProductos", EnsambladorWeb.productoFinanciero().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("productos/listar.html", modelo);
        });
    }
}
