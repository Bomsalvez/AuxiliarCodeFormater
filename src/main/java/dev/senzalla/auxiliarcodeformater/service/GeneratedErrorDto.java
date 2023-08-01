package dev.senzalla.auxiliarcodeformater.service;


import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static dev.senzalla.auxiliarcodeformater.model.Directory.EXTENSION;


class GeneratedErrorDto {
    public GeneratedErrorDto(Path pathException) throws IOException {
        Path newPatch = Path.of(pathException + "\\" + "ErrorDto" + EXTENSION);

        if (!Files.exists(newPatch)) {
            var listArgs = List.of(
                    String.format("package %s;", Directory.getPathRoot(pathException.toFile())),
                    "\n\n",
                    "import lombok.Getter;",
                    "\n\n",
                    "@Getter",
                    "\n",
                    "public class ErrorDto {",
                    " private final String error;",
                    "\n",
                    " private String property;",
                    "\n\n",
                    " public ErrorDto(String error) {",
                    "\n",
                    "  this.error = error;",
                    "\n",
                    " }",
                    "\n\n",
                    " public ErrorDto(String error, String property) {",
                    "\n",
                    "  this.error = error;",
                    "\n",
                    "  this.property = property;",
                    "\n",
                    " }",
                    "\n",
                    "}"
            );
            new CreateFile(newPatch, listArgs);
        }
    }
}
