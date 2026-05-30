package org.banco.webconfig.ruta.prestamo;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.prestamo.PrestamoCrearDto;
import org.banco.dto.prestamo.PrestamoDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaPrestamoCrear extends ControladorBancolombia {
    @Override
    public void registrar(Router r) {
        r.get("/prestamos/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Préstamo");            
            modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos()); 
            cargarMensajes(req, modelo);
            return vista("prestamos/crear.html", modelo);
        });
        r.post("/prestamos/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<PrestamoDto> resultado = EnsambladorWeb.prestamo().crear(
                    new PrestamoCrearDto( 
                        Integer.parseInt(formulario.get("idCliente")),
                        new BigDecimal(formulario.get("montoPrestamo")),
                        new BigDecimal(formulario.get("tasaInteresPrestamo")),
                        OffsetDateTime.parse(formulario.get("fechaDesembolsoPrestamo")),
                        formulario.get("estadoPrestamo")
                    )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Préstamo registrado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar el préstamo.");
                }
                return redireccionar("/prestamos/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/prestamos/crear");
            }
        });
    }
}