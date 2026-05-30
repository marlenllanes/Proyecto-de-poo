package org.banco.webconfig.ruta.movimientocuenta;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.movimientocuenta.MovimientoCuentaActualizarDto;
import org.banco.dto.movimientocuenta.MovimientoCuentaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaMovimientoCuentaEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/movimientos/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<MovimientoCuentaDto> mov = EnsambladorWeb.movimientoCuenta().obtenerUno(codigo);
                if (mov.isEmpty()) {
                    req.mensaje("error", "El movimiento no existe.");
                    return redireccionar("/movimientos/admin");
                }
                MovimientoCuentaDto dto = mov.get(); // 
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Movimiento");
                modelo.put("idMovimientoCuenta", dto.idMovimientoCuenta());
                modelo.put("tipoMovimientoCuenta", dto.tipoMovimientoCuenta());
                modelo.put("valorMovimientoCuenta", dto.valorMovimientoCuenta());
                modelo.put("fechaMovimientoCuenta", dto.fechaMovimientoCuenta());
                modelo.put("saldoPosteriorMovimientoCuenta", dto.saldoPosteriorMovimientoCuenta());
                modelo.put("idCuenta", dto.cuenta().idCuentaBancaria());
                modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("movimientos/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/movimientos/admin");
            }
        });
        r.post("/movimientos/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idMovimientoCuenta"));
                MovimientoCuentaActualizarDto dto = new MovimientoCuentaActualizarDto(
                        id,
                        Integer.parseInt(formulario.get("idCuenta")),
                        formulario.get("tipoMovimientoCuenta"),
                        new BigDecimal(formulario.get("valorMovimientoCuenta")),
                        OffsetDateTime.parse(formulario.get("fechaMovimientoCuenta")),
                        new BigDecimal(formulario.get("saldoPosteriorMovimientoCuenta"))
                );
                Optional<MovimientoCuentaDto> resultado = EnsambladorWeb.movimientoCuenta().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Movimiento actualizado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar el movimiento.");
                }
                return redireccionar("/movimientos/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/movimientos/admin");
            }
        });
    }
}
