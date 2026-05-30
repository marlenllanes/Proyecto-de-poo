package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class CuotaPrestamo {

    private Integer idCuotaPrestamo;
    private Prestamo prestamoCuotaPrestamo;
    private Integer numeroCuotaPrestamo;
    private OffsetDateTime fechaVencimientoCuotaPrestamo;
    private BigDecimal valorCuotaPrestamo;
    private String estadoCuotaPrestamo;

    public CuotaPrestamo() {
    }

    public Integer getIdCuotaPrestamo() {
        return idCuotaPrestamo;
    }

    public void setIdCuotaPrestamo(Integer idCuotaPrestamo) {
        this.idCuotaPrestamo = idCuotaPrestamo;
    }

    public Prestamo getPrestamoCuotaPrestamo() {
        return prestamoCuotaPrestamo;
    }

    public void setPrestamoCuotaPrestamo(Prestamo prestamoCuotaPrestamo) {
        this.prestamoCuotaPrestamo = prestamoCuotaPrestamo;
    }

    public Integer getNumeroCuotaPrestamo() {
        return numeroCuotaPrestamo;
    }

    public void setNumeroCuotaPrestamo(Integer numeroCuotaPrestamo) {
        this.numeroCuotaPrestamo = numeroCuotaPrestamo;
    }

    public OffsetDateTime getFechaVencimientoCuotaPrestamo() {
        return fechaVencimientoCuotaPrestamo;
    }

    public void setFechaVencimientoCuotaPrestamo(OffsetDateTime fechaVencimientoCuotaPrestamo) {
        this.fechaVencimientoCuotaPrestamo = fechaVencimientoCuotaPrestamo;
    }

    public BigDecimal getValorCuotaPrestamo() {
        return valorCuotaPrestamo;
    }

    public void setValorCuotaPrestamo(BigDecimal valorCuotaPrestamo) {
        this.valorCuotaPrestamo = valorCuotaPrestamo;
    }

    public String getEstadoCuotaPrestamo() {
        return estadoCuotaPrestamo;
    }

    public void setEstadoCuotaPrestamo(String estadoCuotaPrestamo) {
        this.estadoCuotaPrestamo = estadoCuotaPrestamo;
    }
    
    
}
