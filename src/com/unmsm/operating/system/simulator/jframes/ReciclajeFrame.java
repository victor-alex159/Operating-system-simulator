
package com.unmsm.operating.system.simulator.jframes;

import com.unmsm.operating.system.simulator.controllers.FileController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class ReciclajeFrame extends javax.swing.JFrame {

    double newFileLocX = 0;
    double newFileLocY = 0;
    ExplorerFrame explorer = new ExplorerFrame();
    public ReciclajeFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        //getFile(false);
    }
    
    
    public void getFile(boolean isClosedReciclaje) {
        String path = "./recicle";
        File files = new File(path);
        String[] listFiles;
        double x = 0.0;
        double y = 0.0; 
        int cont = 0;
        if(isClosedReciclaje) {
            listFiles = null;
            setIconFiles("", x, y, true);
        }
        listFiles = files.list();
        if(listFiles != null || listFiles.length != 0) {
            for (String listFileName : listFiles) {
                cont++;
                if(cont%7 == 0) {
                    x = 0.0;
                    y = y + 0.2;
                }
                //ystem.out.println(listFile);
                setIconFiles(listFileName, x, y, false);
                x = x + 0.25;
            }
            newFileLocX = x;
            newFileLocY = y;
        }
    }
    
    public void setIconFiles(String nameFile, double x, double y,  boolean clear) {
        int size = 400;
        JPanel panel = bodyReciclaje;
        JLabel icon = new JLabel(nameFile);
        icon.setLocation((int)(size * x), (int)(size * y));
        icon.setSize(new Dimension(100, 100));
        addImageIcon("iconDoc.png", icon, panel);
        icon.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int xDif = icon.getLocation().x + x - icon.getWidth();
                int yDif = icon.getLocation().y + y - icon.getHeight();
                icon.setLocation(xDif, yDif);
            }
        });
        
        icon.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    JFrame frame = new JFrame();
                    JPanel panel = new JPanel();
                    JButton delete = new JButton("eliminar");
                    JButton copy = new JButton("copiar");
                    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    panel.setPreferredSize(new Dimension(100, 100));
                    panel.setMaximumSize(new Dimension(100, 100));
                    panel.add(delete);
                    panel.add(copy);
                    frame.getContentPane().add(panel);
                    frame.setSize(250, 150);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);  
                    frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    
                    delete.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if(SwingUtilities.isLeftMouseButton(e)) {
                                FileController fileController = new FileController();
                                fileController.moveFileToRecicle(nameFile);
                                
                            }
                        }
                    });
                }
            }
            
        });
        if(clear) {
            icon.setIcon(null);
            icon.setText(null);
            icon.revalidate();
        }
    }
    
    
    public void addImageIcon(String nameImageIcon, JLabel iconLabel, JPanel panelBody) {
        ImageIcon icon = new ImageIcon("./icons/".concat(nameImageIcon));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        iconLabel.setIcon(scaledIcon);
        panelBody.add(iconLabel);
        panelBody.repaint();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reciclajePanel = new javax.swing.JPanel();
        sideBar = new javax.swing.JPanel();
        bodyReciclaje = new javax.swing.JPanel();
        headerReciclaje = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        directoryTextField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sideBar.setBackground(new java.awt.Color(240, 242, 245));

        javax.swing.GroupLayout sideBarLayout = new javax.swing.GroupLayout(sideBar);
        sideBar.setLayout(sideBarLayout);
        sideBarLayout.setHorizontalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        sideBarLayout.setVerticalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        bodyReciclaje.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyReciclajeLayout = new javax.swing.GroupLayout(bodyReciclaje);
        bodyReciclaje.setLayout(bodyReciclajeLayout);
        bodyReciclajeLayout.setHorizontalGroup(
            bodyReciclajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        bodyReciclajeLayout.setVerticalGroup(
            bodyReciclajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        headerReciclaje.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Buscar");

        backButton.setText("Atras");

        jButton2.setText("Adelante");

        javax.swing.GroupLayout headerReciclajeLayout = new javax.swing.GroupLayout(headerReciclaje);
        headerReciclaje.setLayout(headerReciclajeLayout);
        headerReciclajeLayout.setHorizontalGroup(
            headerReciclajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerReciclajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(directoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(8, 8, 8))
        );
        headerReciclajeLayout.setVerticalGroup(
            headerReciclajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerReciclajeLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(headerReciclajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(directoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reciclajePanelLayout = new javax.swing.GroupLayout(reciclajePanel);
        reciclajePanel.setLayout(reciclajePanelLayout);
        reciclajePanelLayout.setHorizontalGroup(
            reciclajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerReciclaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(reciclajePanelLayout.createSequentialGroup()
                .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodyReciclaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        reciclajePanelLayout.setVerticalGroup(
            reciclajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reciclajePanelLayout.createSequentialGroup()
                .addComponent(headerReciclaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reciclajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyReciclaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(reciclajePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reciclajePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ReciclajeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReciclajeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReciclajeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReciclajeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReciclajeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bodyReciclaje;
    private javax.swing.JTextField directoryTextField;
    private javax.swing.JPanel headerReciclaje;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel reciclajePanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel sideBar;
    // End of variables declaration//GEN-END:variables
}
