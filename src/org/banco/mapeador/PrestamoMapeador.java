package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.prestamo.PrestamoActualizarDto;
import org.banco.dto.prestamo.PrestamoCrearDto;
import org.banco.dto.prestamo.PrestamoDto;
import org.banco.entidad.Prestamo;
import org.banco.mapeador.api.ApiMapeador;

public final class PrestamoMapeador implements
        ApiMapeador<Prestamo, PrestamoCrearDto, PrestamoDto, PrestamoActualizarDto> {

    public static final PrestamoMapeador SINGLETON = new PrestamoMapeador();

    private PrestamoMapeador() {
    }

    @Override
    public PrestamoDto toDto(Prestamo entidad) {
        if (entidad == null) {
            return null;
        }
        return new PrestamoDto(
                entidad.getIdPrestamo(),
                ClienteMapeador.SINGLETON.toDto(entidad.getClientePrestamo()),
                entidad.getMontoPrestamo(),
                entidad.getTasaInteresPrestamo(),
                entidad.getFechaDesembolsoPrestamo(),
                entidad.getEstadoPrestamo()
        );
    }

    @Override
    public Prestamo toEntityFromCrear(PrestamoCrearDto dto) {
        if (dto == null) {
            return null;
        }
        Prestamo entidad = new Prestamo();
        entidad.setMontoPrestamo(dto.montoPrestamo());
        entidad.setTasaInteresPrestamo(dto.tasaInteresPrestamo());
        entidad.setFechaDesembolsoPrestamo(dto.fechaDesembolsoPrestamo());
        entidad.setEstadoPrestamo(dto.estadoPrestamo());
        return entidad;
    }

    @Override
    public Prestamo toEntityFromActualizar(PrestamoActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        Prestamo entidad = new Prestamo();
        entidad.setIdPrestamo(dto.idPrestamo());
        entidad.setMontoPrestamo(dto.montoPrestamo());
        entidad.setTasaInteresPrestamo(dto.tasaInteresPrestamo());
        entidad.setFechaDesembolsoPrestamo(dto.fechaDesembolsoPrestamo());
        entidad.setEstadoPrestamo(dto.estadoPrestamo());
        return entidad;
    }

    @Override
    public List<PrestamoDto> toDtoList(List<Prestamo> entidades) {
        List<PrestamoDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (Prestamo entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
