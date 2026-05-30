
package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.ProductoFinanciero;
import org.banco.recurso.constante.Persistencia;

public class ProductoFinacieroRepositorioImpl extends 
        PlainTextImpl<ProductoFinanciero> 
        implements ProductoFinacieroRepositorio {
    
    public ProductoFinacieroRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.PRODUCTO_FINANCIERO.toString(), ProductoFinanciero.class, usarPK);
    }


}

