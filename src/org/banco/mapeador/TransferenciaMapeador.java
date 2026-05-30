package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.transferencia.TransferenciaActualizarDto;
import org.banco.dto.transferencia.TransferenciaCrearDto;
import org.banco.dto.transferencia.TransferenciaDto;
import org.banco.entidad.Transferencia;
import org.banco.mapeador.api.ApiMapeador;

public final class TransferenciaMapeador implements
        ApiMapeador<Transferencia, TransferenciaCrearDto, TransferenciaDto, TransferenciaActualizarDto> {

    public static final TransferenciaMapeador SINGLETON = new TransferenciaMapeador();

    private TransferenciaMapeador() {
    }

    @Override
    public TransferenciaDto toDto(Transferencia entidad) {
        if (entidad == null) {
            return null;
        }
        return new TransferenciaDto(
                entidad.getIdTransferencia(),
                CuentaBancariaMapeador.SINGLETON.toDto(entidad.getIdTransferenciaPk().getCuentaOrigen()),
                CuentaBancariaMapeador.SINGLETON.toDto(entidad.getIdTransferenciaPk().getCuentaDestino()),
                entidad.getFechaTransferencia(),
                entidad.getValorTransferencia(),
                entidad.getDescripcionTransferencia()
        );
    }

    @Override
    public Transferencia toEntityFromCrear(TransferenciaCrearDto dto) {
        if (dto == null) {
            return null;
        }
        Transferencia entidad = new Transferencia(
                dto.idCuentaOrigen(),
                dto.idCuentaDestino()
        );
        entidad.setFechaTransferencia(dto.fechaTransferencia());
        entidad.setValorTransferencia(dto.valorTransferencia());
        entidad.setDescripcionTransferencia(dto.descripcionTransferencia());
        return entidad;
    }

    @Override
    public Transferencia toEntityFromActualizar(TransferenciaActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        Transferencia entidad = new Transferencia(
                dto.idCuentaOrigen(),
                dto.idCuentaDestino()
        );
        entidad.setIdTransferencia(dto.idTransferencia());
        entidad.setFechaTransferencia(dto.fechaTransferencia());
        entidad.setValorTransferencia(dto.valorTransferencia());
        entidad.setDescripcionTransferencia(dto.descripcionTransferencia());
        return entidad;
    }

    @Override
    public List<TransferenciaDto> toDtoList(List<Transferencia> entidades) {
        List<TransferenciaDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (Transferencia entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
