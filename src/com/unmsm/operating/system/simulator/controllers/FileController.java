package com.unmsm.operating.system.simulator.controllers;

import com.unmsm.operating.system.simulator.model.Directory;
import com.unmsm.operating.system.simulator.model.Archivo;
import com.unmsm.operating.system.simulator.model.User;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FileController {

    private Properties p = new Properties();
    private Directory d;
    private Archivo f;
    private User user;
    private String path = "./src/Properties/";
    private String fileRutas;

    public FileController(User user) {
        try {
            this.user = user;
            fileRutas = path.concat(user.getUsername()).concat("/").concat(user.getUsername()).concat("-path.properties");
            p.load(new FileReader(fileRutas));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el directorio");
        }
    }

    public FileController() {

    }

    public void createDirectory(String name, String nameDirectoryFather) {
        File fileCreated = null;
        String path;
        try {
            //path = p.getProperty(nameDirectoryFather).concat("/").concat(name);
            path = ((nameDirectoryFather).concat("/").concat(name));
            System.out.println(path);
            fileCreated = new File(path);
            if (!fileCreated.exists()) {
                try {
                    if (fileCreated.mkdir()) {
                        //p.setProperty(name, path);
                        //p.store(new FileWriter(fileRutas), "");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo crear el directorio");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el directorio");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontró la ruta especificada" + e.getMessage());
        }
    }

    public void createFile(String name, String extension, String nameDirectoryFather) {
        File fileCreated = null;
        String path;
        try {
            name = name.concat(".").concat(extension);
            path = p.getProperty(nameDirectoryFather).concat("/").concat(name);

            fileCreated = new File(p.getProperty(nameDirectoryFather), name);
            if (!fileCreated.exists()) {
                fileCreated.createNewFile();
                p.setProperty(name, path);
                p.store(new FileWriter(fileRutas), "");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontró la ruta especificada" + e.getMessage());
        }
    }

    public Directory getDirectory(String name) {
        Directory d = new Directory();
        d.setName(name);
        d.setPath(p.getProperty(name));
        return d;
    }
    
    public Archivo getFile(String name) {
        Archivo d = new Archivo();
        d.setName(name);
        d.setPath(p.getProperty(name));
        return d;
    }

    public void deleteDirectory(String directory) {
        File file;
        try {
            file = new File(p.getProperty(directory));
            File fileDeleted = new File(file.getAbsolutePath());
            deleteSubFile(fileDeleted);
            p.remove(directory);
            p.store(new FileWriter(fileRutas), null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error eliminando, la carpeta no existe");
        }
    }

    private void deleteSubFile(File directory) {
        File[] files;
        try {
            files = directory.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteSubFile(f);
                }
                f.delete();
                p.remove(f.getName());
                p.store(new FileWriter(fileRutas), null);
            }
            directory.delete();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error eliminando, las subcarpetas no existen");
        }
    }

    public void deleteFile(String name) {
        File file;
        File fileDeleted;
        try {
            file = new File(p.getProperty(name));
            fileDeleted = new File(file.getAbsolutePath());
            fileDeleted.delete();
            p.remove(name);
            p.store(new FileWriter(fileRutas), null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error eliminando, el archivo no existe");
        }
    }
    
    public void moveFileToRecicle(String nameFile) {
        boolean success = false;
        if(nameFile.contains(".txt")) {
            nameFile = nameFile.replaceAll(".txt", "");
        }
        String pathFile = ("./docFiles/".concat(nameFile).concat(".txt"));
        String pathRecicle = "./recicle";
        File file = new File(pathFile);
        File directoryRecicle = new File(pathRecicle);
        
        success = file.renameTo(new File(directoryRecicle, file.getName()));
        if(!success) {
            JOptionPane.showMessageDialog(null, "Error al mover a directorio de Reciclaje");
        }
        
    }
}
