package org.banco.entidad;

import java.time.OffsetDateTime;

public class Tarjeta {

    private Integer idTarjeta;
    private String numeroTarjeta;
    private String tipoTarjeta;
    private OffsetDateTime fechaExpedicionTarjeta;
    private OffsetDateTime fechaVencimientoTarjeta;
    private String estadoTarjeta;
    private Cliente clienteTarjeta;
    private CuentaBancaria cuentaTarjeta;

    public Tarjeta() {
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public OffsetDateTime getFechaExpedicionTarjeta() {
        return fechaExpedicionTarjeta;
    }

    public void setFechaExpedicionTarjeta(OffsetDateTime fechaExpedicionTarjeta) {
        this.fechaExpedicionTarjeta = fechaExpedicionTarjeta;
    }

    public OffsetDateTime getFechaVencimientoTarjeta() {
        return fechaVencimientoTarjeta;
    }

    public void setFechaVencimientoTarjeta(OffsetDateTime fechaVencimientoTarjeta) {
        this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
    }

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public Cliente getClienteTarjeta() {
        return clienteTarjeta;
    }

    public void setClienteTarjeta(Cliente clienteTarjeta) {
        this.clienteTarjeta = clienteTarjeta;
    }

    public CuentaBancaria getCuentaTarjeta() {
        return cuentaTarjeta;
    }

    public void setCuentaTarjeta(CuentaBancaria cuentaTarjeta) {
        this.cuentaTarjeta = cuentaTarjeta;
    }
    
    
}
