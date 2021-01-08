/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.operating.system.simulator.apps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alex
 */
public class Notepad extends javax.swing.JFrame {

    /**
     * Creates new form Notepad
     */
    
    public Notepad() {
        initComponents();
        newFile();
        about();
        openFile();
        saveFile();
    }
    
    
    /*public void exit() {
        subMenuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose();
                //System.exit(0);
            }
        });
    }*/
    
    public void about() {
        subMenuAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bloc de Notas hecho por Grupo 1 de Sistemas Operativos!!");
            }
        });
    }
    
    public void newFile() {
        subMenuNewFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
    }
    
    public void openFile() {
        subMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
    }
    
    public void saveFile() {
        subMenuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }
    
    public void open() {
        boolean isThereText = false;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            textArea.setText("");
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt");
                fileChooser.setFileFilter(filter);
                List<String> lines = Files.readAllLines(Paths.get(path));
                lines.forEach(line -> {
                    textArea.append(line+"\n");
                });
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Solo archivos en formato de texto!!");
                e.printStackTrace();
            }
        }
    }
    
    public void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileWriter writerFile = null;
            try {
                writerFile = new FileWriter(file);
                writerFile.write(textArea.getText());
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writerFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        subMenuNewFile = new javax.swing.JMenuItem();
        subMenuOpen = new javax.swing.JMenuItem();
        subMenuSave = new javax.swing.JMenuItem();
        menuInfo = new javax.swing.JMenu();
        subMenuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        menuFile.setText("Archivo");

        subMenuNewFile.setText("Nuevo");
        menuFile.add(subMenuNewFile);

        subMenuOpen.setText("Abrir");
        menuFile.add(subMenuOpen);

        subMenuSave.setText("Guardar");
        subMenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuSaveActionPerformed(evt);
            }
        });
        menuFile.add(subMenuSave);

        jMenuBar1.add(menuFile);

        menuInfo.setText("Info");

        subMenuAbout.setText("Acerca de Notepad");
        menuInfo.add(subMenuAbout);

        jMenuBar1.add(menuInfo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subMenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuSaveActionPerformed

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
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notepad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notepad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuInfo;
    private javax.swing.JMenuItem subMenuAbout;
    private javax.swing.JMenuItem subMenuNewFile;
    private javax.swing.JMenuItem subMenuOpen;
    private javax.swing.JMenuItem subMenuSave;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}