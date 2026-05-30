package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.Prestamo;
import org.banco.recurso.constante.Persistencia;

public class PrestamoRepositorioImpl extends
        PlainTextImpl<Prestamo>
        implements PrestamoRepositorio {

    public PrestamoRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.PRESTAMO.toString(), Prestamo.class, usarPK);
    }

}
