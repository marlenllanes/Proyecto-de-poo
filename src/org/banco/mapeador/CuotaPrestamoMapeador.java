package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cuotaprestamo.CuotaPrestamoActualizarDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoCrearDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoDto;
import org.banco.entidad.CuotaPrestamo;
import org.banco.mapeador.api.ApiMapeador;

public final class CuotaPrestamoMapeador implements
        ApiMapeador<CuotaPrestamo, CuotaPrestamoCrearDto, CuotaPrestamoDto, CuotaPrestamoActualizarDto> {

    public static final CuotaPrestamoMapeador SINGLETON = new CuotaPrestamoMapeador();

    private CuotaPrestamoMapeador() {
    }

    @Override
    public CuotaPrestamoDto toDto(CuotaPrestamo entidad) {
        if (entidad == null) {
            return null;
        }
        return new CuotaPrestamoDto(
                entidad.getIdCuotaPrestamo(),
                PrestamoMapeador.SINGLETON.toDto(entidad.getPrestamoCuotaPrestamo()),
                entidad.getNumeroCuotaPrestamo(),
                entidad.getFechaVencimientoCuotaPrestamo(),
                entidad.getValorCuotaPrestamo(),
                entidad.getEstadoCuotaPrestamo()
        );
    }

    @Override
    public CuotaPrestamo toEntityFromCrear(CuotaPrestamoCrearDto dto) {
        if (dto == null) {
            return null;
        }
        CuotaPrestamo entidad = new CuotaPrestamo();
        entidad.setNumeroCuotaPrestamo(dto.numeroCuotaPrestamo());
        entidad.setFechaVencimientoCuotaPrestamo(dto.fechaVencimientoCuotaPrestamo());
        entidad.setValorCuotaPrestamo(dto.valorCuotaPrestamo());
        entidad.setEstadoCuotaPrestamo(dto.estadoCuotaPrestamo());
        return entidad;
    }

    @Override
    public CuotaPrestamo toEntityFromActualizar(CuotaPrestamoActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        CuotaPrestamo entidad = new CuotaPrestamo();
        entidad.setIdCuotaPrestamo(dto.idCuotaPrestamo());
        entidad.setNumeroCuotaPrestamo(dto.numeroCuotaPrestamo());
        entidad.setFechaVencimientoCuotaPrestamo(dto.fechaVencimientoCuotaPrestamo());
        entidad.setValorCuotaPrestamo(dto.valorCuotaPrestamo());
        entidad.setEstadoCuotaPrestamo(dto.estadoCuotaPrestamo());
        return entidad;
    }

    @Override
    public List<CuotaPrestamoDto> toDtoList(List<CuotaPrestamo> entidades) {
        List<CuotaPrestamoDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (CuotaPrestamo entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
