package com.unmsm.operating.system.simulator.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.User;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigController {

    Properties p = new Properties();
    private String path = "./src/Properties/";

    public void createDirectory(User user) {
        String ruta = path.concat(user.getUsername());
        // Properties fileproperties = new Properties();
        File directory = new File(ruta);

        try {
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    createPathDirectory(user);
                    p.load(new FileReader(path.concat(user.getUsername().concat("/").concat(user.getUsername().concat("-path.properties")))));
                    p.setProperty(user.getUsername(), ruta);
                    p.store(new FileWriter(path.concat(user.getUsername().concat("/").concat(user.getUsername().concat("-path.properties")))), "File directory created");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo completar la operación");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el directorio");
        }
    }

    public void createPathDirectory(User user) {
        File fileCreated = null;
        String nameFile = user.getUsername().concat("-path.properties");
        String ruta = path.concat(user.getUsername());

        try {
            if (user.getUsername() != null) {
                fileCreated = new File(ruta, nameFile);
            }
            if (!fileCreated.exists()) {
                if (fileCreated.createNewFile()) {
                    p.load(new FileReader(path.concat(user.getUsername().concat("/").concat(user.getUsername().concat("-path.properties")))));
                    p.setProperty(nameFile, ruta.concat("/").concat(user.getUsername()).concat("-path.properties"));
                    p.store(new FileWriter(path.concat(user.getUsername().concat("/").concat(user.getUsername().concat("-path.properties")))), "File directory created");
                } else {
                    JOptionPane.showMessageDialog(null, "Error de acceso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de acceso");
        }
    }

    public void createFileConfig(User user) {

        File fileCreated = null;
        String nameFile = user.getUsername().concat("-config.properties");
        String ruta = path.concat(user.getUsername().concat("/").concat(nameFile));
        Properties pconfig = new Properties();

        try {
            if (user.getUsername() != null) {
                p.load(new FileReader(p.getProperty(user.getUsername()).concat("/").concat(user.getUsername().concat("-path.properties"))));
                p.setProperty(nameFile, ruta);
                p.store(new FileWriter(p.getProperty(user.getUsername()).concat("/").concat(user.getUsername()).concat("-path.properties")), "File path created");
                fileCreated = new File(p.getProperty(user.getUsername()), nameFile);
            }
            if (!fileCreated.exists()) {
                if (fileCreated.createNewFile()) {
                    pconfig.load(new FileReader(p.getProperty(nameFile)));
                    pconfig.setProperty("rol", user.getRol());
                    pconfig.setProperty("username", user.getUsername());
                    pconfig.setProperty("password", user.getPassword());
                    pconfig.store(new FileWriter(p.getProperty(nameFile)), "File config created");
                } else {
                    JOptionPane.showMessageDialog(null, "Error de acceso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error no se pudo crear el archivo config");
        }
    }

    public void deleteFileConfig(User user) {
        try {
            p.load(new FileReader(path.concat(user.getUsername()).concat("/").concat(user.getUsername()).concat("-path.properties")));
            File fileDeleted = new File(p.getProperty(user.getUsername().concat("-config.properties")));

            if (fileDeleted.exists()) {
                try {
                    if (fileDeleted.delete()) {
                        JOptionPane.showMessageDialog(null, "Se eliminó");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de eliminación");
                    }
                } catch (Error e) {
                    JOptionPane.showMessageDialog(null, "Error en lectura archivo config" + e.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en lectura archivo config" + e.getMessage());
        }
    }
    
    public void deleteFilePath(User user) {
        try {
            File fileDeleted = new File(path.concat(user.getUsername()).concat("/").concat(user.getUsername()).concat("-path.properties"));

            if (fileDeleted.exists()) {
                try {
                    if (fileDeleted.delete()) {
                        JOptionPane.showMessageDialog(null, "Se eliminó");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de eliminación");
                    }
                } catch (Error e) {
                    JOptionPane.showMessageDialog(null, "Error en lectura archivo path" + e.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en lectura archivo path" + e.getMessage());
        }
    }
}
