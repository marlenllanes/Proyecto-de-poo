package org.banco.webconfig.ruta.cliente;

import com.cleandev.webserver.ruteo.Router;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.cliente.ClienteCrearDto;
import org.banco.dto.cliente.ClienteDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteCrear extends ControladorBancolombia {
    
    @Override
    public void registrar(Router r) {
        r.get("/clientes/crear", req -> {//ruta
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Crear los clientes");
            cargarMensajes(req, modelo);
            return vista("clientes/crear.html", modelo);//pagina
        });
        r.post("/losClien/crear", req -> {//ruta
            Map<String, String> formulario = parsearFormulario(req);
            String fechaInput = formulario.get("fechaNacimientoCliente");
            if (fechaInput != null && fechaInput.length() > 10) {
                fechaInput = fechaInput.substring(0, 10); // Corta y deja solo YYYY-MM-DD
            }
            LocalDate fecha = LocalDate.parse(fechaInput);
            OffsetDateTime fechaOffset = fecha.atTime(10, 0).atOffset(ZoneOffset.ofHours(1));
            Optional<ClienteDto> resultado = EnsambladorWeb.cliente().crear(
                    new ClienteCrearDto(
                            formulario.get("nombreCliente"),
                            formulario.get("documentoCliente"),
                            formulario.get("correoCliente"),
                            formulario.get("celularCliente"),
                            fechaOffset,
                            formulario.get("direccionCliente")
                    ));            
            if (resultado.isPresent()) {
                req.mensaje("exito", "todo bien");
            } else {
                req.mensaje("error", "todo mal");
            }
            
            return redireccionar("/clientes/crear");
        });
    }
    
}
