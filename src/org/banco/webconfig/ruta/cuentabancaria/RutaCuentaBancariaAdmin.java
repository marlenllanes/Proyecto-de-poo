package org.banco.webconfig.ruta.cuentabancaria;
import com.cleandev.webserver.ruteo.Router;
import java.util.List;
import java.util.Map;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.webconfig.ControladorBancolombia;
import org.banco.webconfig.EnsambladorWeb;

public class RutaCuentaBancariaAdmin extends ControladorBancolombia {

    @Override
    public void registrar(Router r) {
        r.get("/cuentas/admin", req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Gestión de Cuentas Bancarias");
            // 1. Obtenemos la lista original del servicio
            List<CuentaBancariaDto> originales = EnsambladorWeb.cuentaBancaria().obtenerTodos();
            // 2. Usamos el mapeador para convertir a filas planas
            FilaCuentaBancariaMapeador maper = new FilaCuentaBancariaMapeador();
            List<FilaCuentaBancariaDto> filas = maper.listarCuenta(originales);
            // 3. Pasamos la lista al modelo
            modelo.put("cuentas", filas);
            cargarMensajes(req, modelo);
            return vista("cuentaBancaria/admin.html", modelo);
        });

        r.get("/cuentas/eliminar/:id", req -> {
            try {
                int id = Integer.parseInt(req.param("id"));
                boolean eliminado = EnsambladorWeb.cuentaBancaria().eliminar(id);
                if (eliminado) {
                    req.mensaje("exito", "Cuenta bancaria eliminada correctamente.");
                } else {
                    req.mensaje("error", "No se puede eliminar la cuenta porque tiene movimientos o transferencias asociadas.");
                }
            } catch (Exception e) {
                req.mensaje("error", "Ocurrió un error al intentar eliminar la cuenta.");
            }
            return redireccionar("/cuentas/admin");
        });
    }
}


