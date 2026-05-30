package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.entidad.pk.ClienteProductoSucursalPk;

public class ClienteProductoSucursal {

    private final ClienteProductoSucursalPk idClienteProductoSucursalPk;
    private OffsetDateTime fechaAdquisicion;
    private BigDecimal valorInicial;
    private String estado;

    public ClienteProductoSucursal(Integer idCliente, Integer idProductoFinanciero, Integer idSucursal) {
        this.idClienteProductoSucursalPk = ClienteProductoSucursalPk.conIds(idCliente, idProductoFinanciero, idSucursal);
    }

    public ClienteProductoSucursal(Integer idCliente, Integer idProductoFinanciero, Integer idSucursal, BigDecimal valorInicial) {
        this.idClienteProductoSucursalPk = ClienteProductoSucursalPk.conIds(idCliente, idProductoFinanciero, idSucursal);
        this.valorInicial = valorInicial;
    }

    public ClienteProductoSucursalPk getIdClienteProductoSucursalPk() {
        return idClienteProductoSucursalPk;
    }

    public OffsetDateTime getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(OffsetDateTime fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
