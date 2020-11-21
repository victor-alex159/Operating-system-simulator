
package com.unmsm.operating.system.simulator;

import com.unmsm.operating.system.simulator.controllers.ConfigController;
import com.unmsm.operating.system.simulator.controllers.LoginController;
import com.unmsm.operating.system.simulator.controllers.AdminController;
import com.unmsm.operating.system.simulator.controllers.FileController;
import com.unmsm.operating.system.simulator.controllers.UserController;
import com.unmsm.operating.system.simulator.jframes.LoginFrame;
import com.unmsm.operating.system.simulator.model.User;
import java.io.File;

public class OperatingSystemSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AdminController admin = new AdminController();        
        
        User user1 = new User();
        user1.setRol("user");
        user1.setUsername("user1");
        user1.setPassword("123");
        
        User user2 = new User();
        user2.setRol("user");
        user2.setUsername("user2");
        user2.setPassword("123");
        
        User user3 = new User();
        user3.setRol("user");
        user3.setUsername("user3");
        user3.setPassword("333");
        
        // Create a user
        // admin.addUser(user1);
        
        //List users: admin.getListUser()
        //for(String u: admin.getListUser()) {
        //    System.out.println(u);
        //}
        
        // Crear directorio (name, nameFatherDirectory)
        FileController fileController = new FileController(user1);
        //fileController.createDirectory("prueba", "user1");
        
        // Eliminar un directorio
        fileController.deleteDirectory("user2");
        
        // Crear archivo
        //fileController.createFile("miarchivo", "pdf", "user1");
        
        // Eliminar un archivo
        //fileController.deleteFile("miarchivo.pdf");
        
        // Obtener la ruta y el nombre de un archivo
        //System.out.println(fileController.getDirectory("miarchivo.pdf").getName());
        //System.out.println(fileController.getDirectory("miarchivo.pdf").getPath());
        
        // Obtener la ruta y nombre de un directorio
        //System.out.println(fileController.getDirectory("prueba").getName());
        //System.out.println(fileController.getDirectory("prueba").getPath());
        
        
    }
    
}
