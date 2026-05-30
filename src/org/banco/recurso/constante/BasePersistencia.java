package org.banco.recurso.constante;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BasePersistencia {

    protected static final Path DIR_PROYECTO = Paths.get(System.getProperty("user.dir"));
    protected static final Path CARPETA_PERSISTENCIA = DIR_PROYECTO.resolve("persistenciasBanco");

    protected static Path prepararArchivo(String nombre) {
        return CARPETA_PERSISTENCIA.resolve(nombre);
    }
}
