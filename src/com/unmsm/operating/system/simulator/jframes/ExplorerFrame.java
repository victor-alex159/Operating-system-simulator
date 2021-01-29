
package com.unmsm.operating.system.simulator.jframes;

import com.unmsm.operating.system.simulator.apps.Notepad;
import com.unmsm.operating.system.simulator.controllers.FileController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

import com.unmsm.operating.system.simulator.model.Directory;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ExplorerFrame extends javax.swing.JFrame {

    /**
     * Creates new form ExplorerFrame
     */
    double newFileLocX = 0;
    double newFileLocY = 0;
    boolean isClosedReciclaje;
    Notepad notePad = new Notepad();
    public ExplorerFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        explorer();
        search();
        //readFile();
    }

    public boolean isIsClosedReciclaje() {
        return isClosedReciclaje;
    }

    public void setIsClosedReciclaje(boolean isClosedReciclaje) {
        this.isClosedReciclaje = isClosedReciclaje;
    }
    
    public void explorer() {
        bodyExplorer.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    JFrame frame = new JFrame();
                    JPanel panel = new JPanel();
                    JButton create = new JButton("crear documento");
                    JButton open = new JButton("abir");
                    JButton delete = new JButton("eliminar");
                    JButton copy = new JButton("copiar");
                    JButton createFolder = new JButton("crear carpeta");
                    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    panel.setPreferredSize(new Dimension(100, 100));
                    panel.setMaximumSize(new Dimension(100, 100));
                    panel.add(create);
                    panel.add(open);
                    panel.add(delete);
                    panel.add(copy);
                    panel.add(createFolder);
                    frame.getContentPane().add(panel);
                    frame.setSize(250, 150);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);  
                    frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                     
                    create.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JFrame frame = new JFrame();
                            JPanel panel = new JPanel();
                            JTextField editText = new JTextField(8);
                            JButton saveNameFile = new JButton("Guardar");
                            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            panel.setPreferredSize(new Dimension(100, 100));
                            panel.setMaximumSize(new Dimension(100, 100));
                            panel.add(editText);
                            panel.add(saveNameFile);
                            frame.getContentPane().add(panel);
                            frame.setSize(250, 150);
                            frame.setVisible(true);
                            frame.setLocationRelativeTo(null);  
                            frame.setDefaultCloseOperation(HIDE_ON_CLOSE);

                            saveNameFile.addMouseListener(new MouseAdapter() {
                                 @Override
                                 public void mouseReleased(MouseEvent e) {
                                     if(SwingUtilities.isLeftMouseButton(e)) {
                                         try {
                                            createFile(editText.getText(), "file");
                                            addFile(editText.getText(), "file");
                                             FileController fileController = new FileController();
                                             //fileController.createDirectory("carpeta1", "./docFiles");
                                             //fileController.createFile("archivo", "txt", "docFiles");
                                             //fileController.moveFileToRecicle(editText.getText());
                                             
                                             
                                        }catch(IOException ex) {
                                            ex.printStackTrace();
                                        }
                                     }
                                 }
                            });
                         }  
                     });
                    createFolder.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JFrame frame = new JFrame();
                            JPanel panel = new JPanel();
                            JTextField editText = new JTextField(8);
                            JButton saveNameFile = new JButton("Guardar");
                            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            panel.setPreferredSize(new Dimension(100, 100));
                            panel.setMaximumSize(new Dimension(100, 100));
                            panel.add(editText);
                            panel.add(saveNameFile);
                            frame.getContentPane().add(panel);
                            frame.setSize(250, 150);
                            frame.setVisible(true);
                            frame.setLocationRelativeTo(null);  
                            frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                            saveNameFile.addMouseListener(new MouseAdapter() {
                                 @Override
                                 public void mouseReleased(MouseEvent e) {
                                     if(SwingUtilities.isLeftMouseButton(e)) {
                                         try {
                                            createFile(editText.getText(), "folder");
                                             setIconFiles(editText.getText(), newFileLocX, newFileLocY, "folder", false);
                                             
                                        }catch(IOException ex) {
                                            ex.printStackTrace();
                                        }
                                     }
                                 }
                            }); 
                        }
                    
                    });
                }
            }
        
        });
    }
    
    public void createFile(String nameFile, String type) throws IOException {
        OutputStream output = null;
        OutputStreamWriter outWriter = null;
        Writer bufferWriter = null;
        String path = "";
        try {
            if("file".equals(type)) {
                path = ("./docFiles/".concat(nameFile).concat(".txt"));        
                output = new FileOutputStream(path);
                outWriter = new OutputStreamWriter(output);
                bufferWriter = new BufferedWriter(outWriter);
                bufferWriter.write("HolaMundo");
                bufferWriter.flush();
            }
            if("folder".equals(type)) {
                path = ("./docFiles/".concat(nameFile));
                File directory = new File(path);
                if(directory.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "Carpeta creada");
                }
            }
            
            
        } catch(IOException err) {
            err.printStackTrace();
            
        } finally {
            try {
                if(output != null) {
                    output.close();
                }
                if(outWriter != null) {
                    outWriter.close();
                }
                if(bufferWriter != null) {
                    bufferWriter.close();
                }
            } catch(IOException err) {
                err.printStackTrace();
            }
        }
        
        
    }
    
    public void addFile(String nameFile, String type) {
        Reader reader = null;
        BufferedReader bufferReader = null;
        String path = null;
        boolean existFile = false;
        try {
            if("file".equals(type)){
                path = ("./docFiles/".concat(nameFile).concat(".txt"));
                reader = new FileReader(path);
                bufferReader = new BufferedReader(reader);
                double c = 0.0;
                double d = 0.1; 
                int data = bufferReader.read();
                while(data != -1) {
                    data = bufferReader.read();
                    existFile = true;
                }
                if(existFile) {
                    setIconFiles(nameFile, newFileLocX, newFileLocY, type, false);
                }
            }
            
        } catch(IOException err) {
            err.printStackTrace();
            
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (bufferReader != null) {
                    bufferReader.close();
                }
            } catch(IOException err) {
                err.printStackTrace();
            }
        }
    }
    
    public void deleteFile(String nameFile) {
        File reader = null;
        BufferedReader bufferReader = null;
        String path = ("./docFiles/".concat(nameFile).concat(".txt"));

        try {
            reader = new File(path);
            
            if(reader.delete()) {
                System.out.println("Archivo eliminado");
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
            
        }
    }
    
    public void readFile(boolean clear) {
        String path = "./docFiles";
        File files = new File(path);
        double x = 0.0;
        double y = 0.0; 
        int cont = 0;
        String[] listFiles;
        listFiles = files.list();
        if(listFiles != null || listFiles.length != 0) {
            for (String listFile : listFiles) {
                cont++;
                if(cont%7 == 0) {
                    x = 0.0;
                    y = y + 0.2;
                }
                //ystem.out.println(listFile);
                if(clear) {
                    if(listFile.contains(".txt")) {
                        setIconFiles(listFile, x, y, "file", true);
                    }
                    setIconFiles(listFile, x, y, "folder", true);
                    
                } else {
                    if(listFile.contains(".txt")) {
                        setIconFiles(listFile, x, y, "file", false);
                    } else {
                        setIconFiles(listFile, x, y, "folder", false);
                    }      
                }
                x = x + 0.25;
            }
            newFileLocX = x;
            newFileLocY = y;
        }
    }
    
    public String searchFile(String name) {
        String path = "./docFiles";
        String nameFile = null;
        File files = new File(path);
        double x = 0.0;
        double y = 0.0; 
        int cont = 0;
        String[] listFiles;
        listFiles = files.list();
        if(listFiles != null || listFiles.length != 0) {
            for (String listFile : listFiles) {           
                if(listFile.contains(".txt")) {
                    listFile = listFile.replaceAll(".txt", "");
                    if(name.equals(listFile)) {
                        nameFile = name.concat(".txt");
                    }
                } else {
                    if(name.equals(listFile)) {
                        nameFile = name;
                    }
                }
            }
            return nameFile;
        }
        return nameFile;
    }
    
    public void searchFileByName(String name) {
        String nameFile = searchFile(name);
        System.out.println(nameFile);
        if(nameFile != null) {
            double x = 0.0;
            double y = 0.0;
            int size = 400;
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            JButton openFile = new JButton("Abrir");
            JButton copyFile = new JButton("copiar");
            JButton deleteFile = new JButton("eliminar");
            JLabel icon = new JLabel(nameFile);
            icon.setLocation((int)(size * x), (int)(size * y));
            icon.setSize(new Dimension(100, 100));
            if(nameFile.contains(".txt")) {
                addImageIcon("iconDoc.png", icon, panel);            
            } else {
                addImageIcon("iconFolder.png", icon, panel);
            }
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.setPreferredSize(new Dimension(100, 100));
            panel.setMaximumSize(new Dimension(100, 100));
            panel.add(openFile);
            panel.add(copyFile);
            panel.add(deleteFile);
            frame.getContentPane().add(panel);
            frame.setSize(250, 150);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);  
            frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
            
            openFile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(nameFile.contains(".txt")) {
                        String path = ("./docFiles/".concat(nameFile));
                        notePad.openWithData(path);
                        notePad.setVisible(true);
                        frame.setVisible(false);
                        notePad.setDefaultCloseOperation(HIDE_ON_CLOSE);   
                    }
                }
            });
            
            
            
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no existe");
        }
        
        
    }
    
    public void search() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueSearch = searchTextField.getText();
                searchFileByName(valueSearch);
            }
        });
    }
    
    public void setIconFiles(String nameFile, double x, double y, String type, boolean clear) {
        int size = 400;
        JPanel panel = bodyExplorer;
        JLabel icon = new JLabel(nameFile);
        icon.setLocation((int)(size * x), (int)(size * y));
        icon.setSize(new Dimension(100, 100));
        if("file".equals(type)) {
            addImageIcon("iconDoc.png", icon, panel);            
        }
        if("folder".equals(type)) {
            addImageIcon("iconFolder.png", icon, panel);
        }
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
        if(clear) {
            icon.setIcon(null);
            icon.setText(null);
            icon.revalidate();
        }
        
        icon.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    JFrame frame = new JFrame();
                    JPanel panel = new JPanel();
                    JButton openFile = new JButton("abrir");
                    JButton delete = new JButton("eliminar");
                    JButton copy = new JButton("copiar");
                    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    panel.setPreferredSize(new Dimension(100, 100));
                    panel.setMaximumSize(new Dimension(100, 100));
                    panel.add(openFile);
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
                                //setIsClosedReciclaje(true);
                                icon.setIcon(null);
                                icon.setText(null);
                                icon.revalidate();
                                
                            }
                        }
                    });
                    openFile.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if(nameFile.contains(".txt")) {
                                    String path = ("./docFiles/".concat(nameFile));
                                    notePad.openWithData(path);
                                    notePad.setVisible(true);
                                    frame.setVisible(false);
                                    notePad.setDefaultCloseOperation(HIDE_ON_CLOSE); 
                                }
                            }
                    });
                }
            }
            
        });
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

        explorerPanel = new javax.swing.JPanel();
        sideBar = new javax.swing.JPanel();
        headerExplorer = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        directoryTextField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bodyExplorer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        sideBar.setBackground(new java.awt.Color(240, 242, 245));

        javax.swing.GroupLayout sideBarLayout = new javax.swing.GroupLayout(sideBar);
        sideBar.setLayout(sideBarLayout);
        sideBarLayout.setHorizontalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        sideBarLayout.setVerticalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        headerExplorer.setBackground(new java.awt.Color(204, 204, 204));

        searchButton.setText("Buscar");

        backButton.setText("Atras");

        jButton2.setText("Adelante");

        javax.swing.GroupLayout headerExplorerLayout = new javax.swing.GroupLayout(headerExplorer);
        headerExplorer.setLayout(headerExplorerLayout);
        headerExplorerLayout.setHorizontalGroup(
            headerExplorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerExplorerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(directoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton)
                .addGap(8, 8, 8))
        );
        headerExplorerLayout.setVerticalGroup(
            headerExplorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerExplorerLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(headerExplorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(directoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        bodyExplorer.setBackground(new java.awt.Color(255, 255, 255));
        bodyExplorer.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyExplorerLayout = new javax.swing.GroupLayout(bodyExplorer);
        bodyExplorer.setLayout(bodyExplorerLayout);
        bodyExplorerLayout.setHorizontalGroup(
            bodyExplorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bodyExplorerLayout.setVerticalGroup(
            bodyExplorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout explorerPanelLayout = new javax.swing.GroupLayout(explorerPanel);
        explorerPanel.setLayout(explorerPanelLayout);
        explorerPanelLayout.setHorizontalGroup(
            explorerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerExplorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(explorerPanelLayout.createSequentialGroup()
                .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodyExplorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        explorerPanelLayout.setVerticalGroup(
            explorerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explorerPanelLayout.createSequentialGroup()
                .addComponent(headerExplorer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(explorerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyExplorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(explorerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(explorerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ExplorerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExplorerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExplorerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExplorerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExplorerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bodyExplorer;
    private javax.swing.JTextField directoryTextField;
    private javax.swing.JPanel explorerPanel;
    private javax.swing.JPanel headerExplorer;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel sideBar;
    // End of variables declaration//GEN-END:variables
}
