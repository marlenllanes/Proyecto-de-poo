package org.banco.webconfig.ruta.consumotarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.consumotarjeta.ConsumoTarjetaActualizarDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaConsumoTarjetaEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/consumostarjeta/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<ConsumoTarjetaDto> cons = EnsambladorWeb.consumoTarjeta().obtenerUno(codigo);
                if (cons.isEmpty()) {
                    req.mensaje("error", "El consumo no existe.");
                    return redireccionar("/consumostarjeta/admin");
                }
                ConsumoTarjetaDto dto = cons.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Consumo de Tarjeta");
                modelo.put("idConsumoTarjeta", dto.idConsumoTarjeta());
                modelo.put("idTarjetaConsumoTarjeta", dto.tarjeta());
                modelo.put("fechaConsumoTarjeta", dto.fechaConsumoTarjeta());
                modelo.put("establecimientoConsumoTarjeta", dto.establecimientoConsumoTarjeta());
                modelo.put("valorConsumoTarjeta", dto.valorConsumoTarjeta());
                modelo.put("cuotaConsumoTarjeta", dto.cuotaConsumoTarjeta());
                modelo.put("tarjetas", EnsambladorWeb.tarjeta().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("consumostarjeta/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/consumostarjeta/admin");
            }
        });

        r.post("/consumostarjeta/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idConsumoTarjeta"));

                OffsetDateTime fecha = null;
                if (formulario.get("fechaConsumoTarjeta") != null && !formulario.get("fechaConsumoTarjeta").isEmpty()) {
                    LocalDate date = LocalDate.parse(formulario.get("fechaConsumoTarjeta"));
                    fecha = date.atStartOfDay().atOffset(ZoneOffset.UTC);
                }

                ConsumoTarjetaActualizarDto dto = new ConsumoTarjetaActualizarDto(
                        id,
                        Integer.parseInt(formulario.get("idTarjetaConsumoTarjeta")),
                        fecha,
                        formulario.get("establecimientoConsumoTarjeta"),
                        new BigDecimal(formulario.get("valorConsumoTarjeta")),
                        Integer.parseInt(formulario.get("cuotaConsumoTarjeta"))
                );
                Optional<ConsumoTarjetaDto> resultado = EnsambladorWeb.consumoTarjeta().actualizar(id, dto);

                if (resultado.isPresent()) {
                    req.mensaje("exito", "Consumo actualizado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar el consumo.");
                }

                return redireccionar("/consumostarjeta/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/consumostarjeta/admin");
            }
        });

    }

}