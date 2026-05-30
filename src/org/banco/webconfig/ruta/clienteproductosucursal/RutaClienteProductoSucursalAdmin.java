package org.banco.webconfig.ruta.clienteproductosucursal;

import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.dto.clienteproductosucursal.ClienteProductoSucursalIdentificadorDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaClienteProductoSucursalAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/clienteproductosucursal/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Adquisiciones");
            modelo.put("adquisiciones", EnsambladorWeb.clienteProductoSucursal().obtenerTodos());
            cargarMensajes(req, modelo);
            return vista("clienteproductosucursal/admin.html", modelo);
        });
        r.get("/clienteproductosucursal/eliminar/:idCliente/:idProducto/:idSucursal", req -> {
            int idCli = Integer.parseInt(req.param("idCliente"));
            int idProd = Integer.parseInt(req.param("idProducto"));
            int idSuc = Integer.parseInt(req.param("idSucursal"));
            ClienteProductoSucursalIdentificadorDto identificador = new ClienteProductoSucursalIdentificadorDto(idCli, idProd, idSuc); // 
            boolean eliminado = EnsambladorWeb.clienteProductoSucursal().eliminar(identificador);
            if (eliminado) {
                req.mensaje("exito", "Adquisición eliminada correctamente.");
            } else {
                req.mensaje("error", "No se pudo eliminar la adquisición.");
            }
            return redireccionar("/clienteproductosucursal/admin");
        });
    }
}
