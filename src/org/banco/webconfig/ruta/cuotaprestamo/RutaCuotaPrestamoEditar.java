package org.banco.webconfig.ruta.cuotaprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cuotaprestamo.CuotaPrestamoActualizarDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuotaPrestamoEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuotas/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<CuotaPrestamoDto> cuo = EnsambladorWeb.cuotaPrestamo().obtenerUno(codigo);
                if (cuo.isEmpty()) {
                    req.mensaje("error", "La cuota no existe.");
                    return redireccionar("/cuotas/admin");
                }
                CuotaPrestamoDto dto = cuo.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Cuota de Préstamo");
                modelo.put("idCuotaPrestamo", dto.idCuotaPrestamo());
                modelo.put("numeroCuotaPrestamo", dto.numeroCuotaPrestamo());
                modelo.put("fechaVencimientoCuotaPrestamo", dto.fechaVencimientoCuotaPrestamo());
                modelo.put("valorCuotaPrestamo", dto.valorCuotaPrestamo());
                modelo.put("estadoCuotaPrestamo", dto.estadoCuotaPrestamo());
                modelo.put("idPrestamo", dto.prestamo().idPrestamo());
                modelo.put("prestamos", EnsambladorWeb.prestamo().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("cuotas/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/cuotas/admin");
            }
        });
        r.post("/cuotas/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idCuotaPrestamo"));
                CuotaPrestamoActualizarDto dto = new CuotaPrestamoActualizarDto(
                        id,
                        Integer.parseInt(formulario.get("idPrestamo")),
                        Integer.parseInt(formulario.get("numeroCuotaPrestamo")),
                        OffsetDateTime.parse(formulario.get("fechaVencimientoCuotaPrestamo")),
                        new BigDecimal(formulario.get("valorCuotaPrestamo")),
                        formulario.get("estadoCuotaPrestamo")
                );
                Optional<CuotaPrestamoDto> resultado = EnsambladorWeb.cuotaPrestamo().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Cuota actualizada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar la cuota.");
                }
                return redireccionar("/cuotas/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/cuotas/admin");
            }
        });
    }
}
