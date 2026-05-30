package org.banco.webconfig.ruta.prestamo;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPrestamoAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/prestamos/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Préstamos");
            modelo.put("prestamos", EnsambladorWeb.prestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("prestamos/admin.html", modelo);
        });
        r.get("/prestamos/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.prestamo().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Préstamo eliminado correctamente.");
            } else {
                req.mensaje("error", "No se puede eliminar el préstamo (posibles pagos asociados o error interno).");
            }
            return redireccionar("/prestamos/admin");
        });
    }
}
