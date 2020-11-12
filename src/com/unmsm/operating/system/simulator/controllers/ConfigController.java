
package com.unmsm.operating.system.simulator.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.Config;
import com.unmsm.operating.system.simulator.model.User;

public class ConfigController {
    Properties p = new Properties();
    private Config config = new Config();
    
    public void createDirectory(User user) {
        config.setLocationDirectory("./src/Properties/".concat(user.getUsername()));
        File directory = new File(config.getLocationDirectory());
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                // JOptionPane.showMessageDialog(null, "Se añadió");
            } else {
                JOptionPane.showMessageDialog(null, "No se puedo completar operación");
            }
        }
    }
    
    /**
     * 
     * @param file require name plus extension: "txt"
     */
    
    public void createFileConfig(User user) {
        
        File fileCreated = null;
        
        if(user.getUsername() != null) {
           String nameFile = user.getUsername().concat("-config").concat(".").concat("properties");
           config.setLocationConfig(config.getLocationDirectory().concat("/").concat(nameFile));
           fileCreated = new File(config.getLocationDirectory(), nameFile);
        }
        
        if (!fileCreated.exists()) {
            try {
                if (fileCreated.createNewFile()) {
                    
                    p.load(new FileReader(config.getLocationConfig()));
                    p.setProperty("rol", user.getRol());
                    p.setProperty("username", user.getUsername());
                    p.setProperty("password", user.getPassword());
                    p.store(new FileWriter(config.getLocationConfig()),"File config created");
                    // JOptionPane.showMessageDialog(null, "Se añadió");
                } else {
                    JOptionPane.showMessageDialog(null, "Error de acceso");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en lectura archivo config" + ex.getMessage());
            }
        }
    }
}
