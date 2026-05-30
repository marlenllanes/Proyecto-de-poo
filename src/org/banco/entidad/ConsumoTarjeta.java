package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ConsumoTarjeta {

    private Integer idConsumoTarjeta;
    private Tarjeta tarjetaConsumoTarjeta;
    private OffsetDateTime fechaConsumoTarjeta;
    private String establecimientoConsumoTarjeta;
    private BigDecimal valorConsumoTarjeta;
    private Integer cuotaConsumoTarjeta;

    public ConsumoTarjeta() {
    }

    public Integer getIdConsumoTarjeta() {
        return idConsumoTarjeta;
    }

    public void setIdConsumoTarjeta(Integer idConsumoTarjeta) {
        this.idConsumoTarjeta = idConsumoTarjeta;
    }

    public Tarjeta getTarjetaConsumoTarjeta() {
        return tarjetaConsumoTarjeta;
    }

    public void setTarjetaConsumoTarjeta(Tarjeta tarjetaConsumoTarjeta) {
        this.tarjetaConsumoTarjeta = tarjetaConsumoTarjeta;
    }

    public OffsetDateTime getFechaConsumoTarjeta() {
        return fechaConsumoTarjeta;
    }

    public void setFechaConsumoTarjeta(OffsetDateTime fechaConsumoTarjeta) {
        this.fechaConsumoTarjeta = fechaConsumoTarjeta;
    }

    public String getEstablecimientoConsumoTarjeta() {
        return establecimientoConsumoTarjeta;
    }

    public void setEstablecimientoConsumoTarjeta(String establecimientoConsumoTarjeta) {
        this.establecimientoConsumoTarjeta = establecimientoConsumoTarjeta;
    }

    public BigDecimal getValorConsumoTarjeta() {
        return valorConsumoTarjeta;
    }

    public void setValorConsumoTarjeta(BigDecimal valorConsumoTarjeta) {
        this.valorConsumoTarjeta = valorConsumoTarjeta;
    }

    public Integer getCuotaConsumoTarjeta() {
        return cuotaConsumoTarjeta;
    }

    public void setCuotaConsumoTarjeta(Integer cuotaConsumoTarjeta) {
        this.cuotaConsumoTarjeta = cuotaConsumoTarjeta;
    }
    
    
}
