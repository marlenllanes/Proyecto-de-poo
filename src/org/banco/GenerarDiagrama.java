package org.banco;

import com.cleandev.umlgen.UmlGen;
import com.cleandev.umlgen.config.UmlConfig;
import java.io.IOException;

public class GenerarDiagrama {

    public static void main(String[] args) {
        try {
            // OPCIÓN 1: Rápida con defaults
             UmlGen.generateQuick("src", "diagrama-general.puml");

            // OPCIÓN 2: Personalizada
           // UmlConfig configuracion = UmlConfig.builder()
                    //.includeGettersSetters(true)
                   // .showVisibility(true)
                  //  .colorizeInterfaces(true)
                  //  .externalStereotype("<<external>>")
                   // .addExcludedClass(GenerarDiagrama.class.getSimpleName())
                  //  .addExcludedClass(App.class.getSimpleName())
                  //  .addExcludedClass(Inicializador.class.getSimpleName())
                  //  .addExcludedType("Optional")
                  //  .addExcludedPackage("org.banco.controlador")
                  //  .addExcludedPackage("org.banco.dto")
                  //  .addExcludedPackage("org.banco.entidad")
                  //  .addExcludedPackage("org.banco.mapeador")
                  //  .addExcludedPackage("org.banco.recurso")
                   // .addExcludedPackage("org.banco.repositorio")
                 //   .addExcludedPackage("org.banco.servicio")
                  //  .addExcludedPackage("org.banco.vista")
                 //   .build();

            //UmlGen generator = new UmlGen(configuracion);
            //generator.generateFromDirectory(
                 //   "src/org/banco",
                 //   "src/diagrama.puml"
           // );

            System.out.println("Diagrama generado exitosamente");
        } catch (IOException ex) {
            System.getLogger(GenerarDiagrama.class.getName())
                    .log(System.Logger.Level.ERROR, "Failed to generate UML diagram", ex);
        }
    }
}
