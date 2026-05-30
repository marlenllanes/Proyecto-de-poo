package org.banco.webconfig.ruta.movimientocuenta;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.movimientocuenta.MovimientoCuentaCrearDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaMovimientoCuentaCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/movimientos/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Movimiento");
            modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("movimientos/crear.html", modelo);
        });
        r.post("/movimientos/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<MovimientoCuentaDto> resultado = EnsambladorWeb.movimientoCuenta().crear(
                        new MovimientoCuentaCrearDto(  
                                Integer.parseInt(formulario.get("idCuenta")),
                                formulario.get("tipoMovimientoCuenta"),
                                new BigDecimal(formulario.get("valorMovimientoCuenta")),
                                OffsetDateTime.parse(formulario.get("fechaMovimientoCuenta")),
                                new BigDecimal(formulario.get("saldoPosteriorMovimientoCuenta"))
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Movimiento registrado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar el movimiento. Verifique que haya saldo suficiente si es un retiro.");
                }
                return redireccionar("/movimientos/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/movimientos/crear");
            }
        });
    }
}
