package dev.senzalla.auxiliarcodeformater.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Directory {
    private static Path pathRoot;
    public final static String EXTENSION = ".java";

    public static Path getPathRoot() {
        int oldPackage = pathRoot.toString().lastIndexOf("\\java");
        return Path.of(pathRoot.toString().substring(oldPackage + 6).replace("\\", "."));
    }

    public static Path getPathRoot(File file) {
        int oldPackage = file.toString().lastIndexOf("\\java");
        return Path.of(file.toString()
                .substring(oldPackage + 6)
                .replace("\\", ".")
                .replace(EXTENSION, ""));
    }

    public static Path getPathAbsoluteRoot() {
        return pathRoot;
    }

    public static void setPathRoot(Path path) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path p : stream) {
                if (Files.isDirectory(p)) {
                    try (DirectoryStream<Path> subStream = Files.newDirectoryStream(p, "model")) {
                        if (subStream.iterator().hasNext()) {
                            pathRoot = p.toAbsolutePath();
                        }
                    }
                    setPathRoot(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path createDirectory(String directory) throws IOException {
        Path newPatch = Path.of(pathRoot.toString(), directory);
        return Files.createDirectories(newPatch);
    }

    public static Path createDirectory(Path path, String directory) throws IOException {
        Path newPatch = Path.of(path.toString(), directory);
        return Files.createDirectories(newPatch);
    }

    public static String modifyModelName(File file, String replacement) {
        return file.getName().replace(EXTENSION, replacement);

    }

    public static String getModelName(File file) {
        return modifyModelName(file, "");
    }

    public static String newPackeage(File file) {
        return file.getName().replace(EXTENSION, "");
    }
}
