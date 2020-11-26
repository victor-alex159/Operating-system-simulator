
package com.unmsm.operating.system.simulator.controllers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.User;

public class UserController {
    
    Properties p = new Properties();
    private User user = new User();
    private String path = "./src/Properties/";
    
    public UserController(String name) {
        this.user.setUsername(name);
    }
    
    public String getUsername() {
        String username = "";
        try {
            p.load(new FileReader(path.concat(user.getUsername()).concat("/").concat(user.getUsername()).concat("-config.properties")));
            username = p.getProperty("username");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura" + ex.getMessage());
        }
        return username;
    }
    
    public String getPassword() {
        String pass = "";
        try {
            p.load(new FileReader(path.concat(user.getUsername()).concat("/").concat(user.getUsername()).concat("-config.properties")));
            pass = p.getProperty("password");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura" + ex.getMessage());
        }
        return pass;
    }
    
    public String getRol() {
        String rol = "";
        try {
            p.load(new FileReader(path.concat(user.getUsername()).concat("/").concat(user.getUsername()).concat("-config.properties")));
            rol = p.getProperty("rol");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura de credenciales" + ex.getMessage());
        }
        return rol;
    }
    
}
