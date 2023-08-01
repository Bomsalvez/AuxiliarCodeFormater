package dev.senzalla.auxiliarcodeformater.service;


import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static dev.senzalla.auxiliarcodeformater.model.Directory.*;


class GeneratedRepository {
    private final String packageRepository = "repository";
    private final Path pathRepository = Directory.createDirectory(packageRepository);

    public GeneratedRepository(Path pathAbsoluteRoot) throws IOException {

        final Path pathAbsoluteModel = Path.of(pathAbsoluteRoot + "\\model");
        checkIsDirectory(Objects.requireNonNull(pathAbsoluteModel.toFile().listFiles()));
    }

    private void checkIsDirectory(File[] files) throws IOException {
        for (File f : files) {
            if (Files.isDirectory(f.toPath())) {
                checkIsDirectory(f.listFiles());
            } else {
                fillClass(f);
            }
        }
    }

    private void fillClass(File f) throws IOException {
        String repositoryClass = modifyModelName(f, "Repository");
        var pathModel = Directory.getPathRoot(f);
        String entity = getModelName(f);
        Path newPatch = Path.of(pathRepository + "\\" + repositoryClass + EXTENSION);

        if (!Files.exists(newPatch)) {
            var listArgs = List.of(
                    String.format("package %s.%s;", Directory.getPathRoot(), packageRepository),
                    "\n\n",
                    (String.format("import %s;", pathModel)),
                    "\n",
                    "import org.springframework.data.jpa.repository.JpaRepository;",
                    "\n",
                    "import org.springframework.stereotype.Repository;",
                    "\n\n",
                    "@Repository",
                    "\n",
                    String.format("public interface %s extends JpaRepository<%s, Long> {", repositoryClass, entity),
                    "\n",
                    "}"
            );

            new CreateFile(newPatch, listArgs);
        }
    }
}
