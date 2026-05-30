package org.banco.mapeador.api;

public interface ApiMapeadorCompuesto<E, C, U> {

    E toEntityFromCrear(C dto);

    E toEntityFromActualizar(U dto);

    C toCrearDto(E entidad);

    U toActualizarDto(E entidad);
}
