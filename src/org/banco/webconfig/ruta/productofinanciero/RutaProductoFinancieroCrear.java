package org.banco.webconfig.ruta.productofinanciero;

import com.cleandev.webserver.ruteo.Router;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import org.banco.dto.productofinanciero.ProductoFinancieroCrearDto;
import org.banco.dto.productofinanciero.ProductoFinancieroDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaProductoFinancieroCrear extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/productos/crear", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Registrar Producto Financiero");
            cargarMensajes(req, modelo);
            return vista("productos/crear.html", modelo);
        });
        r.post("/productos/crear", req -> {
            try {
                Map<String, String> formulario = parsearFormulario(req);
                Optional<ProductoFinancieroDto> resultado = EnsambladorWeb.productoFinanciero().crear(
                        new ProductoFinancieroCrearDto(
                                formulario.get("nombreProductoFinanciero"),
                                formulario.get("tipoProductoFinanciero"),
                                new BigDecimal(formulario.get("tasaInteresProductoFinanciero")),
                                formulario.get("descripcionProductoFinanciero")
                        )
                );
                if (resultado.isPresent()) {
                    req.mensaje("exito", "Producto financiero registrado correctamente.");
                } else {
                    req.mensaje("error", "No se pudo registrar el producto financiero.");
                }
                return redireccionar("/productos/crear");
            } catch (Exception e) {
                req.mensaje("error", "Los datos ingresados tienen un formato inválido o están vacíos.");
                return redireccionar("/productos/crear");
            }
        });
    }
}
