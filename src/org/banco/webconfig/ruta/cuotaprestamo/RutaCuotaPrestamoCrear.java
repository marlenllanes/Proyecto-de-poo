package org.banco.webconfig.ruta.cuotaprestamo;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cuotaprestamo.CuotaPrestamoCrearDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuotaPrestamoCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuotas/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Cuota de Préstamo");
            modelo.put("prestamos", EnsambladorWeb.prestamo().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cuotas/crear.html", modelo);
        });
        r.post("/cuotas/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<CuotaPrestamoDto> resultado = EnsambladorWeb.cuotaPrestamo().crear(
                        new CuotaPrestamoCrearDto(
                                Integer.parseInt(formulario.get("idPrestamo")),
                                Integer.parseInt(formulario.get("numeroCuotaPrestamo")),
                                OffsetDateTime.parse(formulario.get("fechaVencimientoCuotaPrestamo")),
                                new BigDecimal(formulario.get("valorCuotaPrestamo")),
                                formulario.get("estadoCuotaPrestamo")
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Cuota registrada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar la cuota.");
                }
                return redireccionar("/cuotas/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/cuotas/crear");
            }
        });
    }
}
