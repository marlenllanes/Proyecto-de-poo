package org.banco.webconfig.ruta.cliente;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/clientes/listar", req -> {//ruta
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de los clientes");
            modelo.put("cliente",EnsambladorWeb.cliente().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("clientes/listar.html", modelo);//pagina
        });
    }

}
