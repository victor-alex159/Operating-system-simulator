/*
 * To change this lice            @Override
            public void paintIcon(Component cmpnt, Graphics grphcs, int i, int i1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getIconWidth() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getIconHeight() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }se header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.operating.system.simulator.jframes;

//import com.sun.glass.events.KeyEvent;
import com.unmsm.operating.system.simulator.controllers.AdminController;
import com.unmsm.operating.system.simulator.controllers.LoginController;
import com.unmsm.operating.system.simulator.controllers.UserController;
import com.unmsm.operating.system.simulator.model.User;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author group so
 * @version 1.0
 * @since 2020-11-05
 */
public class LoginFrame extends javax.swing.JFrame {
    
    User user = new User();

    DesktopFrame desktopFrame;
    AdminController adminController = new AdminController();
    LoginController loginController = new LoginController();
    UserController userController;
    /**
     * Creates new form LoginFrame
     * setExtendedState() maximize windows
     */
    
    public LoginFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // to center components
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        returnJButton.setVisible(false);
        usersJComboBox.setModel(new DefaultComboBoxModel<>(adminController.getListUser()));
        userJTextField.setText(String.valueOf(usersJComboBox.getSelectedItem()));
        this.setTitle(String.valueOf(usersJComboBox.getSelectedItem()));
    }

    public void login() {
        String username = userJTextField.getText();
        String password = String.valueOf(passwordJPasswordField.getPassword());
        this.user.setUsername(username);
        this.user.setPassword(password);
        desktopFrame = new DesktopFrame(user);
        userController = new UserController(username);
        if( username.equals(userController.getUsername()) && password.equals(userController.getPassword())) {
            desktopFrame.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales erróneos");
        }
    }
    
    public void selectListUser() {
        usersJComboBox.setVisible(false);
        userJTextField.setText(String.valueOf(usersJComboBox.getSelectedItem()));
        tittleJLabel.setText(String.valueOf(usersJComboBox.getSelectedItem()));
        returnJButton.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        containerJPanel = new javax.swing.JPanel();
        userJLabel = new javax.swing.JLabel();
        passwordJLabel = new javax.swing.JLabel();
        userJTextField = new javax.swing.JTextField();
        loginButton = new java.awt.Button();
        returnJButton = new javax.swing.JButton();
        usersJComboBox = new javax.swing.JComboBox<>();
        passwordJPasswordField = new javax.swing.JPasswordField();
        iconJLabel = new javax.swing.JLabel();
        tittleJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        containerJPanel.setBackground(new java.awt.Color(30, 30, 30));
        containerJPanel.setLayout(new java.awt.GridBagLayout());

        userJLabel.setBackground(new java.awt.Color(255, 255, 255));
        userJLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userJLabel.setForeground(new java.awt.Color(255, 255, 255));
        userJLabel.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 63, 0, 0);
        containerJPanel.add(userJLabel, gridBagConstraints);

        passwordJLabel.setBackground(new java.awt.Color(255, 255, 255));
        passwordJLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        passwordJLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordJLabel.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 63, 0, 0);
        containerJPanel.add(passwordJLabel, gridBagConstraints);

        userJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userJTextField.setName("userJTextField"); // NOI18N
        userJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userJTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 420;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 134);
        containerJPanel.add(userJTextField, gridBagConstraints);

        loginButton.setActionCommand("ingresar");
        loginButton.setBackground(new java.awt.Color(153, 153, 153));
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setLabel("Ingresar");
        loginButton.setName("ingresar"); // NOI18N
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 365;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 42, 0, 134);
        containerJPanel.add(loginButton, gridBagConstraints);

        returnJButton.setBackground(new java.awt.Color(153, 153, 153));
        returnJButton.setForeground(new java.awt.Color(255, 255, 255));
        returnJButton.setText("atrás");
        returnJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        returnJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        returnJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnJButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 138;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 183, 38, 0);
        containerJPanel.add(returnJButton, gridBagConstraints);

        usersJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        usersJComboBox.setAlignmentX(CENTER_ALIGNMENT);
        usersJComboBox.setAlignmentY(CENTER_ALIGNMENT);
        usersJComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        usersJComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usersJComboBoxItemStateChanged(evt);
            }
        });
        usersJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersJComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 138;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 183, 0, 0);
        containerJPanel.add(usersJComboBox, gridBagConstraints);

        passwordJPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordJPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordJPasswordFieldActionPerformed(evt);
            }
        });
        passwordJPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordJPasswordFieldKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 420;
        gridBagConstraints.ipady = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 42, 0, 134);
        containerJPanel.add(passwordJPasswordField, gridBagConstraints);

        iconJLabel.setIcon(new javax.swing.ImageIcon("./icons/user.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 204, 0, 0);
        containerJPanel.add(iconJLabel, gridBagConstraints);

        tittleJLabel.setFont(new java.awt.Font("Yu Gothic Medium", 1, 50)); // NOI18N
        tittleJLabel.setForeground(new java.awt.Color(255, 255, 255));
        tittleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittleJLabel.setText("Welcome");
        tittleJLabel.setAlignmentX(CENTER_ALIGNMENT);
        tittleJLabel.setAlignmentY(CENTER_ALIGNMENT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 42, 0, 134);
        containerJPanel.add(tittleJLabel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userJTextFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        login();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void passwordJPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordJPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordJPasswordFieldActionPerformed

    private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonKeyPressed

    private void usersJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersJComboBoxActionPerformed
        selectListUser();
    }//GEN-LAST:event_usersJComboBoxActionPerformed

    private void usersJComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usersJComboBoxItemStateChanged
        if(evt.getSource() == usersJComboBox) {
            String seleccionado = (String) usersJComboBox.getSelectedItem();
            setTitle(seleccionado);
        }
    }//GEN-LAST:event_usersJComboBoxItemStateChanged

    private void returnJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnJButtonActionPerformed
        usersJComboBox.setVisible(true);
        returnJButton.setVisible(false);
    }//GEN-LAST:event_returnJButtonActionPerformed

    private void passwordJPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordJPasswordFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_passwordJPasswordFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containerJPanel;
    private javax.swing.JLabel iconJLabel;
    private java.awt.Button loginButton;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JPasswordField passwordJPasswordField;
    private javax.swing.JButton returnJButton;
    private javax.swing.JLabel tittleJLabel;
    private javax.swing.JLabel userJLabel;
    private javax.swing.JTextField userJTextField;
    private javax.swing.JComboBox<String> usersJComboBox;
    // End of variables declaration//GEN-END:variables
}
