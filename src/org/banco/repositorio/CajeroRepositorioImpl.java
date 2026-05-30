package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.Cajero;
import org.banco.recurso.constante.Persistencia;

public class CajeroRepositorioImpl extends 
        PlainTextImpl<Cajero> 
        implements CajeroRepositorio {
    
    public CajeroRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.CAJERO.toString(), Cajero.class, usarPK);
    }


}
