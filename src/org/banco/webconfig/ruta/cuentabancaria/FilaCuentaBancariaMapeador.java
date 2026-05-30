package org.banco.webconfig.ruta.cuentabancaria;
import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;

public class FilaCuentaBancariaMapeador {
    public List<FilaCuentaBancariaDto> listarCuenta(List<CuentaBancariaDto> misCuentas) {
        List<FilaCuentaBancariaDto> fila = new ArrayList<>();
        
        if (misCuentas == null) {
            return fila;
        }
        
        for (CuentaBancariaDto cuen : misCuentas) {
            String nombreCli = "Sin Cliente";
            Integer idCli = null;
            
            if (cuen.cliente() != null) {
                nombreCli = cuen.cliente().nombreCliente();
                idCli = cuen.cliente().idCliente();
            }
            
            FilaCuentaBancariaDto mifila = new FilaCuentaBancariaDto(
                    cuen.idCuentaBancaria(),
                    cuen.numeroCuentaBancaria(),
                    cuen.tipoCuentaBancaria(),
                    cuen.saldoCuentaBancaria(),
                    cuen.fechaAperturaCuentaBancaria(),
                    cuen.estadoCuentaBancaria(),
                    idCli,
                    nombreCli
            );
            fila.add(mifila);
        }
        return fila;
    }
}