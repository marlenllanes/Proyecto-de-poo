package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.cuotaprestamo.CuotaPrestamoActualizarDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoCrearDto;
import org.banco.dto.cuotaprestamo.CuotaPrestamoDto;
import org.banco.entidad.CuotaPrestamo;
import org.banco.entidad.Prestamo;
import org.banco.mapeador.CuotaPrestamoMapeador;
import org.banco.repositorio.CuotaPrestamoRepositorio;
import org.banco.repositorio.PrestamoRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class CuotaPrestamoServicio implements ApiOperacionServicio
        <CuotaPrestamoCrearDto, CuotaPrestamoDto, CuotaPrestamoActualizarDto, Integer> {

    private final CuotaPrestamoRepositorio repositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    public CuotaPrestamoServicio(CuotaPrestamoRepositorio repo, PrestamoRepositorio prestamoRepo) {
        this.repositorio = repo;
        this.prestamoRepositorio = prestamoRepo;
    }

    @Override
    public CuotaPrestamoDto insertInto(CuotaPrestamoCrearDto creacionDTO) {
        CuotaPrestamo entidad = CuotaPrestamoMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Prestamo prestamo = prestamoRepositorio.findById(creacionDTO.idPrestamo());
        if (prestamo == null) {
            return null;
        }
        entidad.setPrestamoCuotaPrestamo(prestamo);
        CuotaPrestamo nueva = repositorio.save(entidad);
        return CuotaPrestamoMapeador.SINGLETON.toDto(nueva);
    }

    @Override
    public CuotaPrestamoDto updateSet(Integer id, CuotaPrestamoActualizarDto actualizarDto) {
        CuotaPrestamo entidad = CuotaPrestamoMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdCuotaPrestamo(id);
        Prestamo prestamo = prestamoRepositorio.findById(actualizarDto.idPrestamo());
        if (prestamo == null) {
            return null;
        }
        entidad.setPrestamoCuotaPrestamo(prestamo);
        CuotaPrestamo actualizada = repositorio.update(entidad);
        return CuotaPrestamoMapeador.SINGLETON.toDto(actualizada);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<CuotaPrestamoDto> selectFrom() {
        List<CuotaPrestamo> cuotas = repositorio.findAll();
        List<Prestamo> prestamos = prestamoRepositorio.findAll();

        Map<Integer, Prestamo> mapaPrestamos = mapaPrestamos(prestamos);

        cuotas = sinPrestamoNulo(cuotas);
        cuotas = sinPrestamoInexistente(cuotas, mapaPrestamos);
        hidratarPrestamo(cuotas, mapaPrestamos);

        return CuotaPrestamoMapeador.SINGLETON.toDtoList(cuotas);
    }

    @Override
    public CuotaPrestamoDto selectOne(Integer codigo) {
        CuotaPrestamo entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return CuotaPrestamoMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }

    private Map<Integer, Prestamo> mapaPrestamos(List<Prestamo> prestamos) {
        Map<Integer, Prestamo> mapa = new HashMap<>();
        for (Prestamo p : prestamos) {
            mapa.put(p.getIdPrestamo(), p);
        }
        return mapa;
    }

    private List<CuotaPrestamo> sinPrestamoNulo(List<CuotaPrestamo> cuotas) {
        List<CuotaPrestamo> resultado = new ArrayList<>();
        for (CuotaPrestamo cuota : cuotas) {
            if (cuota.getPrestamoCuotaPrestamo() == null) {
                System.out.print("ERROR: CuotaPrestamo " + cuota.getIdCuotaPrestamo());
                System.out.println(" prestamo nulo, registro descartado");
                continue;
            }
            resultado.add(cuota);
        }
        return resultado;
    }

    private List<CuotaPrestamo> sinPrestamoInexistente(
            List<CuotaPrestamo> cuotas,
            Map<Integer, Prestamo> mapaPrestamos) {
        List<CuotaPrestamo> resultado = new ArrayList<>();
        for (CuotaPrestamo cuota : cuotas) {
            int idPrestamo = cuota.getPrestamoCuotaPrestamo().getIdPrestamo();
            if (!mapaPrestamos.containsKey(idPrestamo)) {
                System.out.print("ERROR: CuotaPrestamo " + cuota.getIdCuotaPrestamo());
                System.out.println(" prestamo " + idPrestamo + " no existe, registro descartado");
                continue;
            }
            resultado.add(cuota);
        }
        return resultado;
    }

    private void hidratarPrestamo(List<CuotaPrestamo> cuotas, Map<Integer, Prestamo> mapaPrestamos) {
        for (CuotaPrestamo cuota : cuotas) {
            int idPrestamo = cuota.getPrestamoCuotaPrestamo().getIdPrestamo();
            cuota.setPrestamoCuotaPrestamo(mapaPrestamos.get(idPrestamo));
        }
    }

    private boolean hidratarSelectOne(CuotaPrestamo entidad) {
        if (entidad.getPrestamoCuotaPrestamo() == null) {
            return false;
        }
        int idPrestamo = entidad.getPrestamoCuotaPrestamo().getIdPrestamo();
        Prestamo prestamo = prestamoRepositorio.findById(idPrestamo);
        if (prestamo == null) {
            return false;
        }
        entidad.setPrestamoCuotaPrestamo(prestamo);
        return true;
    }
}
