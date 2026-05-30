package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class CuentaBancaria {

    private Integer idCuentaBancaria;
    private String numeroCuentaBancaria;
    private String tipoCuentaBancaria;
    private BigDecimal saldoCuentaBancaria;
    private OffsetDateTime fechaAperturaCuentaBancaria;
    private String estadoCuentaBancaria;
    private Cliente clienteCuentaBancaria;

    public CuentaBancaria() {
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public String getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public BigDecimal getSaldoCuentaBancaria() {
        return saldoCuentaBancaria;
    }

    public void setSaldoCuentaBancaria(BigDecimal saldoCuentaBancaria) {
        this.saldoCuentaBancaria = saldoCuentaBancaria;
    }

    public OffsetDateTime getFechaAperturaCuentaBancaria() {
        return fechaAperturaCuentaBancaria;
    }

    public void setFechaAperturaCuentaBancaria(OffsetDateTime fechaAperturaCuentaBancaria) {
        this.fechaAperturaCuentaBancaria = fechaAperturaCuentaBancaria;
    }

    public String getEstadoCuentaBancaria() {
        return estadoCuentaBancaria;
    }

    public void setEstadoCuentaBancaria(String estadoCuentaBancaria) {
        this.estadoCuentaBancaria = estadoCuentaBancaria;
    }

    public Cliente getClienteCuentaBancaria() {
        return clienteCuentaBancaria;
    }

    public void setClienteCuentaBancaria(Cliente clienteCuentaBancaria) {
        this.clienteCuentaBancaria = clienteCuentaBancaria;
    }
    
    
}
