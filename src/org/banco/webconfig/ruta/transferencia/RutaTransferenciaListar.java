package org.banco.webconfig.ruta.transferencia;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransferenciaListar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/transferencias/listar", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Historial de Transferencias");
            modelo.put("listaTransferencias", EnsambladorWeb.transferencia().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("transferencias/listar.html", modelo);
        });
    }
}
