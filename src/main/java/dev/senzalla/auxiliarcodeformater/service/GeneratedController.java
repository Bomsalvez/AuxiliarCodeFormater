package dev.senzalla.auxiliarcodeformater.service;


import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static dev.senzalla.auxiliarcodeformater.model.Directory.EXTENSION;
import static dev.senzalla.auxiliarcodeformater.model.Directory.modifyModelName;


class GeneratedController {
    private final String packageController = "controller";
    private final Path pathService;

    public GeneratedController(Path pathRoot, Path pathAbsoluteRoot) throws IOException {
        pathService = Path.of(pathRoot + ".service");
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
        final Path pathController = Directory.createDirectory(packageController);
        String archive = modifyModelName(f, "Controller");
        String entity = Directory.getModelName(f);
        Path newPatch = Path.of(pathController + "\\" + archive + EXTENSION);

        if (!Files.exists(newPatch)) {
            var listArgs = List.of(
                    String.format("package %s.%s;", Directory.getPathRoot(), packageController),
                    "\n\n",
                    String.format("import %s.%s.%sService;", pathService, entity, entity),
                    "\n",
                    "import lombok.AllArgsConstructor;",
                    "\n",
                    "import org.springframework.web.bind.annotation.RequestMapping;",
                    "\n",
                    "import org.springframework.web.bind.annotation.RestController;",
                    "\n",
                    "import org.springframework.beans.factory.annotation.Autowired;",
                    "\n\n",
                    "@RestController",
                    "\n",
                    String.format("@RequestMapping(\"/%s\")", entity.toLowerCase()),
                    "\n",
                    "@AllArgsConstructor(onConstructor = @__(@Autowired))",
                    "\n",
                    String.format("public class %sController {", entity),
                    "\n",
                    String.format(" private final %sService service;", entity),
                    "\n",
                    "}"
            );
            new CreateFile(newPatch, listArgs);
        }
    }
}