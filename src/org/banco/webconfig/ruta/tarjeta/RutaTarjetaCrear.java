package org.banco.webconfig.ruta.tarjeta;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import java.time.OffsetDateTime;
import org.banco.dto.tarjeta.TarjetaCrearDto;
import org.banco.dto.tarjeta.TarjetaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTarjetaCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/tarjetas/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Tarjeta");
            modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos());
            modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("tarjetas/crear.html", modelo);
        });

        r.post("/tarjetas/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<TarjetaDto> resultado = EnsambladorWeb.tarjeta().crear(
                        new TarjetaCrearDto(
                                formulario.get("numeroTarjeta"),
                                formulario.get("tipoTarjeta"),
                                OffsetDateTime.parse(formulario.get("fechaExpedicionTarjeta")),
                                OffsetDateTime.parse(formulario.get("fechaVencimientoTarjeta")),
                                formulario.get("estadoTarjeta"),
                                Integer.parseInt(formulario.get("idCliente")),
                                Integer.parseInt(formulario.get("idCuenta"))
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Tarjeta registrada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar la tarjeta.");
                }
                return redireccionar("/tarjetas/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/tarjetas/crear");
            }
        });
    }
}
