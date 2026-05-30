package org.banco.webconfig.ruta.cuentabancaria;
import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cuentabancaria.CuentaBancariaCrearDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;
public class RutaCuentaBancariaCrear extends ControladorBancolombia {
    @Override
    public void registrar(Router r) {
        r.get("/cuentas/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Cuenta Bancaria");
            modelo.put("listaClientes", EnsambladorWeb.cliente().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("cuentaBancaria/crear.html", modelo);
        });
        r.post("/cuentas/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                OffsetDateTime fechaAperturaCuentaBancaria = null;
                if (formulario.get("fechaAperturaCuentaBancaria") != null && !formulario.get("fechaAperturaCuentaBancaria").isEmpty()) {
                    LocalDate date = LocalDate.parse(formulario.get("fechaAperturaCuentaBancaria"));
                    fechaAperturaCuentaBancaria = date.atStartOfDay().atOffset(ZoneOffset.UTC);
                } else {
                    fechaAperturaCuentaBancaria = OffsetDateTime.now(ZoneOffset.UTC);
                }
                Optional<CuentaBancariaDto> resultado = EnsambladorWeb.cuentaBancaria().crear(
                        new CuentaBancariaCrearDto(
                                formulario.get("numeroCuentaBancaria"),
                                formulario.get("tipoCuentaBancaria"),
                                new BigDecimal(formulario.get("saldoCuentaBancaria")),
                                fechaAperturaCuentaBancaria,
                                formulario.get("estadoCuentaBancaria"),
                                Integer.parseInt(formulario.get("idCliente"))
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Cuenta bancaria registrada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar la cuenta bancaria.");
                }
                return redireccionar("/cuentas/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/cuentas/crear");
            }
        });
    }
}