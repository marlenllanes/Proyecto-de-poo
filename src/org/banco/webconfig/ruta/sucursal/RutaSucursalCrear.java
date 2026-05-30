package org.banco.webconfig.ruta.sucursal;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.sucursal.SucursalCrearDto;
import org.banco.dto.sucursal.SucursalDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaSucursalCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/sucursales/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Sucursal");
            cargarMensajes(req, modelo);
            return vista("sucursal/crear.html", modelo);
        });
        r.post("/sucursales/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<SucursalDto> resultado = EnsambladorWeb.sucursal().crear(
                        new SucursalCrearDto(
                                formulario.get("nombreSucursal"),
                                formulario.get("direccionSucursal"),
                                formulario.get("telefonoSucursal"))
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Sucursal registrada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar la sucursal.");
                }
                return redireccionar("/sucursales/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/sucursales/crear");
            }
        });
    }
}
