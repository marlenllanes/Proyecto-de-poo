package org.banco.recurso.utilidad;

import java.util.regex.Pattern;

public class ValidadorCorreo {

    private static final String PATRON_CORREO = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern PATRON = Pattern.compile(PATRON_CORREO);

    public static boolean validarCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            return false;
        }
        return PATRON.matcher(correo).matches();
    }
}
