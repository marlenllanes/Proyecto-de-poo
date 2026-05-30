package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.ConsumoTarjeta;
import org.banco.recurso.constante.Persistencia;

public class ConsumoTarjetaRepositorioImpl extends 
        PlainTextImpl<ConsumoTarjeta> 
        implements ConsumoTarjetaRepositorio {
    
    public ConsumoTarjetaRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.CONSUMO_TARJETA.toString(), ConsumoTarjeta.class, usarPK);
    }


}
