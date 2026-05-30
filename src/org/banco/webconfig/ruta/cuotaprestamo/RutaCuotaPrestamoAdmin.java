package org.banco.webconfig.ruta.cuotaprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuotaPrestamoAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuotas/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Cuotas");
            modelo.put("cuotas", EnsambladorWeb.cuotaPrestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cuotas/admin.html", modelo);
        });
        r.get("/cuotas/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.cuotaPrestamo().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Cuota eliminada correctamente.");
            } else {
                req.mensaje("error", "No se pudo eliminar la cuota.");
            }
            return redireccionar("/cuotas/admin");
        });
    }
}
