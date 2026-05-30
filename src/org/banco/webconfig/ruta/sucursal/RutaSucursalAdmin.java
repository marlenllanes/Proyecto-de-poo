package org.banco.webconfig.ruta.sucursal;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaSucursalAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/sucursales/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Sucursales");
            modelo.put("sucursales", EnsambladorWeb.sucursal().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("sucursal/admin.html", modelo);
        });
        r.get("/sucursales/eliminar/:id", req -> {
            int id = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.sucursal().eliminar(id);
            if (eliminado) {
                req.mensaje("exito", "Sucursal eliminada correctamente.");
            } else {                
                req.mensaje("error", "No se puede eliminar la sucursal si tiene cajeros asociados o productos adquiridos en ella.");
            }
            return redireccionar("/sucursales/admin");
        });
    }
}
