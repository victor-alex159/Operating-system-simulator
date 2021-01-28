package com.unmsm.operating.system.simulator.apps.wordpad.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import colores.Colores;

/**
 * Abre un JDialog, con informaci�n acerca del autor del editor
 * @author frlx.wordpress.com
 * @version 1.0
 * @since 1.6
 * */


public class AcercaDeEditor  extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private JButton cc=null;
	private JButton lnk=null;
	private JButton java=null;
	private JButton lnx=null;
	
	public  AcercaDeEditor()
	{
	    super();
	    super.setIconImage(new ImageIcon(getClass().getResource("/iconos/information.png")).getImage());
		super.setLayout(new BorderLayout());
		super.setSize(460,420);
		super.setVisible(true);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		
		JPanel panel=(JPanel) super.getContentPane();
		
		java=new JButton(new ImageIcon(getClass().getResource("/iconos/java_jre.jpg")));
		java.setBackground(Colores.BackColor);
		java.setCursor(new Cursor(Cursor.HAND_CURSOR));
		java.addActionListener(new EscuchaBoton());
		java.setBorder(new LineBorder(Color.black,2,true));
		java.setToolTipText("Ir al api de JAVA");
		
		lnx=new JButton(new ImageIcon(getClass().getResource("/iconos/tux01.png")));
		lnx.setBackground(Colores.BackColor);
		lnx.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lnx.addActionListener(new EscuchaBoton());
		lnx.setBorder(new LineBorder(Color.black,2,true));
		lnx.setToolTipText("Ir a la página de GNU");
			
		cc=new JButton(new ImageIcon(getClass().getResource("/iconos/cc1.jpg")));
		cc.setBackground(Colores.BackColor);
		cc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cc.addActionListener(new EscuchaBoton());
		cc.setBorder(new LineBorder(Color.black,2,true));
		cc.setToolTipText("Ir a la página de Creative Commons");
		
		lnk=new JButton(new ImageIcon(getClass().getResource("/iconos/wordpress.jpg")));
	    lnk.setBackground(Colores.BackColor);
	    lnk.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    lnk.addActionListener(new EscuchaBoton());
	    lnk.setBorder(new LineBorder(Color.black,2,true));
	    lnk.setToolTipText("Ir al blog");
		
	    JTextArea inf=new JTextArea();
		inf.setFont(new Font("",Font.BOLD, 12));
		inf.setLineWrap(true);
		inf.append("\n *IDE: Eclipse");
		inf.append("\n\tBuild id: 20100218-1602");
		inf.append("\n\thttp://eclipse.org/");
		inf.append("\n *JDK: \n\t1.6");
		inf.append("\n *Autor: \n\tfrlx.wordpress.com\n\tfrlx.wordpress.com@gmail.com");
		inf.append("\n *Versión: \n\t2.0");
		inf.append("\n\n");
		inf.append("010100101101010101010101010101010010101010101010101101010");
		inf.append("010010101010100110101010101010101010011010101010101010101");
		inf.append("010100101010101010100111010101101010101110101010101010101");
		inf.append("010010101010100110101010101010101010011010101010101010101");
		inf.append("010100101010101010100111010101101010101110101010101010101");
		inf.append("010010101010100110101010101010101010011010101010101010101");
		inf.append("010100101010101010100111010101101010101110101010101010101");
		inf.append("010010101010100110101010101010101010011010101010101010101");
		inf.append("010010101010100110101010101010101010011010101010101010101");
		inf.append("010101111101010101010111111101111011110101110101110101010");
	
		inf.setEditable(false);
		inf.setBackground(Color.BLACK);
		inf.setForeground(Colores.GreenAllColor);
		inf.setBorder(new LineBorder(Colores.GreenAllColor,5,true));
				
		
		JPanel jpIzq=new JPanel();
		jpIzq.setBackground(Colores.BackColor);
		jpIzq.setLayout(new GridLayout(4,1));
		jpIzq.add(lnx);
		jpIzq.add(java);
		jpIzq.add(cc);
		jpIzq.add(lnk);
		panel.add(jpIzq,BorderLayout.WEST);
		panel.add(inf,BorderLayout.CENTER);
		panel.updateUI();
				
	}
	
	/**
	 * Clase Interna para los escuchadores de los botones de links a páginas web
	 * */
	
	private class EscuchaBoton implements ActionListener
	{

		public void actionPerformed(ActionEvent ev) 
		{
			JButton bt=(JButton) ev.getSource();
			
			if(bt.equals(lnx))
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://www.gnu.org/home.es.html"));		
				}
				
				catch(Exception ex)
				{
					System.out.println("-->Error al conectarse..");
				}
			}
			else if(bt.equals(java))
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://download.oracle.com/javase/6/docs/api/"));		
				}
				
				catch(Exception ex)
				{
					System.out.println("-->Error al conectarse..");
				}
				
			}
			else if(bt.equals(lnk))
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://frlx.wordpress.com"));
							
				}
				
				catch(Exception ex)
				{
					System.out.println("-->Error al conectarse..");
				}
			}
			else
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://es.creativecommons.org/licencia/"));
							
				}
				
				catch(Exception ex)
				{
					System.out.println("-->Error al conectarse..");
				}
			}
			
		}
		
	}
}
