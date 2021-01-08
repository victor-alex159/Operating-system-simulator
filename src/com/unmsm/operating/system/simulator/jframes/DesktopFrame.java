package com.unmsm.operating.system.simulator.jframes;

import com.unmsm.operating.system.simulator.apps.*;
import com.unmsm.operating.system.simulator.apps.paint.DrawFrame;
import com.unmsm.operating.system.simulator.controllers.FileController;
import com.unmsm.operating.system.simulator.model.User;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class DesktopFrame extends javax.swing.JFrame {

    ExplorerFrame explorer = new ExplorerFrame();
    ReciclajeFrame reciclajeExplorer = new ReciclajeFrame();
    DrawFrame drawFrame = new DrawFrame();
    //PaintFrame paint = new PaintFrame();
    TresenRaya game1=new TresenRaya();
    Calculadora calculadora=new Calculadora();
    Notepad notePad = new Notepad();
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
        showWindows();
        showReciclaje();
        showIcon();
        showInternet();
        showPaint();
        bodyDesktopConf();
        addImagToDesktop();
        iconNotePadMouseDragger();
    }

    private DesktopFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void showReciclaje() {
        iconReciclaje.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
                    reciclajeExplorer.setVisible(true);
                    reciclajeExplorer.setDefaultCloseOperation(HIDE_ON_CLOSE);
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JFrame frame = new JFrame();
                    JPanel panel = new JPanel();
                    JButton open = new JButton();
                    Map<String, JButton> map = new HashMap<>();
                    map.put("Abrir", open);
                    setWindowEdit(map);

                    open.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            reciclajeExplorer.setVisible(true);
                            frame.setVisible(false);
                            reciclajeExplorer.setDefaultCloseOperation(HIDE_ON_CLOSE);
                        }
                    });
                }
            }
        });
        iconReciclaje.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int xDif = iconReciclaje.getLocation().x + x - iconReciclaje.getWidth();
                int yDif = iconReciclaje.getLocation().y + y - iconReciclaje.getHeight();
                iconReciclaje.setLocation(xDif, yDif);
            }
        });
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
    
    public void iconDoubleClicked(JLabel icon, JFrame frame) {
        icon.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                }
            }
        });
    }
    
    public void showWindows() {
        //iconDoubleClicked(iconPaint, paint);
        iconDoubleClicked(iconGame1, game1);
        iconDoubleClicked(iconCalculadora, calculadora);
        iconDoubleClicked(iconNotePad, notePad);
    }

    public void showInternet() {
        iconInternet.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
                    navegador();
                }
            }
        });
    }

    public static void navegador() {
        Runtime app = Runtime.getRuntime();
        try {
            app.exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        } catch (Exception e) {

        }
    }
    
    public void showPaint() {
        iconPaint.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
                               Draw();
                               drawFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                }
            }
        });
    }
    
    public static void Draw()
	{       DrawFrame frame = new DrawFrame();
		frame.setTitle("Dibuja");
		frame.setBackground( new Color(39,174,96));
		frame.pack();

		// put the frame in the middle of the display
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		frame.setVisible(true);
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
        addImageIcon("internet.png");
        addImageIcon("game1.png");
        addImageIcon("calculadora.png");
        addImageIcon("startWindows.png");
        addImageIcon("reciclaje.png");
        addImageIcon("iconNotePad.png");
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
        } else if(nameImageIcon.equals("myPc.png")) {
            iconMyPc.setIcon(scaledIcon);
        } else if(nameImageIcon.equals("reciclaje.png")) {
            iconReciclaje.setIcon(scaledIcon);
        } else if (nameImageIcon.equals("paint.png")) {
            iconPaint.setIcon(scaledIcon);
        } else if(nameImageIcon.equals("internet.png")){
            iconInternet.setIcon(scaledIcon);
        } else if(nameImageIcon.equals("game1.png")){
            iconGame1.setIcon(scaledIcon);
        } else if(nameImageIcon.equals("iconNotePad.png")) {
            iconNotePad.setIcon(scaledIcon);
        }
        else{
            iconCalculadora.setIcon(scaledIcon);
        }
    }

    public void addImagToDesktop() {
        ImageIcon image = new ImageIcon("./icons/fondo1.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        bodyDesktop = new JPanel(new BorderLayout());
        bodyDesktop.add( label, BorderLayout.CENTER );
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
        iconInternet = new javax.swing.JLabel();
        iconGame1 = new javax.swing.JLabel();
        iconCalculadora = new javax.swing.JLabel();
        iconReciclaje = new javax.swing.JLabel();
        iconNotePad = new javax.swing.JLabel();

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

        iconInternet.setBackground(new java.awt.Color(51, 0, 204));
        iconInternet.setText("Internet");
        iconInternet.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                iconInternetMouseDragged(evt);
            }
        });

        iconGame1.setBackground(new java.awt.Color(51, 0, 204));
        iconGame1.setText("3enRaya");
        iconGame1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                iconGame1MouseDragged(evt);
            }
        });

        iconCalculadora.setBackground(new java.awt.Color(51, 0, 204));
        iconCalculadora.setText("Calculadora");
        iconCalculadora.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                iconCalculadoraMouseDragged(evt);
            }
        });

        iconReciclaje.setText("RECICALJE");

        iconNotePad.setText("Bloc de Notas");

        javax.swing.GroupLayout bodyDesktopLayout = new javax.swing.GroupLayout(bodyDesktop);
        bodyDesktop.setLayout(bodyDesktopLayout);
        bodyDesktopLayout.setHorizontalGroup(
            bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bodyDesktopLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bodyDesktopLayout.createSequentialGroup()
                            .addComponent(iconInternet, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(86, 86, 86)
                            .addComponent(iconNotePad))
                        .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(bodyDesktopLayout.createSequentialGroup()
                                .addComponent(iconMyPc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(iconGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bodyDesktopLayout.createSequentialGroup()
                                .addComponent(iconPaint, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(iconCalculadora, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(bodyDesktopLayout.createSequentialGroup()
                        .addComponent(iconReciclaje, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyDesktopLayout.setVerticalGroup(
            bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyDesktopLayout.createSequentialGroup()
                .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyDesktopLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iconMyPc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconGame1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(bodyDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iconPaint, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconCalculadora, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(iconInternet, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bodyDesktopLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(iconNotePad)))
                .addGap(34, 34, 34)
                .addComponent(iconReciclaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
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

    public void iconNotePadMouseDragger() {
        iconNotePad.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int xDif = iconNotePad.getLocation().x + x - iconNotePad.getWidth();
                int yDif = iconNotePad.getLocation().y + y - iconNotePad.getHeight();
                iconNotePad.setLocation(xDif, yDif);
            }
        });
    }
    
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

    private void iconInternetMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconInternetMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        int xDif = iconInternet.getLocation().x + x - iconInternet.getWidth();
        int yDif = iconInternet.getLocation().y + y - iconInternet.getHeight();
        iconInternet.setLocation(xDif, yDif);
    }//GEN-LAST:event_iconInternetMouseDragged

    private void iconGame1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconGame1MouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        int xDif = iconGame1.getLocation().x + x - iconGame1.getWidth();
        int yDif = iconGame1.getLocation().y + y - iconGame1.getHeight();
        iconGame1.setLocation(xDif, yDif);
    }//GEN-LAST:event_iconGame1MouseDragged

    private void iconCalculadoraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconCalculadoraMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        int xDif = iconCalculadora.getLocation().x + x - iconCalculadora.getWidth();
        int yDif = iconCalculadora.getLocation().y + y - iconCalculadora.getHeight();
        iconCalculadora.setLocation(xDif, yDif);
    }//GEN-LAST:event_iconCalculadoraMouseDragged

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
    private javax.swing.JLabel iconCalculadora;
    private javax.swing.JLabel iconGame1;
    private javax.swing.JLabel iconInternet;
    private javax.swing.JLabel iconMyPc;
    private javax.swing.JLabel iconNotePad;
    private javax.swing.JLabel iconPaint;
    private javax.swing.JLabel iconReciclaje;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel taskBar;
    // End of variables declaration//GEN-END:variables
}