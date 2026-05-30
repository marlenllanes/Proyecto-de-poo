package org.banco.repositorio;

import com.cleandev.tpa.PlainTextJoinImpl;
import java.io.IOException;
import org.banco.entidad.ClienteProductoSucursal;
import org.banco.recurso.constante.Persistencia;

public class ClienteProductoSucursalRepositorioImpl
        extends PlainTextJoinImpl<ClienteProductoSucursal>
        implements ClienteProductoSucursalRepositorio {

    public ClienteProductoSucursalRepositorioImpl() throws IOException {
        super(Persistencia.CLIENTE_PRODUCTO_SUCURSAL.toString(), ClienteProductoSucursal.class);
    }
}
