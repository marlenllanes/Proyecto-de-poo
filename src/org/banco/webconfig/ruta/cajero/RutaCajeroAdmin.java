package org.banco.webconfig.ruta.cajero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCajeroAdmin extends ControladorBancolombia{
    
     @Override
    public void registrar(Router r) {

        r.get("/cajeros/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Cajeros");
            modelo.put("cajeros", EnsambladorWeb.cajero().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cajeros/admin.html", modelo);
        });
        
         r.get("/cajeros/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.cajero().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Cajero eliminado correctamente.");
            } else {
                req.mensaje("error", "No se puede eliminar el cajero porque tiene transacciones registradas.");
            }
            return redireccionar("/cajeros/admin");
        });

    }

}
