package org.banco.webconfig.ruta.transaccioncajero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransaccionCajeroAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/transacciones/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Transacciones");
            modelo.put("transacciones", EnsambladorWeb.transaccionCajero().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("transacciones/admin.html", modelo);
        });
        r.get("/transacciones/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.transaccionCajero().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Transacción eliminada.");
            } else {
                req.mensaje("error", "No se pudo eliminar la transacción.");
            }
            return redireccionar("/transacciones/admin");
        });
    }
}
