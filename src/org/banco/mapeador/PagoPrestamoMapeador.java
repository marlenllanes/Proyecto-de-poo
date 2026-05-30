package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.pagoprestamo.PagoPrestamoActualizarDto;
import org.banco.dto.pagoprestamo.PagoPrestamoCrearDto;
import org.banco.dto.pagoprestamo.PagoPrestamoDto;
import org.banco.entidad.PagoPrestamo;
import org.banco.mapeador.api.ApiMapeador;

public final class PagoPrestamoMapeador implements
        ApiMapeador<PagoPrestamo, PagoPrestamoCrearDto, PagoPrestamoDto, PagoPrestamoActualizarDto> {

    public static final PagoPrestamoMapeador SINGLETON = new PagoPrestamoMapeador();

    private PagoPrestamoMapeador() {
    }

    @Override
    public PagoPrestamoDto toDto(PagoPrestamo entidad) {
        if (entidad == null) {
            return null;
        }
        return new PagoPrestamoDto(
                entidad.getIdPagoPrestamo(),
                PrestamoMapeador.SINGLETON.toDto(entidad.getPrestamoPagoPrestamo()),
                entidad.getFechaPagoPrestamo(),
                entidad.getValorPagoPrestamo(),
                entidad.getMetodoPagoPrestamo()
        );
    }

    @Override
    public PagoPrestamo toEntityFromCrear(PagoPrestamoCrearDto dto) {
        if (dto == null) {
            return null;
        }
        PagoPrestamo entidad = new PagoPrestamo();
        entidad.setFechaPagoPrestamo(dto.fechaPagoPrestamo());
        entidad.setValorPagoPrestamo(dto.valorPagoPrestamo());
        entidad.setMetodoPagoPrestamo(dto.metodoPagoPrestamo());
        return entidad;
    }

    @Override
    public PagoPrestamo toEntityFromActualizar(PagoPrestamoActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        PagoPrestamo entidad = new PagoPrestamo();
        entidad.setIdPagoPrestamo(dto.idPagoPrestamo());
        entidad.setFechaPagoPrestamo(dto.fechaPagoPrestamo());
        entidad.setValorPagoPrestamo(dto.valorPagoPrestamo());
        entidad.setMetodoPagoPrestamo(dto.metodoPagoPrestamo());
        return entidad;
    }

    @Override
    public List<PagoPrestamoDto> toDtoList(List<PagoPrestamo> entidades) {
        List<PagoPrestamoDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (PagoPrestamo entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}