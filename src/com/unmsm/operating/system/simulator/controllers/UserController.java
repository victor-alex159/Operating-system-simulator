
package com.unmsm.operating.system.simulator.controllers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.Config;

public class UserController {
    
    Properties p = new Properties();
    private Config config = new Config();
    private String username;
    
    public UserController(String username) {
        this.username = username;
        config.setLocationConfig("./src/Properties/".concat(username).concat("-config.properties"));
    }
    
    public String getUsername() {
        String username = "";
        try {
            p.load(new FileReader(config.getLocationConfig()));
            username = p.getProperty("username");
        } catch (IOException ex) {
            // JOptionPane.showMessageDialog(null, "Error en lectura" + ex.getMessage());
        }
        return username;
    }
    
    public String getPassword() {
        String pass = "";
        try {
            p.load(new FileReader(config.getLocationConfig()));
            pass = p.getProperty("password");
        } catch (IOException ex) {
            // JOptionPane.showMessageDialog(null, "Error en lectura" + ex.getMessage());
        }
        return pass;
    }
    
    public String getRol() {
        String rol = "";
        try {
            p.load(new FileReader(config.getLocationConfig()));
            rol = p.getProperty("rol");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura de credenciales" + ex.getMessage());
        }
        return rol;
    }
    
}
