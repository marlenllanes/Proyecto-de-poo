package org.banco.webconfig;

import com.cleandev.webserver.ruteo.RutaControlador;
import com.cleandev.webserver.servidor.Peticion;
import java.util.HashMap;
import java.util.Map;

public abstract class ControladorBancolmbia extends RutaControlador{

    protected Map<String, Object> modeloBase() {
        Map<String, Object> miModelo = new HashMap<>();
        miModelo.put("creador", "yo");
        miModelo.put("logo_src", "/img/logo.png");
        miModelo.put("logo_alt", "imagen perdida");
        miModelo.put("logo_text", "bancolombia");
        return miModelo;
    }

    protected void cargarMensajes(Peticion req,
            Map<String, Object> modelo) {
        Object exito = req.mensaje("exito");
        Object error = req.mensaje("error");

        if (exito != null) {
            modelo.put("msgExito", exito);
        }
        if (error != null) {
            modelo.put("msgError", error);
        }
    }
}
