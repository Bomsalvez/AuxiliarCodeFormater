package dev.senzalla.auxiliarcodeformater.service;

import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static dev.senzalla.auxiliarcodeformater.model.Directory.EXTENSION;

class GeneratedModel {
    public GeneratedModel( Path pathAbsoluteRoot) throws IOException {
        final Path pathModel = Path.of(pathAbsoluteRoot + "\\model");
        for (File file : Objects.requireNonNull(pathModel.toFile().listFiles())) {
            if (file.getName().contains(EXTENSION)) {
                String modelPackage = createPackage(file);
                Path newPackageModel = Directory.createDirectory(pathModel, modelPackage);
                Path newPackageEntity = Directory.createDirectory(newPackageModel, "entity");
                Directory.createDirectory(newPackageModel, "mapper");
                Directory.createDirectory(newPackageModel, "module");

                Files.move(file.toPath(), Path.of(newPackageEntity + "\\" + file.getName()));
            }
        }
    }

    private String createPackage(File file) {
        String entity = Directory.getModelName(file);
        char lowChar = entity.toLowerCase().charAt(0);
        return entity.replace(entity.charAt(0), lowChar);
    }
}
