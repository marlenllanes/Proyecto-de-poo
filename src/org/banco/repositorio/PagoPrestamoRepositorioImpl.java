package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.PagoPrestamo;
import org.banco.recurso.constante.Persistencia;

public class PagoPrestamoRepositorioImpl extends 
        PlainTextImpl<PagoPrestamo> 
        implements PagoPrestamoRepositorio {
    
    public PagoPrestamoRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.PAGO_PRESTAMO.toString(), PagoPrestamo.class, usarPK);
    }

}

