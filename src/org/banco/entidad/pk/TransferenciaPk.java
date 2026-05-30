package org.banco.entidad.pk;

import org.banco.entidad.CuentaBancaria;

public class TransferenciaPk {

    private final CuentaBancaria cuentaOrigen;
    private final CuentaBancaria cuentaDestino;

    public TransferenciaPk(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public static TransferenciaPk conIds(Integer idCuentaOrigen, Integer idCuentaDestino) {
        CuentaBancaria origen = new CuentaBancaria();
        origen.setIdCuentaBancaria(idCuentaOrigen);

        CuentaBancaria destino = new CuentaBancaria();
        destino.setIdCuentaBancaria(idCuentaDestino);

        return new TransferenciaPk(origen, destino);
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }
}
