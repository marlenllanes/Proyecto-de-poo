package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.pagoprestamo.PagoPrestamoActualizarDto;
import org.banco.dto.pagoprestamo.PagoPrestamoCrearDto;
import org.banco.dto.pagoprestamo.PagoPrestamoDto;
import org.banco.entidad.PagoPrestamo;
import org.banco.entidad.Prestamo;
import org.banco.mapeador.PagoPrestamoMapeador;
import org.banco.repositorio.PagoPrestamoRepositorio;
import org.banco.repositorio.PrestamoRepositorio;
import org.banco.servicio.api.ApiOperacionServicio;

public class PagoPrestamoServicio implements ApiOperacionServicio
        <PagoPrestamoCrearDto, PagoPrestamoDto, PagoPrestamoActualizarDto, Integer> {

    private final PagoPrestamoRepositorio repositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    public PagoPrestamoServicio(PagoPrestamoRepositorio repo, PrestamoRepositorio prestamoRepo) {
        this.repositorio = repo;
        this.prestamoRepositorio = prestamoRepo;
    }

    @Override
    public PagoPrestamoDto insertInto(PagoPrestamoCrearDto creacionDTO) {
        PagoPrestamo entidad = PagoPrestamoMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        Prestamo prestamo = prestamoRepositorio.findById(creacionDTO.idPrestamo());
        if (prestamo == null) {
            return null;
        }
        entidad.setPrestamoPagoPrestamo(prestamo);
        PagoPrestamo nuevo = repositorio.save(entidad);
        return PagoPrestamoMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public PagoPrestamoDto updateSet(Integer id, PagoPrestamoActualizarDto actualizarDto) {
        PagoPrestamo entidad = PagoPrestamoMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
        entidad.setIdPagoPrestamo(id);
        Prestamo prestamo = prestamoRepositorio.findById(actualizarDto.idPrestamo());
        if (prestamo == null) {
            return null;
        }
        entidad.setPrestamoPagoPrestamo(prestamo);
        PagoPrestamo actualizado = repositorio.update(entidad);
        return PagoPrestamoMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<PagoPrestamoDto> selectFrom() {
        List<PagoPrestamo> pagos = repositorio.findAll();
        List<Prestamo> prestamos = prestamoRepositorio.findAll();

        Map<Integer, Prestamo> mapaPrestamos = mapaPrestamos(prestamos);

        pagos = sinPrestamoNulo(pagos);
        pagos = sinPrestamoInexistente(pagos, mapaPrestamos);
        hidratarPrestamo(pagos, mapaPrestamos);

        return PagoPrestamoMapeador.SINGLETON.toDtoList(pagos);
    }

    @Override
    public PagoPrestamoDto selectOne(Integer codigo) {
        PagoPrestamo entidad = repositorio.findById(codigo);
        if (entidad == null) {
            return null;
        }
        if (!hidratarSelectOne(entidad)) {
            return null;
        }
        return PagoPrestamoMapeador.SINGLETON.toDto(entidad);
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

    private List<PagoPrestamo> sinPrestamoNulo(List<PagoPrestamo> pagos) {
        List<PagoPrestamo> resultado = new ArrayList<>();
        for (PagoPrestamo pago : pagos) {
            if (pago.getPrestamoPagoPrestamo() == null) {
                System.out.print("ERROR: PagoPrestamo " + pago.getIdPagoPrestamo());
                System.out.println(" prestamo nulo, registro descartado");
                continue;
            }
            resultado.add(pago);
        }
        return resultado;
    }

    private List<PagoPrestamo> sinPrestamoInexistente(
            List<PagoPrestamo> pagos,
            Map<Integer, Prestamo> mapaPrestamos) {
        List<PagoPrestamo> resultado = new ArrayList<>();
        for (PagoPrestamo pago : pagos) {
            int idPrestamo = pago.getPrestamoPagoPrestamo().getIdPrestamo();
            if (!mapaPrestamos.containsKey(idPrestamo)) {
                System.out.print("ERROR: PagoPrestamo " + pago.getIdPagoPrestamo());
                System.out.println(" prestamo " + idPrestamo + " no existe, registro descartado");
                continue;
            }
            resultado.add(pago);
        }
        return resultado;
    }

    private void hidratarPrestamo(List<PagoPrestamo> pagos, Map<Integer, Prestamo> mapaPrestamos) {
        for (PagoPrestamo pago : pagos) {
            int idPrestamo = pago.getPrestamoPagoPrestamo().getIdPrestamo();
            pago.setPrestamoPagoPrestamo(mapaPrestamos.get(idPrestamo));
        }
    }

    private boolean hidratarSelectOne(PagoPrestamo entidad) {
        if (entidad.getPrestamoPagoPrestamo() == null) {
            return false;
        }
        int idPrestamo = entidad.getPrestamoPagoPrestamo().getIdPrestamo();
        Prestamo prestamo = prestamoRepositorio.findById(idPrestamo);
        if (prestamo == null) {
            return false;
        }
        entidad.setPrestamoPagoPrestamo(prestamo);
        return true;
    }
}