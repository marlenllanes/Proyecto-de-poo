package org.banco.webconfig.ruta.productofinanciero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaProductoFinancieroAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/productos/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Productos Financieros");
            modelo.put("productos", EnsambladorWeb.productoFinanciero().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("productos/admin.html", modelo);
        });
        r.get("/productos/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.productoFinanciero().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Producto financiero eliminado correctamente.");
            } else {
                req.mensaje("error", "No se puede eliminar el producto financiero si algún cliente lo ha adquirido."); // 
            }
            return redireccionar("/productos/admin");
        });
    }
}
