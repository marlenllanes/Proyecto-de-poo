package org.banco.webconfig.ruta.pagoprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.pagoprestamo.PagoPrestamoCrearDto;
import org.banco.dto.pagoprestamo.PagoPrestamoDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPagoPrestamoCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/pagos/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Pago de Préstamo");
            modelo.put("prestamos", EnsambladorWeb.prestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("pagos/crear.html", modelo);
        });
        r.post("/pagos/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<PagoPrestamoDto> resultado = EnsambladorWeb.pagoPrestamo().crear(
                        new PagoPrestamoCrearDto(
                                Integer.parseInt(formulario.get("idPrestamo")),
                                OffsetDateTime.parse(formulario.get("fechaPagoPrestamo")),
                                new BigDecimal(formulario.get("valorPagoPrestamo")),
                                formulario.get("metodoPagoPrestamo")
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Pago registrado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar el pago.");
                }
                return redireccionar("/pagos/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/pagos/crear");
            }
        });
    }
}
