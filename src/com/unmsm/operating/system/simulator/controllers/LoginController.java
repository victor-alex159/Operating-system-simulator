
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
    
    public User getUser()  {
 
        try {
            p.load(new FileReader("./src/Properties/admin-config.properties"));
            user.setUsername(p.getProperty("username"));
            user.setPassword(p.getProperty("password"));
            user.setRol(p.getProperty("rol"));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura de usuarios" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura de usuarios" + ex.getMessage());
        }
        
        return user;
    }
}
