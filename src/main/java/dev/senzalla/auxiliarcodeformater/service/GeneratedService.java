package dev.senzalla.auxiliarcodeformater.service;

import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static dev.senzalla.auxiliarcodeformater.model.Directory.*;

class GeneratedService {
    private final String packageService = "service";
    private final Path pathService = Directory.createDirectory(packageService);
    private final Path pathRepository;


    public GeneratedService(Path pathRoot, Path pathAbsoluteRoot) throws IOException {
        pathRepository = Path.of(pathRoot + ".repository");
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
        String entity = getModelName(f);
        Path subPackageService = Directory.createDirectory(pathService, entity);
        String serviceClass = modifyModelName(f, "Service");
        Path newPatch = Path.of(subPackageService + "\\" + serviceClass + EXTENSION);


        if (!Files.exists(newPatch)) {
            var listArgs = List.of(
                    String.format("package %s.%s.%s;", Directory.getPathRoot(), packageService, entity),
                    "\n\n",
                    String.format("import %s.%sRepository;", pathRepository, entity),
                    "\n",
                    "import lombok.AllArgsConstructor;",
                    "\n",
                    "import org.springframework.beans.factory.annotation.Autowired;",
                    "\n",
                    "import org.springframework.stereotype.Service;",
                    "\n\n",
                    "@Service",
                    "@AllArgsConstructor(onConstructor = @__(@Autowired))",
                    "\n",
                    String.format("public class %sService {", entity),
                    "\n",
                    String.format(" private final %sRepository repository;", entity),
                    "\n",
                    "}"
            );
            new CreateFile(newPatch, listArgs);
        }
    }
}
