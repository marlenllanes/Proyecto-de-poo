package org.banco.webconfig.ruta.pagoprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.pagoprestamo.PagoPrestamoActualizarDto;
import org.banco.dto.pagoprestamo.PagoPrestamoDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPagoPrestamoEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/pagos/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<PagoPrestamoDto> pago = EnsambladorWeb.pagoPrestamo().obtenerUno(codigo);
                if (pago.isEmpty()) {
                    req.mensaje("error", "El pago no existe.");
                    return redireccionar("/pagos/admin");
                }
                PagoPrestamoDto dto = pago.get(); // 
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Pago");
                modelo.put("idPagoPrestamo", dto.idPagoPrestamo());
                modelo.put("fechaPagoPrestamo", dto.fechaPagoPrestamo());
                modelo.put("valorPagoPrestamo", dto.valorPagoPrestamo());
                modelo.put("metodoPagoPrestamo", dto.metodoPagoPrestamo());
                modelo.put("idPrestamo", dto.prestamo().idPrestamo());
                modelo.put("prestamos", EnsambladorWeb.prestamo().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("pagos/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/pagos/admin");
            }
        });
        r.post("/pagos/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idPagoPrestamo"));
                PagoPrestamoActualizarDto dto = new PagoPrestamoActualizarDto( 
                        id,
                        Integer.parseInt(formulario.get("idPrestamo")),
                        OffsetDateTime.parse(formulario.get("fechaPagoPrestamo")),
                        new BigDecimal(formulario.get("valorPagoPrestamo")),
                        formulario.get("metodoPagoPrestamo")
                );
                Optional<PagoPrestamoDto> resultado = EnsambladorWeb.pagoPrestamo().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Pago actualizado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar el pago.");
                }
                return redireccionar("/pagos/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/pagos/admin");
            }
        });
    }
}
