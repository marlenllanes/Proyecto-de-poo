package org.banco.servicio;

import java.util.List;
import org.banco.dto.productofinanciero.ProductoFinancieroActualizarDto;
import org.banco.dto.productofinanciero.ProductoFinancieroCrearDto;
import org.banco.dto.productofinanciero.ProductoFinancieroDto;
import org.banco.entidad.ProductoFinanciero;
import org.banco.mapeador.ProductoFinancieroMapeador;
import org.banco.servicio.api.ApiOperacionServicio;
import org.banco.repositorio.ProductoFinacieroRepositorio;

public class ProductoFinancieroServicio implements ApiOperacionServicio
        <ProductoFinancieroCrearDto, ProductoFinancieroDto, ProductoFinancieroActualizarDto, Integer> {

    private final ProductoFinacieroRepositorio repositorio;
    
    public ProductoFinancieroServicio (ProductoFinacieroRepositorio repo) {
        this.repositorio = repo;
    }

    @Override
    public ProductoFinancieroDto insertInto(ProductoFinancieroCrearDto creacionDTO) {
        ProductoFinanciero entidad = ProductoFinancieroMapeador.SINGLETON.toEntityFromCrear(creacionDTO);
        ProductoFinanciero nuevo = repositorio.save(entidad);
        return ProductoFinancieroMapeador.SINGLETON.toDto(nuevo);
    }

    @Override
    public ProductoFinancieroDto updateSet(Integer id, ProductoFinancieroActualizarDto actualizarDto) {
       ProductoFinanciero entidad = ProductoFinancieroMapeador.SINGLETON.toEntityFromActualizar(actualizarDto);
       entidad.setIdProductoFinanciero(id);
       ProductoFinanciero actualizado = repositorio.update(entidad);
       return ProductoFinancieroMapeador.SINGLETON.toDto(actualizado);
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        return repositorio.deleteById(codigo);
    }

    @Override
    public List<ProductoFinancieroDto> selectFrom() {
         List<ProductoFinanciero> productoFin = repositorio.findAll();
         return ProductoFinancieroMapeador.SINGLETON.toDtoList(productoFin);
    }

    @Override
    public ProductoFinancieroDto selectOne(Integer codigo) {
        ProductoFinanciero entidad = repositorio.findById(codigo);
        return ProductoFinancieroMapeador.SINGLETON.toDto(entidad);
    }

    @Override
    public int countRows() {
        return repositorio.count();
    }

    @Override
    public int lastSerial() {
        return repositorio.getLastId();
    }
    
}
