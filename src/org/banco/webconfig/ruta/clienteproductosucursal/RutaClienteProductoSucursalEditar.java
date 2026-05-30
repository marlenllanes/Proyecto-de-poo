package org.banco.webconfig.ruta.clienteproductosucursal;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalIdentificadorDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalActualizarDto;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteProductoSucursalEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/clienteproductosucursal/editar/:idCliente/:idProducto/:idSucursal", req -> {
            try {
                int idCli = Integer.parseInt(req.param("idCliente"));
                int idProd = Integer.parseInt(req.param("idProducto"));
                int idSuc = Integer.parseInt(req.param("idSucursal"));
                ClienteProductoSucursalIdentificadorDto identificador = new ClienteProductoSucursalIdentificadorDto(idCli, idProd, idSuc);
                Optional<ClienteProductoSucursalDto> adq = EnsambladorWeb.clienteProductoSucursal().obtenerUno(identificador);
                if (adq.isEmpty()) {
                    req.mensaje("error", "La adquisición no existe.");
                    return redireccionar("/clienteproductosucursal/admin");
                }
                ClienteProductoSucursalDto dto = adq.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Adquisición");
                modelo.put("fechaAdquisicion", dto.fechaAdquisicion());
                modelo.put("valorInicial", dto.valorInicial());
                modelo.put("estado", dto.estado());
                modelo.put("idCliente", dto.cliente().idCliente());
                modelo.put("idProductoFinanciero", dto.productoFinanciero().idProductoFinanciero());
                modelo.put("idSucursal", dto.sucursal().idSucursal());
                modelo.put("clientes", EnsambladorWeb.cliente().obtenerTodos());
                modelo.put("productos", EnsambladorWeb.productoFinanciero().obtenerTodos());
                modelo.put("sucursales", EnsambladorWeb.sucursal().obtenerTodos());
                cargarMensajes(req, modelo);
                return vista("clienteproductosucursal/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "IDs inválidos.");
                return redireccionar("/clienteproductosucursal/admin");
            }
        });
        r.post("/clienteproductosucursal/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int idCli = Integer.parseInt(formulario.get("idCliente"));
                int idProd = Integer.parseInt(formulario.get("idProductoFinanciero"));
                int idSuc = Integer.parseInt(formulario.get("idSucursal"));
                ClienteProductoSucursalIdentificadorDto identificador = new ClienteProductoSucursalIdentificadorDto(idCli, idProd, idSuc);
                ClienteProductoSucursalActualizarDto dto = new ClienteProductoSucursalActualizarDto(
                        idCli,
                        idProd,
                        idSuc,
                        OffsetDateTime.parse(formulario.get("fechaAdquisicion")),
                        new BigDecimal(formulario.get("valorInicial")),
                        formulario.get("estado")
                );
                Optional<ClienteProductoSucursalActualizarDto> resultado = EnsambladorWeb.clienteProductoSucursal().actualizar(identificador, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Adquisición actualizada correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar la adquisición.");
                }
                return redireccionar("/clienteproductosucursal/editar/" + idCli + "/" + idProd + "/" + idSuc);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/clienteproductosucursal/admin");
            }
        });
    }
}

