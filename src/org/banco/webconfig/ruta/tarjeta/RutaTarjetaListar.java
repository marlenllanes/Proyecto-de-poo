package org.banco.webconfig.ruta.tarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTarjetaListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/tarjetas/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Tarjetas");
            modelo.put("listaTarjetas", EnsambladorWeb.tarjeta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("tarjetas/listar.html", modelo);
        });
    }
}
