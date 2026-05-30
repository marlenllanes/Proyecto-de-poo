package org.banco.webconfig.ruta.tarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTarjetaAdmin extends ControladorBancolombia {
    
    @Override
    public void registrar(Router r) {
        r.get("/tarjetas/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Tarjetas");
            modelo.put("tarjetas", EnsambladorWeb.tarjeta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("tarjetas/admin.html", modelo);
        });
        r.get("/tarjetas/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.tarjeta().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Tarjeta eliminada correctamente.");
            } else {                
                req.mensaje("error", "No se puede eliminar la tarjeta porque tiene consumos registrados.");
            }
            return redireccionar("/tarjetas/admin");
        });
    }
}