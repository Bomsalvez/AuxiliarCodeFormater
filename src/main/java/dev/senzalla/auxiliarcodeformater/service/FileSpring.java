package dev.senzalla.auxiliarcodeformater.service;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import static dev.senzalla.auxiliarcodeformater.model.Directory.*;

public class FileSpring {
    public void creteFilesSpring(File file, Set<JCheckBox> boxes) {
        try {
            setPathRoot(file.toPath());
            Path pathRoot = getPathRoot();
            Path pathAbsoluteRoot = getPathAbsoluteRoot();
            for (JCheckBox box : boxes) {
                switch (box.getText()) {
                    case "Repository" -> {
                        if (box.isSelected()) {
                            new GeneratedRepository(pathAbsoluteRoot);
                        }
                    }
                    case "Service" -> {
                        if (box.isSelected()) {
                            new GeneratedService(pathRoot, pathAbsoluteRoot);
                        }
                    }
                    case "Controller" -> {
                        if (box.isSelected()) {
                            new GeneratedController(pathRoot, pathAbsoluteRoot);
                        }
                    }
                    case "Model" -> {
                        if (box.isSelected()) {
                            new GeneratedModel(pathAbsoluteRoot);
                        }
                    }
                    case "Settings" -> {
                        if (box.isSelected()) {
                            new GeneratedSetting();
                        }
                    }
                    case "Properties" -> {
                        if (box.isSelected()) {
                            new GeneratedProperty(file.toPath());
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
