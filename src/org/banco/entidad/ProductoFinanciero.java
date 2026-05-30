package org.banco.entidad;

import java.math.BigDecimal;

public class ProductoFinanciero {

    private Integer idProductoFinanciero;
    private String nombreProductoFinanciero;
    private String tipoProductoFinanciero;
    private BigDecimal tasaInteresProductoFinanciero;
    private String descripcionProductoFinanciero;

    public ProductoFinanciero() {
    }

    public Integer getIdProductoFinanciero() {
        return idProductoFinanciero;
    }

    public void setIdProductoFinanciero(Integer idProductoFinanciero) {
        this.idProductoFinanciero = idProductoFinanciero;
    }

    public String getNombreProductoFinanciero() {
        return nombreProductoFinanciero;
    }

    public void setNombreProductoFinanciero(String nombreProductoFinanciero) {
        this.nombreProductoFinanciero = nombreProductoFinanciero;
    }

    public String getTipoProductoFinanciero() {
        return tipoProductoFinanciero;
    }

    public void setTipoProductoFinanciero(String tipoProductoFinanciero) {
        this.tipoProductoFinanciero = tipoProductoFinanciero;
    }

    public BigDecimal getTasaInteresProductoFinanciero() {
        return tasaInteresProductoFinanciero;
    }

    public void setTasaInteresProductoFinanciero(BigDecimal tasaInteresProductoFinanciero) {
        this.tasaInteresProductoFinanciero = tasaInteresProductoFinanciero;
    }

    public String getDescripcionProductoFinanciero() {
        return descripcionProductoFinanciero;
    }

    public void setDescripcionProductoFinanciero(String descripcionProductoFinanciero) {
        this.descripcionProductoFinanciero = descripcionProductoFinanciero;
    }
    
    
}
