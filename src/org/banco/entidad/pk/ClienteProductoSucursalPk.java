package org.banco.entidad.pk;

import org.banco.entidad.Cliente;
import org.banco.entidad.ProductoFinanciero;
import org.banco.entidad.Sucursal;

public class ClienteProductoSucursalPk {

    private final Cliente cliente;
    private final ProductoFinanciero productoFinanciero;
    private final Sucursal sucursal;

    public ClienteProductoSucursalPk(Cliente cliente, ProductoFinanciero productoFinanciero, Sucursal sucursal) {
        this.cliente = cliente;
        this.productoFinanciero = productoFinanciero;
        this.sucursal = sucursal;
    }

    public static ClienteProductoSucursalPk conIds(Integer idCliente, Integer idProductoFinanciero, Integer idSucursal) {
        Cliente cli = new Cliente();
        cli.setIdCliente(idCliente);

        ProductoFinanciero prod = new ProductoFinanciero();
        prod.setIdProductoFinanciero(idProductoFinanciero);

        Sucursal suc = new Sucursal();
        suc.setIdSucursal(idSucursal);

        return new ClienteProductoSucursalPk(cli, prod, suc);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ProductoFinanciero getProductoFinanciero() {
        return productoFinanciero;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }
}
