package dev.senzalla.auxiliarcodeformater.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class CreateFile {
    private final FileOutputStream output;

    public CreateFile(Path newPatch, List<String> listArgs) throws IOException {
            Files.createFile(newPatch);
            output = new FileOutputStream(newPatch.toFile());
            for (String arg : listArgs) {
                writeLine(arg);
            }
            output.close();
    }

    private void writeLine(String s) throws IOException {
        output.write(s.getBytes());
    }
}
