package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.TransaccionCajero;
import org.banco.recurso.constante.Persistencia;

public class TransaccionCajeroRepositorioImpl extends
        PlainTextImpl<TransaccionCajero>
        implements TransaccionCajeroRepositorio {

    public TransaccionCajeroRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.TRANSACCION_CAJERO.toString(), TransaccionCajero.class, usarPK);
    }

}
