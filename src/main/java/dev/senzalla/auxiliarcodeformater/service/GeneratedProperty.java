package dev.senzalla.auxiliarcodeformater.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


class GeneratedProperty {
    public GeneratedProperty(Path pathRoot) throws IOException {
        String pathProperty = "\\src\\main\\resources";
        Path pathOldProperty = Path.of((pathRoot + pathProperty + "\\application.properties"));
        File file = new File(String.valueOf(pathOldProperty));
        file.delete();

        Path newPatch = Path.of(pathRoot + pathProperty + "\\application.yml");
        if (!Files.exists(newPatch)) {
            var listArgs = List.of(
                    "# App \n" +
                            "server:\n" +
                            "  servlet:\n" +
                            "    application-display-name: \n" +
                            "    context-path: \n\n" +
                            "# Jackson\n" +
                            "spring:\n" +
                            "  jackson:\n" +
                            "    default-property-inclusion: non_null \n\n" +
                            "# JPA\n" +
                            "  jpa:\n" +
                            "    hibernate:\n" +
                            "      naming:\n" +
                            "        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl"
            );
            new CreateFile(newPatch, listArgs);
        }
    }

}
