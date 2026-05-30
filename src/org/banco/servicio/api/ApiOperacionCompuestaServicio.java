package org.banco.servicio.api;

import java.util.List;

public interface ApiOperacionCompuestaServicio<
        C, // DTO para crear
        H, // DTO hidratado (read)
        U, // DTO para actualizar
        ID // DTO identificador (PK compuesta)
        > {

    C insertInto(C creacionDTO);

    U updateSet(ID id, U actualizarDto);

    Boolean deleteFrom(ID codigo);

    List<H> selectFrom();

    H selectOne(ID codigo);

    int countRows();
}
