package org.banco.webconfig.ruta.cuentabancaria;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuentaBancariaListar extends ControladorBancolombia {
    
    @Override
    public void registrar(Router r) {
        r.get("/cuentasbancarias/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Listado de Cuentas Bancarias");
            modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cuentasbancarias/listar.html", modelo);
        });

    }

}
