package org.banco.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.banco.dto.transferencia.TransferenciaActualizarDto;
import org.banco.dto.transferencia.TransferenciaCrearDto;
import org.banco.dto.transferencia.TransferenciaDto;
import org.banco.dto.transferencia.TransferenciaIdentificadorDto;
import org.banco.entidad.CuentaBancaria;
import org.banco.entidad.Transferencia;
import org.banco.entidad.pk.TransferenciaPk;
import org.banco.mapeador.CuentaBancariaMapeador;
import org.banco.mapeador.TransferenciaMapeador;
import org.banco.repositorio.CuentaBancariaRepositorio;
import org.banco.repositorio.TransferenciaRepositorio;
import org.banco.servicio.api.ApiOperacionCompuestaServicio;

public class TransferenciaServicio implements ApiOperacionCompuestaServicio<
        TransferenciaCrearDto, TransferenciaDto, TransferenciaActualizarDto, TransferenciaIdentificadorDto> {

    private final TransferenciaRepositorio repositorio;
    private final CuentaBancariaRepositorio cuentaBancariaRepositorio;

    public TransferenciaServicio(
            TransferenciaRepositorio repo,
            CuentaBancariaRepositorio cuentaRepo) {
        this.repositorio = repo;
        this.cuentaBancariaRepositorio = cuentaRepo;
    }

    private TransferenciaPk toPk(TransferenciaIdentificadorDto idDto) {
        return TransferenciaPk.conIds(idDto.idCuentaOrigen(), idDto.idCuentaDestino());
    }

    private Transferencia buscarEntidadPorPk(TransferenciaPk codigo) {
        int idOrigen = codigo.getCuentaOrigen().getIdCuentaBancaria();
        int idDestino = codigo.getCuentaDestino().getIdCuentaBancaria();

        for (Transferencia t : repositorio.findAll()) {
            TransferenciaPk pkTemp = t.getIdTransferenciaPk();
            if (pkTemp.getCuentaOrigen().getIdCuentaBancaria() == idOrigen
                    && pkTemp.getCuentaDestino().getIdCuentaBancaria() == idDestino) {
                return t;
            }
        }
        return null;
    }

    @Override
    public TransferenciaCrearDto insertInto(TransferenciaCrearDto creacionDTO) {
        CuentaBancaria origen = cuentaBancariaRepositorio.findById(creacionDTO.idCuentaOrigen());
        if (origen == null) {
            return null;
        }
        CuentaBancaria destino = cuentaBancariaRepositorio.findById(creacionDTO.idCuentaDestino());
        if (destino == null) {
            return null;
        }
        if (creacionDTO.idCuentaOrigen().equals(creacionDTO.idCuentaDestino())) {
            return null;
        }
        Transferencia entidad = TransferenciaMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        if (buscarEntidadPorPk(entidad.getIdTransferenciaPk()) != null) {
            return null;
        }
        Transferencia nueva = repositorio.save(entidad);
        return TransferenciaMapeador.SINGLETON.toCrearDto(nueva);
    }

    @Override
    public TransferenciaActualizarDto updateSet(
            TransferenciaIdentificadorDto id,
            TransferenciaActualizarDto actualizarDto) {
        TransferenciaPk pk = toPk(id);
        Transferencia existente = buscarEntidadPorPk(pk);
        if (existente == null) {
            return null;
        }
        repositorio.delete(existente);
        Transferencia actualizado = new Transferencia(id.idCuentaOrigen(), id.idCuentaDestino());
        actualizado.setFechaTransferencia(actualizarDto.fechaTransferencia());
        actualizado.setValorTransferencia(actualizarDto.valorTransferencia());
        actualizado.setDescripcionTransferencia(actualizarDto.descripcionTransferencia());
        Transferencia grabado = repositorio.save(actualizado);
        return TransferenciaMapeador.SINGLETON.toActualizarDto(grabado);
    }

    @Override
    public Boolean deleteFrom(TransferenciaIdentificadorDto codigo) {
        TransferenciaPk pk = toPk(codigo);
        Transferencia existente = buscarEntidadPorPk(pk);
        if (existente == null) {
            return false;
        }
        return repositorio.delete(existente);
    }

    @Override
    public int countRows() {
        return repositorio.findAll().size();
    }

    @Override
    public List<TransferenciaDto> selectFrom() {
        List<Transferencia> transferencias = repositorio.findAll();
        List<CuentaBancaria> cuentas = cuentaBancariaRepositorio.findAll();

        Map<Integer, CuentaBancaria> mapaCuentas = mapaCuentas(cuentas);

        transferencias = sinCuentaOrigenNula(transferencias);
        transferencias = sinCuentaDestinoNula(transferencias);
        transferencias = sinCuentaOrigenInexistente(transferencias, mapaCuentas);
        transferencias = sinCuentaDestinoInexistente(transferencias, mapaCuentas);
        hidratarCuentas(transferencias, mapaCuentas);

        List<TransferenciaDto> resultado = new ArrayList<>();
        for (Transferencia t : transferencias) {
            int idOrigen = t.getIdTransferenciaPk().getCuentaOrigen().getIdCuentaBancaria();
            int idDestino = t.getIdTransferenciaPk().getCuentaDestino().getIdCuentaBancaria();
            CuentaBancaria origenReal = mapaCuentas.get(idOrigen);
            CuentaBancaria destinoReal = mapaCuentas.get(idDestino);
            resultado.add(new TransferenciaDto(
                    t.getIdTransferencia(),
                    CuentaBancariaMapeador.SINGLETON.toDto(origenReal),
                    CuentaBancariaMapeador.SINGLETON.toDto(destinoReal),
                    t.getFechaTransferencia(),
                    t.getValorTransferencia(),
                    t.getDescripcionTransferencia()
            ));
        }
        return resultado;
    }

    @Override
    public TransferenciaDto selectOne(TransferenciaIdentificadorDto codigo) {
        TransferenciaPk pk = toPk(codigo);
        Transferencia entidad = buscarEntidadPorPk(pk);
        if (entidad == null) {
            return null;
        }
        CuentaBancaria origen = cuentaBancariaRepositorio.findById(pk.getCuentaOrigen().getIdCuentaBancaria());
        if (origen == null) {
            return null;
        }
        CuentaBancaria destino = cuentaBancariaRepositorio.findById(pk.getCuentaDestino().getIdCuentaBancaria());
        if (destino == null) {
            return null;
        }
        return new TransferenciaDto(
                entidad.getIdTransferencia(),
                CuentaBancariaMapeador.SINGLETON.toDto(origen),
                CuentaBancariaMapeador.SINGLETON.toDto(destino),
                entidad.getFechaTransferencia(),
                entidad.getValorTransferencia(),
                entidad.getDescripcionTransferencia()
        );
    }

    private Map<Integer, CuentaBancaria> mapaCuentas(List<CuentaBancaria> cuentas) {
        Map<Integer, CuentaBancaria> mapa = new HashMap<>();
        for (CuentaBancaria c : cuentas) {
            mapa.put(c.getIdCuentaBancaria(), c);
        }
        return mapa;
    }

    private List<Transferencia> sinCuentaOrigenNula(List<Transferencia> transferencias) {
        List<Transferencia> resultado = new ArrayList<>();
        for (Transferencia t : transferencias) {
            if (t.getIdTransferenciaPk().getCuentaOrigen() == null) {
                System.out.print("ERROR: Transferencia cuenta origen nula");
                System.out.println(", registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private List<Transferencia> sinCuentaDestinoNula(List<Transferencia> transferencias) {
        List<Transferencia> resultado = new ArrayList<>();
        for (Transferencia t : transferencias) {
            if (t.getIdTransferenciaPk().getCuentaDestino() == null) {
                System.out.print("ERROR: Transferencia cuenta destino nula");
                System.out.println(", registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private List<Transferencia> sinCuentaOrigenInexistente(
            List<Transferencia> transferencias,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        List<Transferencia> resultado = new ArrayList<>();
        for (Transferencia t : transferencias) {
            int idOrigen = t.getIdTransferenciaPk().getCuentaOrigen().getIdCuentaBancaria();
            if (!mapaCuentas.containsKey(idOrigen)) {
                System.out.print("ERROR: Transferencia cuenta origen " + idOrigen);
                System.out.println(" no existe, registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private List<Transferencia> sinCuentaDestinoInexistente(
            List<Transferencia> transferencias,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        List<Transferencia> resultado = new ArrayList<>();
        for (Transferencia t : transferencias) {
            int idDestino = t.getIdTransferenciaPk().getCuentaDestino().getIdCuentaBancaria();
            if (!mapaCuentas.containsKey(idDestino)) {
                System.out.print("ERROR: Transferencia cuenta destino " + idDestino);
                System.out.println(" no existe, registro descartado");
                continue;
            }
            resultado.add(t);
        }
        return resultado;
    }

    private void hidratarCuentas(
            List<Transferencia> transferencias,
            Map<Integer, CuentaBancaria> mapaCuentas) {
        for (Transferencia t : transferencias) {
            TransferenciaPk pk = t.getIdTransferenciaPk();
            CuentaBancaria origenReal = mapaCuentas.get(pk.getCuentaOrigen().getIdCuentaBancaria());
            CuentaBancaria destinoReal = mapaCuentas.get(pk.getCuentaDestino().getIdCuentaBancaria());
            pk.getCuentaOrigen().setIdCuentaBancaria(origenReal.getIdCuentaBancaria());
            pk.getCuentaOrigen().setNumeroCuentaBancaria(origenReal.getNumeroCuentaBancaria());
            pk.getCuentaOrigen().setTipoCuentaBancaria(origenReal.getTipoCuentaBancaria());
            pk.getCuentaOrigen().setSaldoCuentaBancaria(origenReal.getSaldoCuentaBancaria());
            pk.getCuentaOrigen().setFechaAperturaCuentaBancaria(origenReal.getFechaAperturaCuentaBancaria());
            pk.getCuentaOrigen().setEstadoCuentaBancaria(origenReal.getEstadoCuentaBancaria());
            pk.getCuentaOrigen().setClienteCuentaBancaria(origenReal.getClienteCuentaBancaria());
            pk.getCuentaDestino().setIdCuentaBancaria(destinoReal.getIdCuentaBancaria());
            pk.getCuentaDestino().setNumeroCuentaBancaria(destinoReal.getNumeroCuentaBancaria());
            pk.getCuentaDestino().setTipoCuentaBancaria(destinoReal.getTipoCuentaBancaria());
            pk.getCuentaDestino().setSaldoCuentaBancaria(destinoReal.getSaldoCuentaBancaria());
            pk.getCuentaDestino().setFechaAperturaCuentaBancaria(destinoReal.getFechaAperturaCuentaBancaria());
            pk.getCuentaDestino().setEstadoCuentaBancaria(destinoReal.getEstadoCuentaBancaria());
            pk.getCuentaDestino().setClienteCuentaBancaria(destinoReal.getClienteCuentaBancaria());
        }
    }
}