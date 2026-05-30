package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransaccionCajero {

    private Integer idTransaccionCajero;
    private Cajero cajeroTransaccionCajero;
    private CuentaBancaria cuentaTransaccionCajero;
    private String tipoTransaccionCajero;
    private BigDecimal valorTransaccionCajero;
    private OffsetDateTime fechaTransaccionCajero;

    public TransaccionCajero() {
    }

    public Integer getIdTransaccionCajero() {
        return idTransaccionCajero;
    }

    public void setIdTransaccionCajero(Integer idTransaccionCajero) {
        this.idTransaccionCajero = idTransaccionCajero;
    }

    public Cajero getCajeroTransaccionCajero() {
        return cajeroTransaccionCajero;
    }

    public void setCajeroTransaccionCajero(Cajero cajeroTransaccionCajero) {
        this.cajeroTransaccionCajero = cajeroTransaccionCajero;
    }

    public CuentaBancaria getCuentaTransaccionCajero() {
        return cuentaTransaccionCajero;
    }

    public void setCuentaTransaccionCajero(CuentaBancaria cuentaTransaccionCajero) {
        this.cuentaTransaccionCajero = cuentaTransaccionCajero;
    }

    public String getTipoTransaccionCajero() {
        return tipoTransaccionCajero;
    }

    public void setTipoTransaccionCajero(String tipoTransaccionCajero) {
        this.tipoTransaccionCajero = tipoTransaccionCajero;
    }

    public BigDecimal getValorTransaccionCajero() {
        return valorTransaccionCajero;
    }

    public void setValorTransaccionCajero(BigDecimal valorTransaccionCajero) {
        this.valorTransaccionCajero = valorTransaccionCajero;
    }

    public OffsetDateTime getFechaTransaccionCajero() {
        return fechaTransaccionCajero;
    }

    public void setFechaTransaccionCajero(OffsetDateTime fechaTransaccionCajero) {
        this.fechaTransaccionCajero = fechaTransaccionCajero;
    }

}
