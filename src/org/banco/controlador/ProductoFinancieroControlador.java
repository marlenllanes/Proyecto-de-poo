package org.banco.controlador;

import java.util.List;
import java.util.Optional;
import org.banco.dto.productofinanciero.ProductoFinancieroActualizarDto;
import org.banco.dto.productofinanciero.ProductoFinancieroCrearDto;
import org.banco.dto.productofinanciero.ProductoFinancieroDto;
import org.banco.servicio.ProductoFinancieroServicio;

public class ProductoFinancieroControlador {

    private final ProductoFinancieroServicio servicio;

    public ProductoFinancieroControlador(ProductoFinancieroServicio servicio) {
        this.servicio = servicio;
    }   

    public Optional<ProductoFinancieroDto> crear(ProductoFinancieroCrearDto dto) {
        return Optional.ofNullable(servicio.insertInto(dto));
    }

    public Optional<ProductoFinancieroDto> actualizar(Integer id, ProductoFinancieroActualizarDto dto) {
        return Optional.ofNullable(servicio.updateSet(id, dto));
    }

    public List<ProductoFinancieroDto> obtenerTodos() {
        return servicio.selectFrom();
    }

    public Optional<ProductoFinancieroDto> obtenerUno(Integer id) {
        return Optional.ofNullable(servicio.selectOne(id));
    }

    public boolean eliminar(Integer id) {
        Boolean resultado = servicio.deleteFrom(id);
        return resultado != null && resultado;
    }

    public int contar() {
        return servicio.countRows();
    }

    public int ultimoSerial() {
        return servicio.lastSerial();
    }
}
