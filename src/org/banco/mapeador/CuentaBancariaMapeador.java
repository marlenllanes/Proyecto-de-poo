package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.cuentabancaria.CuentaBancariaActualizarDto;
import org.banco.dto.cuentabancaria.CuentaBancariaCrearDto;
import org.banco.dto.cuentabancaria.CuentaBancariaDto;
import org.banco.entidad.CuentaBancaria;
import org.banco.mapeador.api.ApiMapeador;

public final class CuentaBancariaMapeador implements
        ApiMapeador<CuentaBancaria, CuentaBancariaCrearDto, CuentaBancariaDto, CuentaBancariaActualizarDto> {

    public static final CuentaBancariaMapeador SINGLETON = new CuentaBancariaMapeador();

    private CuentaBancariaMapeador() {
    }

    @Override
    public CuentaBancariaDto toDto(CuentaBancaria entidad) {
        if (entidad == null) {
            return null;
        }
        return new CuentaBancariaDto(
                entidad.getIdCuentaBancaria(),
                entidad.getNumeroCuentaBancaria(),
                entidad.getTipoCuentaBancaria(),
                entidad.getSaldoCuentaBancaria(),
                entidad.getFechaAperturaCuentaBancaria(),
                entidad.getEstadoCuentaBancaria(),
                ClienteMapeador.SINGLETON.toDto(entidad.getClienteCuentaBancaria())
        );
    }

    @Override
    public CuentaBancaria toEntityFromCrear(CuentaBancariaCrearDto dto) {
        if (dto == null) {
            return null;
        }
        CuentaBancaria entidad = new CuentaBancaria();
        entidad.setNumeroCuentaBancaria(dto.numeroCuentaBancaria());
        entidad.setTipoCuentaBancaria(dto.tipoCuentaBancaria());
        entidad.setSaldoCuentaBancaria(dto.saldoCuentaBancaria());
        entidad.setFechaAperturaCuentaBancaria(dto.fechaAperturaCuentaBancaria());
        entidad.setEstadoCuentaBancaria(dto.estadoCuentaBancaria());
        return entidad;
    }

    @Override
    public CuentaBancaria toEntityFromActualizar(CuentaBancariaActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        CuentaBancaria entidad = new CuentaBancaria();
        entidad.setIdCuentaBancaria(dto.idCuentaBancaria());
        entidad.setNumeroCuentaBancaria(dto.numeroCuentaBancaria());
        entidad.setTipoCuentaBancaria(dto.tipoCuentaBancaria());
        entidad.setSaldoCuentaBancaria(dto.saldoCuentaBancaria());
        entidad.setFechaAperturaCuentaBancaria(dto.fechaAperturaCuentaBancaria());
        entidad.setEstadoCuentaBancaria(dto.estadoCuentaBancaria());
        return entidad;
    }

    @Override
    public List<CuentaBancariaDto> toDtoList(List<CuentaBancaria> entidades) {
        List<CuentaBancariaDto> arreglo = new ArrayList<>();
        if (entidades == null) {
            return arreglo;
        }
        for (CuentaBancaria entidad : entidades) {
            arreglo.add(toDto(entidad));
        }
        return arreglo;
    }
}
