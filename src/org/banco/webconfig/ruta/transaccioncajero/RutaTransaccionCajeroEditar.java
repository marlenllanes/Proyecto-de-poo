package org.banco.webconfig.ruta.transaccioncajero;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.transaccioncajero.TransaccionCajeroActualizarDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransaccionCajeroEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/transacciones/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<TransaccionCajeroDto> trans = EnsambladorWeb.transaccionCajero().obtenerUno(codigo);
                if (trans.isEmpty()) {
                    req.mensaje("error", "Transacción no encontrada.");
                    return redireccionar("/transacciones/admin");
                }
                TransaccionCajeroDto dto = trans.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Transacción");
                modelo.put("idTransaccionCajero", dto.idTransaccionCajero());
                modelo.put("tipoTransaccionCajero", dto.tipoTransaccionCajero());
                modelo.put("valorTransaccionCajero", dto.valorTransaccionCajero());
                modelo.put("fechaTransaccionCajero", dto.fechaTransaccionCajero());
                modelo.put("idCajero", dto.cajero().idCajero());
                modelo.put("idCuenta", dto.cuenta().idCuentaBancaria());
                modelo.put("cajeros", EnsambladorWeb.cajero().obtenerTodos());
                modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("transacciones/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/transacciones/admin");
            }
        });
        r.post("/transacciones/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idTransaccionCajero"));
                TransaccionCajeroActualizarDto dto = new TransaccionCajeroActualizarDto( // 
                        id,
                        Integer.parseInt(formulario.get("idCajero")),
                        Integer.parseInt(formulario.get("idCuenta")),
                        formulario.get("tipoTransaccionCajero"),
                        new BigDecimal(formulario.get("valorTransaccionCajero")),
                        OffsetDateTime.parse(formulario.get("fechaTransaccionCajero"))
                );
                Optional<TransaccionCajeroDto> resultado = EnsambladorWeb.transaccionCajero().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Transacción actualizada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar la transacción.");
                }
                return redireccionar("/transacciones/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Error en los datos.");
                return redireccionar("/transacciones/admin");
            }
        });
    }
}
