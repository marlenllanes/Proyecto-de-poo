package org.banco.webconfig.ruta.transferencias;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.transferencia.TransferenciaCrearDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransferenciaCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/transferencias/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Realizar Transferencia");
            modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("transferencias/crear.html", modelo);
        });
        r.post("/transferencias/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<TransferenciaCrearDto> resultado = EnsambladorWeb.transferencia().crear(
                        new TransferenciaCrearDto( 
                                Integer.parseInt(formulario.get("idCuentaOrigen")),
                                Integer.parseInt(formulario.get("idCuentaDestino")),
                                OffsetDateTime.parse(formulario.get("fechaTransferencia")),
                                new BigDecimal(formulario.get("valorTransferencia")),
                                formulario.get("descripcionTransferencia")
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Transferencia realizada con éxito.");
                } else {
                    req.mensaje("error", "Error: Verifique saldo o que las cuentas no sean iguales.");
                }
                return redireccionar("/transferencias/crear");
            } catch (Exception e) {
                req.mensaje("error", "Datos de entrada inválidos.");
                return redireccionar("/transferencias/crear");
            }
        });
    }
}
