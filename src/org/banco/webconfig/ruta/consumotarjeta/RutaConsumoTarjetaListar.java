package org.banco.webconfig.ruta.consumotarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaConsumoTarjetaListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/consumostarjeta/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Consumos de Tarjeta");
            modelo.put("consumos", EnsambladorWeb.consumoTarjeta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("consumostarjeta/listar.html", modelo);
        });

    }

}