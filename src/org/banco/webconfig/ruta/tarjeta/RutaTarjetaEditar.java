package org.banco.webconfig.ruta.tarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import java.time.OffsetDateTime;
import org.banco.dto.tarjeta.TarjetaActualizarDto;
import org.banco.dto.tarjeta.TarjetaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTarjetaEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/tarjetas/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<TarjetaDto> tar = EnsambladorWeb.tarjeta().obtenerUno(codigo);
                if (tar.isEmpty()) {
                    req.mensaje("error", "La tarjeta no existe.");
                    return redireccionar("/tarjetas/admin");
                }
                TarjetaDto dto = tar.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Tarjeta");
                modelo.put("idTarjeta", dto.idTarjeta());
                modelo.put("numeroTarjeta", dto.numeroTarjeta());
                modelo.put("tipoTarjeta", dto.tipoTarjeta());
                modelo.put("fechaExpedicionTarjeta", dto.fechaExpedicionTarjeta());
                modelo.put("fechaVencimientoTarjeta", dto.fechaVencimientoTarjeta());
                modelo.put("estadoTarjeta", dto.estadoTarjeta());                
                modelo.put("idCliente", dto.cliente() != null ? dto.cliente().idCliente() : "");
                modelo.put("idCuenta", dto.cuenta() != null ? dto.cuenta().idCuentaBancaria() : "");
                modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos());
                modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("tarjetas/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/tarjetas/admin");
            }
        });
        r.post("/tarjetas/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idTarjeta"));
                TarjetaActualizarDto dto = new TarjetaActualizarDto( // 
                        id,
                        formulario.get("numeroTarjeta"),
                        formulario.get("tipoTarjeta"),
                        OffsetDateTime.parse(formulario.get("fechaExpedicionTarjeta")),
                        OffsetDateTime.parse(formulario.get("fechaVencimientoTarjeta")),
                        formulario.get("estadoTarjeta"),
                        Integer.parseInt(formulario.get("idCliente")),
                        Integer.parseInt(formulario.get("idCuenta"))
                );
                Optional<TarjetaDto> resultado = EnsambladorWeb.tarjeta().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Tarjeta actualizada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar la tarjeta.");
                }
                return redireccionar("/tarjetas/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/tarjetas/admin");
            }
        });
    }
}
