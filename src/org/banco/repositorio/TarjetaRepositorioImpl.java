package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.Tarjeta;
import org.banco.recurso.constante.Persistencia;

public class TarjetaRepositorioImpl extends
        PlainTextImpl<Tarjeta>
        implements TarjetaRepositorio {

    public TarjetaRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.TARJETA.toString(), Tarjeta.class, usarPK);
    }

}
