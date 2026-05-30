package org.banco.webconfig.ruta.cliente;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cliente.ClienteActualizarDto;
import org.banco.dto.cliente.ClienteDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cliente/editar/:id", req -> {
            int codigo = Integer.parseInt(req.param("id"));
            Optional<ClienteDto> clien = EnsambladorWeb.cliente().obtenerUno(codigo);
            if (clien.isEmpty()) {
                req.mensaje("error", "no me engañes");
                return redireccionar("/clientes/admin");
            }
            ClienteDto dto = clien.get();
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Soy el titulo de editar");
            modelo.put("idCliente", dto.idCliente());
            modelo.put("nombreCliente", dto.nombreCliente());
            modelo.put("documentoCliente", dto.documentoCliente());
            modelo.put("correoCliente", dto.correoCliente());
            modelo.put("celularCliente", dto.celularCliente());
            modelo.put("fechaNacimientoCliente", dto.fechaNacimientoCliente());

            cargarMensajes(req, modelo);
            return vista("/clientes/editar.html", modelo);

        });
        r.post("/elCliente/edit", req -> {//ruta
            Map<String, String> formulario = parsearFormulario(req);
            int codigo = Integer.parseInt(formulario.get("idCliente"));
            ClienteActualizarDto dto = new ClienteActualizarDto(
                    codigo,
                    formulario.get("nombreCliente"),
                    formulario.get("documentoCliente"),
                    formulario.get("correoCliente"),
                    formulario.get("celularCliente"),
                    java.time.OffsetDateTime.parse(formulario.get("fechaNacimientoCliente")),
                    formulario.get("direccionCliente")
            );

            Optional<ClienteDto> resultado = EnsambladorWeb.cliente().actualizar(codigo, dto);

            if (resultado.isPresent()) {
                req.mensaje("exito", " si funcionaa");
            } else {
                req.mensaje("error", " ta malito");
            }

            return redireccionar("/cliente/editar/" + codigo);
        });
    }

}
