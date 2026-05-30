package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class MovimientoCuenta {

    private Integer idMovimientoCuenta;
    private CuentaBancaria cuentaMovimientoCuenta;
    private String tipoMovimientoCuenta;
    private BigDecimal valorMovimientoCuenta;
    private OffsetDateTime fechaMovimientoCuenta;
    private BigDecimal saldoPosteriorMovimientoCuenta;

    public MovimientoCuenta() {
    }

    public Integer getIdMovimientoCuenta() {
        return idMovimientoCuenta;
    }

    public void setIdMovimientoCuenta(Integer idMovimientoCuenta) {
        this.idMovimientoCuenta = idMovimientoCuenta;
    }

    public CuentaBancaria getCuentaMovimientoCuenta() {
        return cuentaMovimientoCuenta;
    }

    public void setCuentaMovimientoCuenta(CuentaBancaria cuentaMovimientoCuenta) {
        this.cuentaMovimientoCuenta = cuentaMovimientoCuenta;
    }

    public String getTipoMovimientoCuenta() {
        return tipoMovimientoCuenta;
    }

    public void setTipoMovimientoCuenta(String tipoMovimientoCuenta) {
        this.tipoMovimientoCuenta = tipoMovimientoCuenta;
    }

    public BigDecimal getValorMovimientoCuenta() {
        return valorMovimientoCuenta;
    }

    public void setValorMovimientoCuenta(BigDecimal valorMovimientoCuenta) {
        this.valorMovimientoCuenta = valorMovimientoCuenta;
    }

    public OffsetDateTime getFechaMovimientoCuenta() {
        return fechaMovimientoCuenta;
    }

    public void setFechaMovimientoCuenta(OffsetDateTime fechaMovimientoCuenta) {
        this.fechaMovimientoCuenta = fechaMovimientoCuenta;
    }

    public BigDecimal getSaldoPosteriorMovimientoCuenta() {
        return saldoPosteriorMovimientoCuenta;
    }

    public void setSaldoPosteriorMovimientoCuenta(BigDecimal saldoPosteriorMovimientoCuenta) {
        this.saldoPosteriorMovimientoCuenta = saldoPosteriorMovimientoCuenta;
    }
    
    
}
