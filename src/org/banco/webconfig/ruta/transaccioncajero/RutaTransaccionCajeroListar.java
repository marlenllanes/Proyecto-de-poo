package org.banco.webconfig.ruta.transaccioncajero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransaccionCajeroListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/transacciones/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Transacciones en Cajero");
            modelo.put("listaTransacciones", EnsambladorWeb.transaccionCajero().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("transacciones/listar.html", modelo);
        });
    }
}
