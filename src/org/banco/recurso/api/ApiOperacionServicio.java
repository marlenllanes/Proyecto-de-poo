package org.banco.recurso.api;

import java.util.List;

public interface ApiOperacionServicio<C, R, U, ID> {

    R insertInto(C creacionDTO);

    R updateSet(ID id, U actualizarDto);

    Boolean deleteFrom(ID codigo);

    List<R> selectFrom();

    R selectOne(ID codigo);

    int countRows();

    int lastSerial();
}
