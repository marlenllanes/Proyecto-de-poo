
package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.CuotaPrestamo;
import org.banco.recurso.constante.Persistencia;


public class CuotaPrestamoRepositorioImpl extends 
        PlainTextImpl<CuotaPrestamo> 
        implements CuotaPrestamoRepositorio {
    
    public CuotaPrestamoRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.CUOTA_PRESTAMO.toString(), CuotaPrestamo.class, usarPK);
    }


}

