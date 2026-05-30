package org.banco.webconfig.ruta.transferencias;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.dto.transferencia.TransferenciaIdentificadorDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransferenciaAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/transferencias/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Transferencias");
            modelo.put("transferencias", EnsambladorWeb.transferencia().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("transferencias/admin.html", modelo);
        });
        r.get("/transferencias/eliminar/:idOrigen/:idDestino", req -> {
            try {
                int idOrigen = Integer.parseInt(req.param("idOrigen"));
                int idDestino = Integer.parseInt(req.param("idDestino"));
                boolean eliminado = EnsambladorWeb.transferencia()
                        .eliminar(new TransferenciaIdentificadorDto(idOrigen, idDestino)
                );
                if (eliminado) {
                    req.mensaje("exito", "Transferencia eliminada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo eliminar la transferencia.");
                }
            } catch (Exception e) {
                req.mensaje("error", "Ocurrió un error al intentar eliminar la transferencia.");
            }
            return redireccionar("/transferencias/admin");
        });
    }

}
