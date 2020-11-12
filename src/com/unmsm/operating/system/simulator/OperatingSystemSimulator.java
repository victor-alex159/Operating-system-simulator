
package com.unmsm.operating.system.simulator;

import com.unmsm.operating.system.simulator.controllers.ConfigController;
import com.unmsm.operating.system.simulator.controllers.LoginController;
import com.unmsm.operating.system.simulator.controllers.AdminController;
import com.unmsm.operating.system.simulator.controllers.UserController;
import com.unmsm.operating.system.simulator.jframes.LoginFrame;
import com.unmsm.operating.system.simulator.model.User;

public class OperatingSystemSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        LoginController loginController = new LoginController();
//        AdminController adminController = new AdminController();
//        User user = loginController.getUser();
        
//        User user10 = new User();
//        user10.setRol("user10");
//        user10.setUsername("user10");
//        user10.setPassword("121");
          
        /*
         * @adminController
         * adiciona un nuevo usuario
         * crea su espacio de trabajo directorio 
         * crea archivo configuracion con sus credenciales
        */ 
//        adminController.addUser(user10);
        
        /* 
         * @UserController
         * controlador para obtener las credenciales
        */
        //UserController userController = new UserController("user9");
        //System.out.println("Username: " + userController.getUsername());
        //System.out.println("Pasword: " + userController.getPassword());
        //System.out.println("Rol: " + userController.getRol()); // por ahora solo 2 roles: user y admin(solo uno)
        
         LoginFrame loginFrame = new LoginFrame();
         loginFrame.setVisible(true);
    }
    
}
