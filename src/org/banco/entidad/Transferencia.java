package org.banco.entidad;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.banco.entidad.pk.TransferenciaPk;

public class Transferencia {

    private Integer idTransferencia;
    private final TransferenciaPk idTransferenciaPk;
    private OffsetDateTime fechaTransferencia;
    private BigDecimal valorTransferencia;
    private String descripcionTransferencia;

    public Transferencia(Integer idCuentaOrigen, Integer idCuentaDestino) {
        this.idTransferenciaPk = TransferenciaPk.conIds(idCuentaOrigen, idCuentaDestino);
    }

    public Transferencia(Integer idCuentaOrigen, Integer idCuentaDestino, BigDecimal valorTransferencia) {
        this.idTransferenciaPk = TransferenciaPk.conIds(idCuentaOrigen, idCuentaDestino);
        this.valorTransferencia = valorTransferencia;
    }

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public TransferenciaPk getIdTransferenciaPk() {
        return idTransferenciaPk;
    }

    public OffsetDateTime getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(OffsetDateTime fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public BigDecimal getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(BigDecimal valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public String getDescripcionTransferencia() {
        return descripcionTransferencia;
    }

    public void setDescripcionTransferencia(String descripcionTransferencia) {
        this.descripcionTransferencia = descripcionTransferencia;
    }
}
