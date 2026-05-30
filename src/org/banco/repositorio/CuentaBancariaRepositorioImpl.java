package org.banco.repositorio;

import com.cleandev.tpa.PlainTextImpl;
import java.io.IOException;
import org.banco.entidad.CuentaBancaria;
import org.banco.recurso.constante.Persistencia;

public class CuentaBancariaRepositorioImpl extends
        PlainTextImpl<CuentaBancaria>
        implements CuentaBancariaRepositorio {

    public CuentaBancariaRepositorioImpl(boolean usarPK) throws IOException {
        super(Persistencia.CUENTA_BANCARIA.toString(), CuentaBancaria.class, usarPK);
    }

}
