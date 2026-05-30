package org.banco.webconfig.ruta.consumotarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaConsumoTarjetaAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/consumostarjeta/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Consumos de Tarjeta");
            modelo.put("consumos", EnsambladorWeb.consumoTarjeta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("consumostarjeta/admin.html", modelo);
        });

        r.get("/consumostarjeta/eliminar/:id", req -> {
            try {
                int id = Integer.parseInt(req.param("id"));
                boolean eliminado = EnsambladorWeb.consumoTarjeta().eliminar(id);
                if (eliminado) {
                    req.mensaje("exito", "Consumo eliminado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo eliminar el consumo.");
                }
            } catch (Exception e) {
                req.mensaje("error", "Ocurrió un error al intentar eliminar el consumo.");
            }
            return redireccionar("/consumostarjeta/admin");
        });

    }

}