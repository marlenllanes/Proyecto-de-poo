package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.ClienteProductoSucursal;
import org.banco.recurso.constante.Persistencia;

public class ClienteProductoSucursalRepositorioImpl extends
        PlainTextImpl<ClienteProductoSucursal>
        implements ClienteProductoSucursalRepositorio {

    public ClienteProductoSucursalRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.CLIENTE_PRODUCTO_SUCURSAL.toString(), ClienteProductoSucursal.class, usarPK);
    }

}
