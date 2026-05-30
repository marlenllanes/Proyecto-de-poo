package org.banco.webconfig.ruta.clienteproductosucursal;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalCrearDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteProductoSucursalCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/clienteproductosucursal/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Adquisición");
            modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos());
            modelo.put("productos", EnsambladorWeb.productoFinanciero().obtenerTodos());
            modelo.put("sucursales", EnsambladorWeb.sucursal().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("clienteproductosucursal/crear.html", modelo);
        });
        r.post("/adquisiciones/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<ClienteProductoSucursalCrearDto> resultado = EnsambladorWeb.clienteProductoSucursal().crear(
                        new ClienteProductoSucursalCrearDto(
                                Integer.parseInt(formulario.get("idCliente")),
                                Integer.parseInt(formulario.get("idProductoFinanciero")),
                                Integer.parseInt(formulario.get("idSucursal")),
                                OffsetDateTime.parse(formulario.get("fechaAdquisicion")),
                                new BigDecimal(formulario.get("valorInicial")),
                                formulario.get("estado")
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Adquisición registrada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar. Verifique que el cliente no tenga ya este producto en esta sucursal.");
                }
                return redireccionar("/clienteproductosucursal/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/clienteproductosucursal/crear");
            }
        });
    }
}
