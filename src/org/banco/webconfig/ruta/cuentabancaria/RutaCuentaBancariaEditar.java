package org.banco.webconfig.ruta.cuentabancaria;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cuentabancaria.CuentaBancariaActualizarDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuentaBancariaEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {

        r.get("/cuentasbancarias/editar/:id", req -> {
            try {
                int id = Integer.parseInt(req.param("id"));
                Optional<CuentaBancariaDto> cuenta = EnsambladorWeb.cuentaBancaria().obtenerUno(id);
                if (cuenta.isEmpty()) {
                    req.mensaje("error", "La cuenta bancaria no existe.");
                    return redireccionar("/cuentasbancarias/admin");
                }
                CuentaBancariaDto dto = cuenta.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Cuenta Bancaria");
                modelo.put("idCuentaBancaria", dto.idCuentaBancaria());
                modelo.put("numeroCuentaBancaria", dto.numeroCuentaBancaria());
                modelo.put("tipoCuentaBancaria", dto.tipoCuentaBancaria());
                modelo.put("saldoCuentaBancaria", dto.saldoCuentaBancaria());
                modelo.put("fechaAperturaCuentaBancaria", dto.fechaAperturaCuentaBancaria());
                modelo.put("estadoCuentaBancaria", dto.estadoCuentaBancaria());
                modelo.put("idCliente", dto.cliente().idCliente());
                modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("cuentasbancarias/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/cuentasbancarias/admin");
            }
        });

        r.post("/cuentasbancarias/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idCuentaBancaria"));

                OffsetDateTime fechaApertura = null;
                if (formulario.get("fechaAperturaCuentaBancaria") != null && !formulario.get("fechaAperturaCuentaBancaria").isEmpty()) {
                    LocalDate date = LocalDate.parse(formulario.get("fechaAperturaCuentaBancaria"));
                    fechaApertura = date.atStartOfDay().atOffset(ZoneOffset.UTC);
                }

                CuentaBancariaActualizarDto dto = new CuentaBancariaActualizarDto(
                        id,
                        formulario.get("numeroCuentaBancaria"),
                        formulario.get("tipoCuentaBancaria"),
                        new BigDecimal(formulario.get("saldoCuentaBancaria")),
                        fechaApertura,
                        formulario.get("estadoCuentaBancaria"),
                        Integer.parseInt(formulario.get("idCliente"))
                );

                Optional<CuentaBancariaDto> resultado = EnsambladorWeb.cuentaBancaria().actualizar(id, dto);

                if (resultado.isPresent()) {
                    req.mensaje("exito", "Cuenta bancaria actualizada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar la cuenta bancaria.");
                }
                return redireccionar("/cuentasbancarias/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/cuentasbancarias/admin");
            }
        });

    }

}
