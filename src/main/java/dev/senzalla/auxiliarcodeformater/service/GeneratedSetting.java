package dev.senzalla.auxiliarcodeformater.service;


import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GeneratedSetting {
    public GeneratedSetting() throws IOException {
        final Path pathSettings = Path.of(Directory.getPathAbsoluteRoot() + "\\setting");
        if (!Files.exists(pathSettings)) {
            Files.createDirectory(pathSettings);
        }
        new GeneratedException(pathSettings);
    }
}
