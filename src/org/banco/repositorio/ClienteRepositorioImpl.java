package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.Cliente;
import org.banco.recurso.constante.Persistencia;

public class ClienteRepositorioImpl extends 
        PlainTextImpl<Cliente> 
        implements ClienteRepositorio {
    
    public ClienteRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.CLIENTE.toString(), Cliente.class, usarPK);
    }


}
