package org.banco.repositorio;

import com.cleandev.tpa.PlainTextJoinImpl;
import java.io.IOException;
import org.banco.entidad.Transferencia;
import org.banco.recurso.constante.Persistencia;

public class TransferenciaRepositorioImpl
        extends PlainTextJoinImpl<Transferencia>
        implements TransferenciaRepositorio {

    public TransferenciaRepositorioImpl() throws IOException {
        super(Persistencia.TRANSFERENCIA.toString(), Transferencia.class);
    }
}
