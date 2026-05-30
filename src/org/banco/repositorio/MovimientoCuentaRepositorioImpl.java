package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.MovimientoCuenta;
import org.banco.recurso.constante.Persistencia;

public class MovimientoCuentaRepositorioImpl extends
        PlainTextImpl<MovimientoCuenta>
        implements MovimientoCuentaRepositorio {

    public MovimientoCuentaRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.MOVIMIENTO_CUENTA.toString(), MovimientoCuenta.class, usarPK);
    }

}
