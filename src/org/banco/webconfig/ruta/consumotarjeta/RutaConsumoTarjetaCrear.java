package org.banco.webconfig.ruta.consumotarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.consumotarjeta.ConsumoTarjetaCrearDto;
import org.banco.dto.consumotarjeta.ConsumoTarjetaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaConsumoTarjetaCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/consumostarjeta/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Consumo de Tarjeta");
            modelo.put("tarjetas", EnsambladorWeb.tarjeta().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("consumostarjeta/crear.html", modelo);
        });

        r.post("/consumostarjeta/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);

                OffsetDateTime fecha = null;
                if (formulario.get("fechaConsumoTarjeta") != null && !formulario.get("fechaConsumoTarjeta").isEmpty()) {
                    LocalDate date = LocalDate.parse(formulario.get("fechaConsumoTarjeta"));
                    fecha = date.atStartOfDay().atOffset(ZoneOffset.UTC);
                }

                Optional<ConsumoTarjetaDto> resultado = EnsambladorWeb.consumoTarjeta().crear(
                        new ConsumoTarjetaCrearDto(
                                Integer.parseInt(formulario.get("idTarjetaConsumoTarjeta")),
                                fecha,
                                formulario.get("establecimientoConsumoTarjeta"),
                                new BigDecimal(formulario.get("valorConsumoTarjeta")),
                                Integer.parseInt(formulario.get("cuotaConsumoTarjeta"))
                        )
                );

                if (resultado.isPresent()) {
                    req.mensaje("exito", "Consumo registrado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar el consumo.");
                }

                return redireccionar("/consumostarjeta/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/consumostarjeta/crear");
            }
        });

    }

}
