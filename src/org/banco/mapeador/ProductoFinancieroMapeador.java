package org.banco.mapeador;

import java.util.ArrayList;
import java.util.List;
import org.banco.dto.productofinanciero.ProductoFinancieroActualizarDto;
import org.banco.dto.productofinanciero.ProductoFinancieroCrearDto;
import org.banco.dto.productofinanciero.ProductoFinancieroDto;
import org.banco.entidad.ProductoFinanciero;
import org.banco.mapeador.api.ApiMapeador;

public final class ProductoFinancieroMapeador implements
        ApiMapeador<ProductoFinanciero, ProductoFinancieroCrearDto, ProductoFinancieroDto, ProductoFinancieroActualizarDto> {

    public static final ProductoFinancieroMapeador SINGLETON = new ProductoFinancieroMapeador();

    private ProductoFinancieroMapeador() {
    }

    @Override
    public ProductoFinancieroDto toDto(ProductoFinanciero entidad) {
        if (entidad == null) {
            return null;
        }

        ProductoFinancieroDto dto = new ProductoFinancieroDto(
                entidad.getIdProductoFinanciero(),
                entidad.getNombreProductoFinanciero(),
                entidad.getTipoProductoFinanciero(),
                entidad.getTasaInteresProductoFinanciero(),
                entidad.getDescripcionProductoFinanciero()
        );

        return dto;
    }

    @Override
    public ProductoFinanciero toEntityFromCrear(ProductoFinancieroCrearDto dto) {
        if (dto == null) {
            return null;
        }

        ProductoFinanciero entidad = new ProductoFinanciero();
        entidad.setNombreProductoFinanciero(dto.nombreProductoFinanciero());
        entidad.setTipoProductoFinanciero(dto.tipoProductoFinanciero());
        entidad.setTasaInteresProductoFinanciero(dto.tasaInteresProductoFinanciero());
        entidad.setDescripcionProductoFinanciero(dto.descripcionProductoFinanciero());

        return entidad;

    }

    @Override
    public ProductoFinanciero toEntityFromActualizar(ProductoFinancieroActualizarDto dto) {
        if (dto == null) {
            return null;
        }
        ProductoFinanciero entidad = new ProductoFinanciero();
        entidad.setIdProductoFinanciero(dto.idProductoFinanciero());
        entidad.setNombreProductoFinanciero(dto.nombreProductoFinanciero());
        entidad.setTipoProductoFinanciero(dto.tipoProductoFinanciero());
        entidad.setTasaInteresProductoFinanciero(dto.tasaInteresProductoFinanciero());
        entidad.setDescripcionProductoFinanciero(dto.descripcionProductoFinanciero());

        return entidad;

    }

    @Override
    public List<ProductoFinancieroDto> toDtoList(List<ProductoFinanciero> entidades) {
        List<ProductoFinancieroDto> arreglo = new ArrayList<>();

        if (entidades == null) {
            return arreglo;
        }
        for (ProductoFinanciero entidad : entidades) {
            ProductoFinancieroDto dto = toDto(entidad);
            arreglo.add(dto);
        }

        return arreglo;
    }

}
