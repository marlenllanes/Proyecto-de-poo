package org.banco.webconfig.ruta.cuentabancaria;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuentaBancariaAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuentasbancarias/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Cuentas Bancarias");
            modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cuentasbancarias/admin.html", modelo);
        });

        r.get("/cuentasbancarias/eliminar/:id", req -> {
            try {
                int id = Integer.parseInt(req.param("id"));
                boolean eliminado = EnsambladorWeb.cuentaBancaria().eliminar(id);
                if (eliminado) {
                    req.mensaje("exito", "Cuenta bancaria eliminada correctamente.");
                } else {
                    req.mensaje("error", "No se puede eliminar la cuenta porque tiene movimientos o transferencias asociadas.");
                }
            } catch (Exception e) {
                req.mensaje("error", "Ocurrió un error al intentar eliminar la cuenta.");
            }
            return redireccionar("/cuentasbancarias/admin");
        });

    }

}
