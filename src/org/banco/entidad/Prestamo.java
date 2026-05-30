package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Prestamo {

    private Integer idPrestamo;
    private Cliente clientePrestamo;
    private BigDecimal montoPrestamo;
    private BigDecimal tasaInteresPrestamo;
    private OffsetDateTime fechaDesembolsoPrestamo;
    private String estadoPrestamo;

    public Prestamo() {
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Cliente getClientePrestamo() {
        return clientePrestamo;
    }

    public void setClientePrestamo(Cliente clientePrestamo) {
        this.clientePrestamo = clientePrestamo;
    }

    public BigDecimal getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(BigDecimal montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public BigDecimal getTasaInteresPrestamo() {
        return tasaInteresPrestamo;
    }

    public void setTasaInteresPrestamo(BigDecimal tasaInteresPrestamo) {
        this.tasaInteresPrestamo = tasaInteresPrestamo;
    }

    public OffsetDateTime getFechaDesembolsoPrestamo() {
        return fechaDesembolsoPrestamo;
    }

    public void setFechaDesembolsoPrestamo(OffsetDateTime fechaDesembolsoPrestamo) {
        this.fechaDesembolsoPrestamo = fechaDesembolsoPrestamo;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
    
    
}
