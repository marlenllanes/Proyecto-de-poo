package org.banco.webconfig.ruta.cliente;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/clientes/admin", req -> {//ruta
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Soy el que administra los clientes");
            modelo.put("cliente", EnsambladorWeb.cliente().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("clientes/admin.html", modelo);//pagina
        });
        r.get("/cliente/eliminar/:id", req -> {//ruta
            int codigo = Integer.parseInt(req.param("id"));
            boolean eliminado = EnsambladorWeb.cliente().eliminar(codigo);
            
            if (eliminado) {
                req.mensaje("exito","Uyyyyyy bien");
            } else {
                req.mensaje("error","nada que hacer");
            }
            return redireccionar("/clientes/admin");//pagina
        });
    }

}
