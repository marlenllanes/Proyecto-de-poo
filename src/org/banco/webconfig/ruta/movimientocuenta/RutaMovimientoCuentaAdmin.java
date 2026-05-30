package org.banco.webconfig.ruta.movimientocuenta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaMovimientoCuentaAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/movimientos/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Movimientos");
            modelo.put("movimientos", EnsambladorWeb.movimientoCuenta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("movimientos/admin.html", modelo);
        });
        r.get("/movimientos/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.movimientoCuenta().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Movimiento eliminado correctamente.");
            } else {
                req.mensaje("error", "No se pudo eliminar el movimiento.");
            }
            return redireccionar("/movimientos/admin");
        });
    }
}
