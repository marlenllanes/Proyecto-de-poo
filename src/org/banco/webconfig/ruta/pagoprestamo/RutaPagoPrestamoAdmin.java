package org.banco.webconfig.ruta.pagoprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPagoPrestamoAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/pagos/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Pagos");
            modelo.put("pagos", EnsambladorWeb.pagoPrestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("pagos/admin.html", modelo);
        });
        r.get("/pagos/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.pagoPrestamo().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Pago eliminado correctamente.");
            } else {
                req.mensaje("error", "No se pudo eliminar el pago.");
            }
            return redireccionar("/pagos/admin");
        });
    }
}
