package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PagoPrestamo {

    private Integer idPagoPrestamo;
    private Prestamo prestamoPagoPrestamo;
    private OffsetDateTime fechaPagoPrestamo;
    private BigDecimal valorPagoPrestamo;
    private String metodoPagoPrestamo;

    public PagoPrestamo() {
    }

    public Integer getIdPagoPrestamo() {
        return idPagoPrestamo;
    }

    public void setIdPagoPrestamo(Integer idPagoPrestamo) {
        this.idPagoPrestamo = idPagoPrestamo;
    }

    public Prestamo getPrestamoPagoPrestamo() {
        return prestamoPagoPrestamo;
    }

    public void setPrestamoPagoPrestamo(Prestamo prestamoPagoPrestamo) {
        this.prestamoPagoPrestamo = prestamoPagoPrestamo;
    }

    public OffsetDateTime getFechaPagoPrestamo() {
        return fechaPagoPrestamo;
    }

    public void setFechaPagoPrestamo(OffsetDateTime fechaPagoPrestamo) {
        this.fechaPagoPrestamo = fechaPagoPrestamo;
    }

    public BigDecimal getValorPagoPrestamo() {
        return valorPagoPrestamo;
    }

    public void setValorPagoPrestamo(BigDecimal valorPagoPrestamo) {
        this.valorPagoPrestamo = valorPagoPrestamo;
    }

    public String getMetodoPagoPrestamo() {
        return metodoPagoPrestamo;
    }

    public void setMetodoPagoPrestamo(String metodoPagoPrestamo) {
        this.metodoPagoPrestamo = metodoPagoPrestamo;
    }
    
    
}
