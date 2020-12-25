package com.unmsm.operating.system.simulator.jframes;

import com.unmsm.operating.system.simulator.apps.*;
import com.unmsm.operating.system.simulator.controllers.FileController;
import com.unmsm.operating.system.simulator.model.User;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DesktopFrame extends javax.swing.JFrame {

    ExplorerFrame explorer = new ExplorerFrame();
    PaintFrame paint=new PaintFrame();
    FileController fileController;
    User user = new User();

    /**
     * Creates new form DesktopFrame
     */
    public DesktopFrame(User user) {
        initComponents();
        this.user = user;
        fileController = new FileController(user);
        this.setExtendedState(DesktopFrame.MAXIMIZED_BOTH);
        Date hour = new Date();
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("hh:mm a");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        hourNow.setText(hourFormat.format(hour));
        dateNow.setText(dateFormat.format(date));
        showExplorer();
        showPaint();
        showIcon();
        bodyDesktopConf();
    }

    private DesktopFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showExplorer() {
        iconMyPc.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
                    explorer.setVisible(true);
                    explorer.setDefaultCloseOperation(HIDE_ON_CLOSE);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JFrame frame = new JFrame();
                    JPanel panel = new JPanel();
                    JButton open = new JButton();
                    JButton delete = new JButton();
                    JButton copy = new JButton();
                    Map<String, JButton> map = new HashMap<>();
                    map.put("Abrir", open);
                    map.put("Eliminar", delete);
                    map.put("Copiar", copy);
                    setWindowEdit(map);

                    open.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            explorer.setVisible(true);
                            frame.setVisible(false);
                            explorer.setDefaultCloseOperation(HIDE_ON_CLOSE);
                        }
                    });
                }
            }

        });
    }

    public void showPaint() {
        iconPaint.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
                   paint.setVisible(true);
                   paint.setDefaultCloseOperation(HIDE_ON_CLOSE);
                }
            }
    });
    }

    public void bodyDesktopConf() {
        bodyDesktop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JButton addImage = new JButton();
                    JButton copy = new JButton();
                    JButton create = new JButton();
                    JButton createDirectory = new JButton();
                    Map<String, JButton> map = new HashMap<>();
                    map.put("Agregar fondo", addImage);
                    map.put("Copiar", copy);
                    map.put("Crear arhivo", create);
                    map.put("Crear carpeta", createDirectory);
                    setWindowEdit(map);

                    create.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                fileController.createFile("archivo", "txt", user.getUsername());
                            }
                        }
                    });

                    createDirectory.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                fileController.createDirectory("midirectorio", user.getUsername());
                            }
                        }
                    });
                }
            }
        });
    }

    public void setWindowEdit(Map<String, JButton> button) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        button.entrySet().forEach((btn) -> {
            btn.getValue().setText(btn.getKey());
            panel.add(btn.getValue());
        });
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setPreferredSize(new Dimension(150, 150));
        panel.setMaximumSize(new Dimension(150, 150));
        frame.getContentPane().add(panel);
        frame.setSize(250, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    public void showIcon() {
        addImageIcon("myPc.png"); // Icono Mi Pc
        addImageIcon("paint.png");
        addImageIcon("startWindows.png");

    }

    public void addImageIcon(String nameImageIcon) {
        ImageIcon icon = new ImageIcon("./icons/".concat(nameImageIcon));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);

        if (nameImageIcon.equals("startWindows.png")) {
            startButton.setOpaque(false);
            startButton.setContentAreaFilled(false);
            startButton.setFocusPainted(false);
            startButton.setIcon(scaledIcon);
        } else if (nameImageIcon.equals("myPc.png")) {
            iconMyPc.setIcon(scaledIcon);
        } else {
            iconPaint.setIcon(scaledIcon);
        }
    }

    public void addImagToDesktop() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyDesktop = new javax.swing.JPanel();
        taskBar = new javax.swing.JPanel();
        dateNow = new javax.swing.JLabel();
        hourNow = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        iconMyPc = new javax.swing.JLabel();
        iconPaint = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        bodyDesktop.setBackground(new java.awt.Color(153, 204, 255));

        taskBar.setBackground(new java.awt.Color(30, 30, 30));

        dateNow.setForeground(new java.awt.Color(255, 255, 255));
        dateNow.setText("DATENOW");

        hourNow.setForeground(new java.awt.Color(255, 255, 255));
        hourNow.setText("HOURNOW");

        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskBarLayout = new javax.swing.GroupLayout(taskBar);
        taskBar.setLayout(taskBarLayout);
        taskBarLayout.setHorizontalGroup(
            taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskBarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1107, Short.MAX_VALUE)
                .addGroup(taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hourNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        taskBarLayout.setVerticalGroup(
            taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskBarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(taskBarLayout.createSequentialGroup()
                        .addComponent(hourNow, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateNow)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        iconMyPc.setBackground(new java.awt.Color(51, 0, 204));
        iconMyPc.setText("MI PC");
        iconMyPc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                iconMyPcMouseDragged(evt);
            }
        });

        iconPaint.setBackground(new java.awt.Color(51, 0, 204));
        iconPaint.setText("Paint");
        iconPaint.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                iconPaintMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout bodyDesktopLayout = new javax.swing.GroupLayout(bodyDesktop);
        bodyDesktop.setLayout(bodyDesktopLayout);
        bodyDesktopLayout.setHorizontalGroup(
            bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bodyDesktopLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconMyPc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconPaint, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyDesktopLayout.setVerticalGroup(
            bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyDesktopLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(iconMyPc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(iconPaint, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 412, Short.MAX_VALUE)
                .addComponent(taskBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        iconPaint.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconMyPcMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMyPcMouseDragged
        int x = evt.getX();
        int y = evt.getY();
        int xDif = iconMyPc.getLocation().x + x - iconMyPc.getWidth();
        int yDif = iconMyPc.getLocation().y + y - iconMyPc.getHeight();
        iconMyPc.setLocation(xDif, yDif);
    }//GEN-LAST:event_iconMyPcMouseDragged

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startButtonActionPerformed

    private void iconPaintMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconPaintMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        int xDif = iconPaint.getLocation().x + x - iconPaint.getWidth();
        int yDif = iconPaint.getLocation().y + y - iconPaint.getHeight();
        iconPaint.setLocation(xDif, yDif);

    }//GEN-LAST:event_iconPaintMouseDragged

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
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesktopFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyDesktop;
    private javax.swing.JLabel dateNow;
    private javax.swing.JLabel hourNow;
    private javax.swing.JLabel iconMyPc;
    private javax.swing.JLabel iconPaint;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel taskBar;
    // End of variables declaration//GEN-END:variables
}
