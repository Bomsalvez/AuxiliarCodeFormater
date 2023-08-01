package dev.senzalla.auxiliarcodeformater.service;

import javax.swing.*;
import java.io.File;
import java.util.Set;

public class FileNode {
    public void createFilesNode(File file, Set<JCheckBox> boxes) {
        System.out.println(file.getAbsolutePath());
    }
}
