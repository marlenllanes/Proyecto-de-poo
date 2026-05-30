package org.banco.webconfig.ruta.transferencia;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.transferencia.TransferenciaActualizarDto;
import org.banco.dto.transferencia.TransferenciaDto;
import org.banco.dto.transferencia.TransferenciaIdentificadorDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransferenciaEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/transferencias/editar/:idOrigen/:idDestino", req -> {
            try {
                int idOrigen = Integer.parseInt(req.param("idOrigen"));
                int idDestino = Integer.parseInt(req.param("idDestino"));
                TransferenciaIdentificadorDto id = new TransferenciaIdentificadorDto(idOrigen, idDestino);
                Optional<TransferenciaDto> transf = EnsambladorWeb.transferencia().obtenerUno(id);
                if (transf.isEmpty()) {
                    req.mensaje("error", "Transferencia no encontrada.");
                    return redireccionar("/transferencias/admin");
                }
                TransferenciaDto dto = transf.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Transferencia");
                modelo.put("idCuentaOrigen", dto.cuentaOrigen().idCuentaBancaria());
                modelo.put("idCuentaDestino", dto.cuentaDestino().idCuentaBancaria());
                modelo.put("fechaTransferencia", dto.fechaTransferencia());
                modelo.put("valorTransferencia", dto.valorTransferencia());
                modelo.put("descripcionTransferencia", dto.descripcionTransferencia());
                modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("transferencias/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "Error cargando la transferencia: " + e.getMessage());
                return redireccionar("/transferencias/admin");
            }
        });
        r.post("/transferencias/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int idTransferencia = Integer.parseInt(formulario.get("idTransferencia"));
                int idOrigen = Integer.parseInt(formulario.get("idCuentaOrigen"));
                int idDestino = Integer.parseInt(formulario.get("idCuentaDestino"));
                TransferenciaActualizarDto dto = new TransferenciaActualizarDto(
                        idTransferencia,
                        idOrigen,
                        idDestino,
                        OffsetDateTime.parse(formulario.get("fechaTransferencia")),
                        new BigDecimal(formulario.get("valorTransferencia")),
                        formulario.get("descripcionTransferencia")
                );
                Optional<TransferenciaActualizarDto> resultado = EnsambladorWeb.transferencia().actualizar(
                        new TransferenciaIdentificadorDto(idOrigen, idDestino), dto
                );

                if (resultado.isPresent()) {
                    req.mensaje("exito", "Transferencia actualizada correctamente.");
                    return redireccionar("/transferencias/admin");
                } else {
                    req.mensaje("error", "No se pudo actualizar la transferencia.");
                    return redireccionar("/transferencias/admin");
                }
            } catch (Exception e) {
                req.mensaje("error", "Error en los datos enviados.");
                return redireccionar("/transferencias/admin");
            }
        });

    }

}
