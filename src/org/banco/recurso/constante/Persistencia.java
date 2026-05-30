package org.banco.recurso.constante;

import java.nio.file.Path;

public final class Persistencia extends BasePersistencia {

    private Persistencia() {
    }

    public static final Path CLIENTE = prepararArchivo("cliente.txt");
    public static final Path CUENTA_BANCARIA = prepararArchivo("cuenta_bancaria.txt");
    public static final Path MOVIMIENTO_CUENTA = prepararArchivo("movimiento_cuenta.txt");
    public static final Path TARJETA = prepararArchivo("tarjeta.txt");
    public static final Path CONSUMO_TARJETA = prepararArchivo("consumo_tarjeta.txt");

    public static final Path PRESTAMO = prepararArchivo("prestamo.txt");
    public static final Path CUOTA_PRESTAMO = prepararArchivo("cuota_prestamo.txt");
    public static final Path PAGO_PRESTAMO = prepararArchivo("pago_prestamo.txt");

    public static final Path TRANSFERENCIA = prepararArchivo("transferencia.txt");
    public static final Path TRANSFERENCIA_PK = prepararArchivo("transferencia_pk.txt");

    public static final Path SUCURSAL = prepararArchivo("sucursal.txt");
    public static final Path CAJERO = prepararArchivo("cajero.txt");
    public static final Path TRANSACCION_CAJERO = prepararArchivo("transaccion_cajero.txt");

    public static final Path PRODUCTO_FINANCIERO = prepararArchivo("producto_financiero.txt");

    public static final Path CLIENTE_PRODUCTO_SUCURSAL = prepararArchivo("cliente_producto_sucursal.txt");
    public static final Path CLIENTE_PRODUCTO_SUCURSAL_PK = prepararArchivo("cliente_producto_sucursal_pk.txt");
}
