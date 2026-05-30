package org.banco.webconfig.ruta.productofinanciero;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.productofinanciero.ProductoFinancieroActualizarDto;
import org.banco.dto.productofinanciero.ProductoFinancieroDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaProductoFinancieroEditar extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/productos/editar/:id", req -> {
            try {
                int codigo = Integer.parseInt(req.param("id"));
                Optional<ProductoFinancieroDto> prod = EnsambladorWeb.productoFinanciero().obtenerUno(codigo);
                if (prod.isEmpty()) {
                    req.mensaje("error", "El producto financiero no existe.");
                    return redireccionar("/productos/admin");
                }
                ProductoFinancieroDto dto = prod.get();
                Map<String, Object> modelo = modeloBase();
                modelo.put("titulo", "Editar Producto Financiero");
                modelo.put("idProductoFinanciero", dto.idProductoFinanciero());
                modelo.put("nombreProductoFinanciero", dto.nombreProductoFinanciero());
                modelo.put("tipoProductoFinanciero", dto.tipoProductoFinanciero());
                modelo.put("tasaInteresProductoFinanciero", dto.tasaInteresProductoFinanciero());
                modelo.put("descripcionProductoFinanciero", dto.descripcionProductoFinanciero());

                cargarMensajes(req, modelo);
                return vista("productos/editar.html", modelo);
            } catch (Exception e) {
                req.mensaje("error", "ID inválido.");
                return redireccionar("/productos/admin");
            }
        });
        r.post("/productos/editar/guardar", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                int id = Integer.parseInt(formulario.get("idProductoFinanciero"));
                ProductoFinancieroActualizarDto dto = new ProductoFinancieroActualizarDto(
                        id,
                        formulario.get("nombreProductoFinanciero"),
                        formulario.get("tipoProductoFinanciero"),
                        new BigDecimal(formulario.get("tasaInteresProductoFinanciero")),
                        formulario.get("descripcionProductoFinanciero")
                );
                Optional<ProductoFinancieroDto> resultado = EnsambladorWeb.productoFinanciero().actualizar(id, dto);
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Producto financiero actualizado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo actualizar el producto financiero.");
                }
                return redireccionar("/productos/editar/" + id);
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/productos/admin");
            }
        });
    }
}
