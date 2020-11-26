package com.unmsm.operating.system.simulator.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.unmsm.operating.system.simulator.model.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController {

    private Properties p = new Properties();
    private ConfigController configController = new ConfigController();
    private FileController fileController;
    private String path = "./src/Properties/";
    
    public String[] getListUser() {
        String[] users = {};
        try {
            p.load(new FileReader(path.concat("admin/").concat("list-users.properties")));
            users = p.getProperty("users").split(",");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en lectura en lista de usuarios" + e.getMessage());
        }
        return users;
    }

    public void addUser(User user) {

        String users = "";
        try {

            if (!isNewUser(user.getUsername())) {
                JOptionPane.showMessageDialog(null, "Usuario ya registrado");
                return;
            }
            configController.createDirectory(user);
            configController.createFileConfig(user);
            
            // Add user into list-user.properties
            p.load(new FileReader(path.concat("admin/").concat("list-users.properties")));
            users = p.getProperty("users").concat(",").concat(user.getUsername());
            p.setProperty("users", users);
            p.store(new FileWriter(path.concat("admin/").concat("list-users.properties")), "New user add");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al añadir usuario" + e.getMessage());
        }
    }

    public void deleteUser(User user) {

        List<String> userList = new ArrayList<>();
        String users;

        try {
            if (isNewUser(user.getUsername())) {
                JOptionPane.showMessageDialog(null, "Usuario no registrado");
                return;
            }

            // Get list-user.properties
            p.load(new FileReader(path.concat("admin/").concat("list-users.properties")));
            userList.addAll(Arrays.asList(p.getProperty("users").split(",")));

            // Delete user from list-user.properties
            userList.remove(user.getUsername());

            // Delete user-config.properties
             configController.deleteFileConfig(user);
             configController.deleteFilePath(user);

            // Convert userList: List<Stirng> to users: String
            users = userList.stream().map(Object::toString).collect(Collectors.joining(","));

            // Update user properties
            p.setProperty("users", users);
            p.store(new FileWriter(path.concat("admin/").concat("list-users.properties")), "User deleted");

            //Delete files and directory
            fileController = new FileController(user);
            fileController.deleteDirectory(user.getUsername());
//            File f = new File(p.getProperty(user.getUsername()));
//            fileController.deleteDirectory(f);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al añadir usuario" + e.getMessage());
        }
    }

    public boolean isNewUser(String nameUser) {
        boolean resp = true;
        String[] listUser = getListUser();
        for (String u : listUser) {
            if (nameUser.equals(u)) {
                resp = false;
                return resp;
            }
        }
        return resp;
    }
    
}
