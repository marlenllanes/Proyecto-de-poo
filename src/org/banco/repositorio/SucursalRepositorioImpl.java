package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.Sucursal;
import org.banco.recurso.constante.Persistencia;

public class SucursalRepositorioImpl extends 
        PlainTextImpl<Sucursal> 
        implements SucursalRepositorio {
    
    public SucursalRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.SUCURSAL.toString(), Sucursal.class, usarPK);
    }


}

