
package com.unmsm.operating.system.simulator.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.User;

public class LoginController {
    private Properties p = new Properties();
    private User user = new User();
    private String path = "./src/Properties/";
    
    public User getUser()  {
 
        try {
            p.load(new FileReader(path.concat("admin-config.properties")));
            user.setUsername(p.getProperty("username"));
            user.setPassword(p.getProperty("password"));
            user.setRol(p.getProperty("rol"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura de usuarios" + ex.getMessage());
        }
        
        return user;
    }
}
