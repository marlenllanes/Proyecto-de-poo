package org.banco.webconfig.ruta.sucursal;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.sucursal.SucursalActualizarDto;
import org.banco.dto.sucursal.SucursalDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaSucursalEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/sucursal/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<SucursalDto> suc = EnsambladorWeb.sucursal().obtenerUno(codigo);
                if (suc.isEmpty()) {
                    req.mensaje("error", "La sucursal no existe.");
                    return redireccionar("/sucursales/admin");
                }

                SucursalDto dto = suc.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Sucursal");
                modelo.put("idSucursal", dto.idSucursal());
                modelo.put("nombreSucursal", dto.nombreSucursal());
                modelo.put("direccionSucursal", dto.direccionSucursal());
                modelo.put("telefonoSucursal", dto.telefonoSucursal());

                cargarMensajes(req, modelo);
                return vista("sucursal/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/sucursales/admin");
            }
        });

        r.post("/sucursal/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idSucursal"));
                SucursalActualizarDto dto = new SucursalActualizarDto(
                        id,
                        formulario.get("nombreSucursal"),
                        formulario.get("direccionSucursal"),
                        formulario.get("telefonoSucursal")
                );
                Optional<SucursalDto> resultado = EnsambladorWeb.sucursal().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Sucursal actualizada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar la sucursal.");
                }
                return redireccionar("/sucursal/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/sucursales/admin");
            }
        }
        );
    }
}
