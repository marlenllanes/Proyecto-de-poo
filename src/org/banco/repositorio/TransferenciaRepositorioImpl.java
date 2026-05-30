package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.Transferencia;
import org.banco.recurso.constante.Persistencia;

public class TransferenciaRepositorioImpl extends 
        PlainTextImpl<Transferencia> 
        implements TransferenciaRepositorio {
    
    public TransferenciaRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.TRANSFERENCIA.toString(), Transferencia.class, usarPK);
    }


}
