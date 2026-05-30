package org.banco.webconfig.ruta.transaccioncajero;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.transaccioncajero.TransaccionCajeroCrearDto;
import org.banco.dto.transaccioncajero.TransaccionCajeroDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaTransaccionCajeroCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/transacciones/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Transacción");
            modelo.put("cajeros", EnsambladorWeb.cajero().obtenerTodos());
            modelo.put("cuentas", EnsambladorWeb.cuentaBancaria().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("transacciones/crear.html", modelo);
        });
        r.post("/transacciones/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<TransaccionCajeroDto> resultado = EnsambladorWeb.transaccionCajero().crear(
                        new TransaccionCajeroCrearDto( 
                                Integer.parseInt(formulario.get("idCajero")),
                                Integer.parseInt(formulario.get("idCuenta")),
                                formulario.get("tipoTransaccionCajero"),
                                new BigDecimal(formulario.get("valorTransaccionCajero")),
                                OffsetDateTime.parse(formulario.get("fechaTransaccionCajero"))
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Transacción registrada con éxito.");
                } else {
                    req.mensaje("error", "No se pudo registrar la transacción.");
                }
                return redireccionar("/transacciones/crear");
            } catch (Exception e) {
                req.mensaje("error", "Formato de datos inválido.");
                return redireccionar("/transacciones/crear");
            }
        });
    }
}
