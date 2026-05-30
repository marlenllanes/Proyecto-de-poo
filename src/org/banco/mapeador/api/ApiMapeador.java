package org.banco.mapeador.api;

import java.util.List;

public interface ApiMapeador<E, C, R, U> {

    R toDto(E entidad);

    E toEntityFromCrear(C dto);

    E toEntityFromActualizar(U dto);

    List<R> toDtoList(List<E> entidades);
}
