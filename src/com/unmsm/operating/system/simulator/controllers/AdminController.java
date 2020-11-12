
package com.unmsm.operating.system.simulator.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.User;

public class AdminController {
    private Properties p = new Properties();
    private ConfigController configController = new ConfigController();
    
    public String[] getListUser() {
        String[] users = {};
        try {
            p.load(new FileReader("./src/Properties/list-users.properties"));
            users = p.getProperty("users").split(",");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en lectura" + ex.getMessage());
        }
        return users;
    }
    
    private boolean isNewUser(String nameUser) {
        boolean resp = true;
        String[] listUser = getListUser();
        for (String u: listUser) {
            if(nameUser.equals(u)) {
                resp = false;
            }
        }
        return resp;
    }
    
    public void addUser(User user) {
        
        String users = "";
        try {
            if(!isNewUser(user.getUsername())) {
                JOptionPane.showMessageDialog(null, "Usuario ya registrado");
                return;
            }
            
            p.load(new FileReader("./src/Properties/list-users.properties"));
            users = p.getProperty("users").toString().concat(",").concat(user.getUsername());
            p.setProperty("users", users);
            p.store(new FileWriter("./src/Properties/list-users.properties"),"New user add");
            
            configController.createDirectory(user);
            configController.createFileConfig(user);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al añadir usuario" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al añadir usuario" + ex.getMessage());
        }
    }
}
