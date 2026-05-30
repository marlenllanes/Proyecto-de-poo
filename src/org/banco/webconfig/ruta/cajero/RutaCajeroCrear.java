package org.banco.webconfig.ruta.cajero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cajero.CajeroCrearDto;
import org.banco.dto.cajero.CajeroDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCajeroCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/cajeros/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Cajero");
            modelo.put("listaSucursales", EnsambladorWeb.sucursal().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cajero/crear.html", modelo);
        });

        r.post("/cajeros/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);

                Optional<CajeroDto> resultado = EnsambladorWeb.cajero().crear(
                        new CajeroCrearDto(
                                 Integer.valueOf(formulario.get("sucursalCajero")),
                                formulario.get("nombreCajero"),
                                formulario.get("turnoCajero")
                        )
                );

                if (resultado.isPresent()) {
                    req.mensaje("exito", "Cajero registrado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar el cajero.");
                }

                return redireccionar("/cajeros/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/cajeros/crear");
            }
        });

    }

}