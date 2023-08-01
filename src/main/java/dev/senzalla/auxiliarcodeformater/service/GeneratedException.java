package dev.senzalla.auxiliarcodeformater.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GeneratedException {
    public GeneratedException(Path pathSettings) throws IOException {
        final Path pathException = Path.of(pathSettings + "\\exception");
        if (!Files.exists(pathException)) {
            Files.createDirectory(pathException);
        }
        new GeneratedErrorDto(pathException);
        new GeneratedResourceExceptionHandler(pathException);
    }
}
