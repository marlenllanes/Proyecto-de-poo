package org.banco.webconfig.ruta.cajero;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cajero.CajeroActualizarDto;
import org.banco.dto.cajero.CajeroDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCajeroEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/cajero/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<CajeroDto> caje = EnsambladorWeb.cajero().obtenerUno(codigo);
                if (caje.isEmpty()) {
                    req.mensaje("error", "El cajero no existe.");
                    return redireccionar("/cajeros/admin");
                }
                CajeroDto dto = caje.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Cajero");
                modelo.put("idCajero", dto.idCajero());
                modelo.put("idSucursalCajero", dto.idSucursalCajero());
                modelo.put("nombreCajero", dto.nombreCajero());
                modelo.put("turnoCajero", dto.turnoCajero());
                modelo.put("listaSucursales", EnsambladorWeb.sucursal().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("cajero/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/cajeros/admin");
            }
        });

        r.post("/cajero/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idCajero"));
                CajeroActualizarDto dto = new CajeroActualizarDto(
                        id,
                         Integer.valueOf(formulario.get("sucursalCajero")),
                        formulario.get("nombreCajero"),
                        formulario.get("turnoCajero")
                );
                Optional<CajeroDto> resultado = EnsambladorWeb.cajero().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Cajero actualizado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar el cajero.");
                }
                return redireccionar("/cajeros/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/cajeros/admin");
            }
        });

    }

}