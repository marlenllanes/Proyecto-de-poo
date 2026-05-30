package org.banco.webconfig;

import com.cleandev.webserver.ruteo.RegistroRutas;
import com.cleandev.webserver.ruteo.Router;
import java.util.Map;
import org.banco.webconfig.ruta.cliente.RutaClienteAdmin;
import org.banco.webconfig.ruta.cliente.RutaClienteCrear;
import org.banco.webconfig.ruta.cliente.RutaClienteEditar;
import org.banco.webconfig.ruta.cliente.RutaClienteListar;


public class Ruteo extends ControladorBancolombia
        implements RegistroRutas {

    private void registrarPaginasPrincipales(Router r) {
        r.get("/", req -> vista("index.html", modeloBase()));
        r.get("/acercade", req -> vista("acerca_de.html", modeloBase()));
    }

    private void registrarArchivosEstaticos(Router r) {
        r.get("/favicon.ico", req -> archivoEstatico("/assets/icon/favicon.ico"));
        r.get("/assets/*", req -> archivoEstatico(req.rutaSolicitada()));
        r.get("/public", req -> archivoEstatico(req.rutaSolicitada()));
    }

    @Override
    public void registrar(Router r) {
        registrarPaginasPrincipales(r);
        registrarArchivosEstaticos(r);

       // new RutaCategoriaAdmin().registrar(r);
        //new RutaCategoriaCrear().registrar(r);
        //new RutaCategoriaEditar().registrar(r);
        //new RutaCategoriaListar().registrar(r);
        new RutaClienteAdmin().registrar(r);
        new RutaClienteCrear().registrar(r);
        new RutaClienteEditar().registrar(r);
        new RutaClienteListar().registrar(r);


        setManejador404(req -> {
            Map<String, Object> modelo = modeloBase();
            modelo.put("titulo", "Lo siento no se pudo encontrar");
            modelo.put("rutaSolicitada", req.rutaSolicitada());
            return vista("no_encontrada.html", modelo);
        });

    }

}
