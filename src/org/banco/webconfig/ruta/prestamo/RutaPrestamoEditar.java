package org.banco.webconfig.ruta.prestamo;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.prestamo.PrestamoActualizarDto;
import org.banco.dto.prestamo.PrestamoDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPrestamoEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/prestamos/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<PrestamoDto> pres = EnsambladorWeb.prestamo().obtenerUno(codigo);
                if (pres.isEmpty()) {
                    req.mensaje("error", "El préstamo no existe.");
                    return redireccionar("/prestamos/admin");
                }
                PrestamoDto dto = pres.get(); // 
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Préstamo");
                modelo.put("idPrestamo", dto.idPrestamo());
                modelo.put("montoPrestamo", dto.montoPrestamo());
                modelo.put("tasaInteresPrestamo", dto.tasaInteresPrestamo());
                modelo.put("fechaDesembolsoPrestamo", dto.fechaDesembolsoPrestamo());
                modelo.put("estadoPrestamo", dto.estadoPrestamo());
                modelo.put("idCliente", dto.cliente().idCliente());
                modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("prestamos/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/prestamos/admin");
            }
        });

        r.post("/prestamos/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idPrestamo"));
                PrestamoActualizarDto dto = new PrestamoActualizarDto(id,
                        Integer.parseInt(formulario.get("idCliente")),
                        new BigDecimal(formulario.get("montoPrestamo")),
                        new BigDecimal(formulario.get("tasaInteresPrestamo")),
                        OffsetDateTime.parse(formulario.get("fechaDesembolsoPrestamo")),
                        formulario.get("estadoPrestamo")
                );
                Optional<PrestamoDto> resultado = EnsambladorWeb.prestamo().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Préstamo actualizado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar el préstamo.");
                }
                return redireccionar("/prestamos/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/prestamos/admin");
            }
        });
    }
}
