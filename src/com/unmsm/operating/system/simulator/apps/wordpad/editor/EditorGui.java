package com.unmsm.operating.system.simulator.apps.wordpad.editor;
/*
 *    
 *      
 *     Copyright 2010 frlx <frlx.wordpress.com@gmail.com>
 *      
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */
   
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import colores.Colores;
import com.unmsm.operating.system.simulator.apps.wordpad.escuchadores.EscuchaGuardaPdf;
import com.unmsm.operating.system.simulator.apps.wordpad.escuchadores.EscuchaImprimir;


    public class EditorGui //extends JFrame
   {
    	
    JFrame ventana;
    /*-----Paneles Principales----*/
      JPanel jpPrincipal;
      JScrollPane jpLectura;
   
      /*-------Componentes---------*/
      JButton jbPath;
      JTextPane jtaDatos;
      
      /*-------Menu---------*/
      JMenuBar jmbMenuBar;
      JMenu jmArchivo;
      JMenu jmEdicion;
      JMenu jmAyuda;
      		
      	  /*menu Archivo*/
	      JMenuItem jmiAbrir;
	      JMenuItem jmiNuevo;
	      JMenuItem jmiGuardar;
	      JMenuItem jmiGuardarComo;
	      JMenuItem jmiActualizar;
	      JMenuItem jmiSalir;
	            
	      /*menu Edici�n*/
	      JMenuItem jmiAjusteLinea;
	      JMenuItem jmiFuente;
	      JMenuItem jmiReemplazar;
	      JMenuItem jmiCopiar;
	      JMenuItem jmiPegar;
	      JMenuItem jmiCortar;
	      
	      /*menu Ayuda*/
	      JMenuItem jmiAcerca;
	      
        
      /*---------JToolBar--------*/
      JToolBar jtbHerramientas;
      	  JButton jbPdf;
	      JButton jbGuardar;
	      JButton jbGuardarComo;
	      JButton jbActualizar;
	      JButton jbNuevo;
	      JButton jbAbrir;
	      JButton jbReemplazar;
	      JButton jbJustificar;
	      JButton jbIzq;
	      JButton jbCen;
	      JButton jbDer;
	      JButton jbFuente;
	      JButton jbCopiar;
	      JButton jbPegar;
	      JButton jbCortar;
	      JButton jbImprimir;
       
      
      
      String nombreFichero;
      OperacionesFichero ref;
      Fuente fFuente;
      EscuchaGuardaPdf egPdf;
      EscuchaImprimir eiImprimir;
      
      /**
       * Constructor
       * */
      
      public EditorGui()
      {
      
         ventana=new JFrame("Editor");
         ventana.setIconImage(new ImageIcon(this.getClass().getResource("libro.png")).getImage());
         ref=new OperacionesFichero();
         inicializaPaneles();
         inicializaComponentes();
        ventana.setVisible(false);
        ventana.setSize(500,500);
         
         nombreFichero=ref.getPath();
                 
         menuPrincipal();
         barraHerramientas();
         actualiza();       
      }
       
      /**
       * Constructor
       * @param directorio path del fichero a abrir al iniciar
       * */
     
      public EditorGui(String directorio)
      {
    	 ventana=new JFrame("WordPad");
    	 ventana.setIconImage(new ImageIcon(getClass().getResource("libro.png")).getImage());
         ref=new OperacionesFichero();
         nombreFichero=directorio;
        
         inicializaPaneles();
         inicializaComponentes();
           
         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ventana.setVisible(true);
         ventana.setSize(500,500);
              
         ref.verificaFichero(directorio);
         leeFichero(directorio);
         System.out.println("Path fichero-->"+ref.getPath()+"=>"+nombreFichero);
         ventana.setTitle(nombreFichero);
         menuPrincipal();
         barraHerramientas();
         actualiza();   
                 
      }
      
      /**
       * Implementa un menu en la parte, superior del editor.
       * */
      public void menuPrincipal()
      {
		   jmbMenuBar=new JMenuBar();
		   jmbMenuBar.setBackground(new Color(176,196,222));
		   jmbMenuBar.setBorder(new PanelBorder(Color.BLACK,""));
		  
		   jmArchivo=new JMenu("Archivo");
		   menuArchivo();
		   
		   jmEdicion=new JMenu("Edición");
		   menuEdicion();
		   
		   jmAyuda=new JMenu("Ayuda");
		   menuAyuda();
		   
		   jmbMenuBar.add(jmArchivo);
		   jmbMenuBar.add(jmEdicion);
		   jmbMenuBar.add(jmAyuda);
		   		   
		   ventana.setJMenuBar(jmbMenuBar);
		  
	  }
      
      /**
       * Implementa los items del menu "Archivo"
       * */
      public void menuArchivo()
      {
    	  jmiAbrir=new JMenuItem("Abrir",new ImageIcon(this.getClass().getResource("folder-horizontal-open.png")));
    	  jmiAbrir.setBackground(new Color(176,196,222));
    	  jmiAbrir.addActionListener(new EscuchaAbrir());
    	  jmArchivo.add(jmiAbrir);
    	  
    	  jmiNuevo=new JMenuItem("Nuevo",new ImageIcon(getClass().getResource("page_2.png")));
    	  jmiNuevo.setBackground(new Color(176,196,222));
    	  jmiNuevo.addActionListener(new EscuchaNuevo());
    	  jmArchivo.add(jmiNuevo);
    	  jmArchivo.addSeparator();
    	  
    	  jmiGuardar=new JMenuItem("Guardar",new ImageIcon(getClass().getResource("disk.png")));
    	  jmiGuardar.setBackground(new Color(176,196,222));
    	  jmiGuardar.addActionListener(new EscuchaGuardar());
    	  jmArchivo.add(jmiGuardar);
    	      	  
    	  jmiGuardarComo=new JMenuItem("Guardar Como",new ImageIcon(getClass().getResource("drive_disk.png")));
    	  jmiGuardarComo.setBackground(new Color(176,196,222));
    	  jmiGuardarComo.addActionListener(new EscuchaGuardarComo());
    	  jmArchivo.add(jmiGuardarComo);
    	  jmArchivo.addSeparator();
    	  
    	  
    	  jmiActualizar=new JMenuItem("Recargar",new ImageIcon(getClass().getResource("arrow_refresh.png")));
    	  jmiActualizar.setBackground(new Color(176,196,222));
    	  jmiActualizar.addActionListener(new EscuchaActualizar());
    	  jmArchivo.add(jmiActualizar);
    	  jmArchivo.addSeparator();
    	  
    	  jmiSalir=new JMenuItem("Salir",new ImageIcon(getClass().getResource("cross-circle.png")));
    	  jmiSalir.setBackground(new Color(176,196,222));
    	  jmiSalir.addActionListener(new EscuchaSalir());
    	  
    	  jmArchivo.add(jmiSalir);
      }
      
      /**
       * Implementa los items del menu "Edici�n"
       * */
      public void menuEdicion()
      {
    	  
    	  jmiReemplazar=new JMenuItem("Reemplazar",new ImageIcon(getClass().getResource("textfield_rename.png")));
    	  jmiReemplazar.setBackground(new Color(176,196,222));
    	  jmiReemplazar.addActionListener(new EscuchaReemplaza());
    	  jmEdicion.add(jmiReemplazar);
    	  jmEdicion.addSeparator();
    	    	  
    	  jmiFuente=new JMenuItem("Fuente",new ImageIcon(getClass().getResource("font.png")));
    	  jmiFuente.setBackground(new Color(176,196,222));
    	  jmiFuente.addActionListener(new EscuchaFuente());
    	  jmEdicion.add(jmiFuente);
    	  jmEdicion.addSeparator();
    	  
    	  
    	  jmiCopiar=new JMenuItem("Copiar",new ImageIcon(getClass().getResource("page_2_copy.png")));
    	  jmiCopiar.setBackground(new Color(176,196,222));
    	  jmiCopiar.addActionListener(new EscuchaCopia());
    	  jmEdicion.add(jmiCopiar);
    	  
    	  jmiPegar=new JMenuItem("Pegar",new ImageIcon(getClass().getResource("broom.png")));
    	  jmiPegar.setBackground(new Color(176,196,222));
    	  jmiPegar.addActionListener(new EscuchaPega());
    	  jmEdicion.add(jmiPegar);
    	  
    	  jmiCortar=new JMenuItem("Cortar",new ImageIcon(getClass().getResource("cut.png")));
    	  jmiCortar.setBackground(new Color(176,196,222));
    	  jmiCortar.addActionListener(new EscuchaCorta());
    	  jmEdicion.add(jmiCortar);
    	  
      }
      
      /**
       * Implementa los items del menu "Ayuda"
       * */
      public void menuAyuda()
      {
    	   	      	  
          jmiAcerca=new JMenuItem("Acerca de..",new ImageIcon(getClass().getResource("eye.png")));
          jmiAcerca.setBackground(new Color(176,196,222));
          jmiAcerca.addActionListener(new EscuchaAcercaDe());
          jmAyuda.add(jmiAcerca);
       
    	  
      }
         
      /**
       * Implementa una barra de herramientas
       * */
      public void barraHerramientas()
      {
    	  	    	 
    	  jtbHerramientas=new JToolBar("");
    	  jtbHerramientas.setBorder(new PanelBorder(""));
    	  
    	  jbNuevo=new JButton(new ImageIcon(getClass().getResource("page_2.png")));
    	  jbNuevo.setToolTipText("Crear nuevo documento");
    	  jbNuevo.addActionListener(new EscuchaNuevo());
    	  jtbHerramientas.add(jbNuevo);
    	     	  
    	  jbAbrir=new JButton(new ImageIcon(getClass().getResource("folder-horizontal-open.png")));
    	  jbAbrir.addActionListener(new EscuchaAbrir());
    	  jbAbrir.setToolTipText("Abrir documento");
    	  jtbHerramientas.add(jbAbrir);
    	  
    	  jbActualizar=new JButton(new ImageIcon(getClass().getResource("arrow_refresh.png")));
          jbActualizar.addActionListener(new EscuchaActualizar());
          jbActualizar.setToolTipText("recargar documento sin guardar cambios");
          jtbHerramientas.add(jbActualizar);
          jtbHerramientas.addSeparator(new Dimension(10,25));                     
    	  
          jbPdf=new JButton(new ImageIcon(getClass().getResource("document-pdf.png")));
          jbPdf.setToolTipText("Guardar en pdf");
          egPdf=new EscuchaGuardaPdf(this);
          jbPdf.addActionListener(egPdf);
          jtbHerramientas.add(jbPdf);
          
    	  jbGuardar=new JButton(new ImageIcon(getClass().getResource("disk.png")));
    	  jbGuardar.addActionListener(new EscuchaGuardar());
    	  jbGuardar.setToolTipText("Guardar documento");
    	  jtbHerramientas.add(jbGuardar);
    	  
    	  jbGuardarComo=new JButton(new ImageIcon(getClass().getResource("drive_disk.png")));
          jbGuardarComo.addActionListener(new EscuchaGuardarComo());
          jbGuardarComo.setToolTipText("Guardar documento como");
          jtbHerramientas.add(jbGuardarComo);
          
          jbImprimir=new JButton(new ImageIcon(getClass().getResource("printer.png")));
          jbImprimir.setToolTipText("Imprimir");
          eiImprimir=new EscuchaImprimir(this);
          jbImprimir.addActionListener(eiImprimir);
          jtbHerramientas.add(jbImprimir);
          
         
          jtbHerramientas.addSeparator(new Dimension(10,25));
                  
          jbReemplazar=new JButton(new ImageIcon(getClass().getResource("textfield_rename.png")));
          jbReemplazar.addActionListener(new EscuchaReemplaza());
          jbReemplazar.setToolTipText("Reemplazar");
          
          jtbHerramientas.add(jbReemplazar);    
          jtbHerramientas.addSeparator(new Dimension(10,25));
          
          AlinearTexto b1=new AlinearTexto(1);
	      AlinearTexto b2=new AlinearTexto(2);
	      AlinearTexto b3=new AlinearTexto(3);
                    
          jbIzq=new JButton(new ImageIcon(getClass().getResource("text_align_left.png")));
          jbIzq.setToolTipText("Texto a la izquierda");
          
	      jbCen=new JButton(new ImageIcon(getClass().getResource("text_align_center.png")));
	      jbCen.setToolTipText("Centrar");
	      
	      jbDer=new JButton(new ImageIcon(getClass().getResource("text_align_right.png")));
	      jbDer.setToolTipText("Texto a la derecha");
	      
	      jbIzq.addActionListener(b1);
	      jbCen.addActionListener(b2);
	      jbDer.addActionListener(b3);
	      	      
          jtbHerramientas.add(jbIzq);
          jtbHerramientas.add(jbCen);
          jtbHerramientas.add(jbDer);
          
          jbFuente=new JButton(new ImageIcon(getClass().getResource("font.png")));
          jbFuente.addActionListener(new EscuchaFuente());
          jbFuente.setToolTipText("Tamaño y tipo de letra");
          
          jtbHerramientas.add(jbFuente);
          jtbHerramientas.addSeparator(new Dimension(10,25));
          
          jbCopiar=new JButton(new ImageIcon(getClass().getResource("page_2_copy.png")));
          jbCopiar.addActionListener(new EscuchaCopia());
          jbCopiar.setToolTipText("Copiar");
          jtbHerramientas.add(jbCopiar);
          
          jbPegar=new JButton(new ImageIcon(getClass().getResource("broom.png")));
          jbPegar.addActionListener(new EscuchaPega());
          jbPegar.setToolTipText("Pegar");          
          jtbHerramientas.add(jbPegar);
          
          jbCortar=new JButton(new ImageIcon(getClass().getResource("cut.png")));
          jbCortar.addActionListener(new EscuchaCorta());
          jbCortar.setToolTipText("Cortar");
          jtbHerramientas.add(jbCortar);
          
          
    	  jpPrincipal.add(jtbHerramientas, BorderLayout.PAGE_START);
    	  actualiza();
      }
      
      /**
       * Carga los datos de un fichero al area de texto.
       * @param directorio
       * @exception IOException
       * */
      public void leeFichero(String directorio)
      {
         try 
         {
            jtaDatos.read(new InputStreamReader(new FileInputStream(directorio)), null);
            nombreFichero=directorio;
         } 
                      
             catch (IOException ex)
            {
               System.out.println("-->Error al leer fichero ");
            }
        
      }
      
      /**
       * Inicializa los paneles
       * */
      public void inicializaPaneles()
      {
         jpPrincipal=new JPanel();
         jpPrincipal=(JPanel) ventana.getContentPane();
         jpPrincipal.setBorder(new PanelBorder(Color.black,""));
         jpPrincipal.setBackground(new Color(70,130,180));
       
         jpPrincipal.setLayout(new BorderLayout());
                  
      }
   
      /**
       * Inicializa los componentes 
       * */
      private void inicializaComponentes()
      {
              
         System.out.println("--->"+nombreFichero);
         
      
         jtaDatos=new JTextPane();
         jtaDatos.setBackground(Colores.BackArea);
                 
         jpLectura=new JScrollPane(jtaDatos);
         jpLectura.setBorder(new PanelBorder(Color.BLACK,Color.BLACK,1,TitledBorder.CENTER,""));
         jpLectura.setBackground(new Color(70,130,180));
      
         jpPrincipal.add(jpLectura,BorderLayout.CENTER);
      
         actualiza();
      }
   
      /**
       * Actualiza los paneles
       * */       
      public void actualiza()
      {
         jpLectura.updateUI();
         jpPrincipal.updateUI();
             	       	       	   
      }
   
      /**
       * Cambia el titulo de la ventana principal
       * */
      public void setTitle(String title)
      {
    	  ventana.setTitle(title);
      }
      
      /**
       * Obtiene el path completo del fichero
       * @return String
       * */
      public String getPathFile()
      {
    	  return nombreFichero;
      }
      
      public String[] getTexto()
      {
    	 String[] texto=jtaDatos.getText().split("\n");
    	  	 	
    	 return texto;
      }
      
      public String getTextoPlano()
      {
    	     	  	 	
    	 return jtaDatos.getText();
      }
      
      public Font getFontText()
	{
		return jtaDatos.getFont();
	}
      
      
      /**
	 * @return the jtaDatos
	 */
	public JTextPane getJtaDatos() {
		return jtaDatos;
	}

	/**
	 * @param jtaDatos the jtaDatos to set
	 */
	public void setJtaDatos(JTextPane jtaDatos) {
		this.jtaDatos = jtaDatos;
	}

	public JFrame getVentana()
      {
    	  return ventana;
      }
       
      /**
       * Clase que implementa la interf�z
       * ActionListener, para cuando queremos abrir un fichero
       * @see AbrirFichero
       * */
      /*---------------------ESCUCHADORES--------------*/         
      public class EscuchaAbrir implements ActionListener
       {
            AbrirFichero abrir =new AbrirFichero(nombreFichero);
           
    	    public void actionPerformed(ActionEvent e)
    	   {
    	    
    	     if(abrir.menu())
    	     {
    	    	 nombreFichero=abrir.getNombreFichero();
    	    	 leeFichero(nombreFichero);
    	      	 System.out.println("-->"+abrir.getNombreFichero());
    	     }
    	     
    	   }
       }

      /**
       * Clase que implementa la interf�z
       * ActionListener, para cuando queremos guardar un fichero
       * @see EscribeFichero
       * */
      public class EscuchaGuardar implements ActionListener
      {
    	  EscribeFichero escribe=new EscribeFichero(); 
    	  public void actionPerformed(ActionEvent e)
    	  {
    		  escribe.guardar(nombreFichero, jtaDatos);
    		  nombreFichero=escribe.getPathFichero();
    		  setTitle(nombreFichero);
    		
    		  
    	  }

      }
     

      /**
       * Clase que implementa la interf�z
       * ActionListener, para cuando queremos guardar un fichero
       * @see GuardarComo
       * */
      public class EscuchaGuardarComo implements ActionListener
      {
    	  
    	GuardarComo guardar=new GuardarComo();
		public void actionPerformed(ActionEvent e)
    	  {
    		 
			  guardar.guardar(nombreFichero, jtaDatos);
			  nombreFichero=guardar.getPathFichero();
    		  setTitle(nombreFichero);
    		 
    	  }
      }
      

      /**
       * Clase que implementa la interf�z
       * ActionListener, para cuando queremos recargar un fichero
       * */
      public class EscuchaActualizar implements ActionListener
       {
    	   public void actionPerformed(ActionEvent e)
    	   {
    		   if(nombreFichero!=null)
               {
                   jtaDatos.setText("");			   
    			   leeFichero(nombreFichero);
               }
    		   else
    		   {
    			   nombreFichero="";
    		   }
    			   
    	   }
       }
       

      /**
       * Clase que implementa la interf�z
       * ActionListener, para cuando queremos salir del editor
       * @exception Throwable
       * */
      public class EscuchaSalir implements ActionListener
      {
   	   public void actionPerformed(ActionEvent e)
   	   {
   		   System.out.println("-.-.->Menu/Salir pulsado, saliendo.....");
   		   try
   		   {
   			 
   			   int i =JOptionPane.showConfirmDialog(null,"¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
   			   System.out.println("\n-->selección: "+i);
   			   if (i==0)
   			   {
   				   System.out.println("\n\t-->Salir=Si");
   				   System.exit(0);
   			   }
   			   else
   			   {
   				   System.out.println("\n\t-->Salir=No");
   			   
   			   }
   				   
   			       			   
   		   }
   		   catch(Throwable ex)
   		   {
   		     System.err.println("-.->Error al cerrar");
   		   }
   	   }
   	   
      }
      

      /**
       * Clase que implementa la interf�z
       * ActionListener, para cuando queremos crear un nuevo fichero
       * @see GuardarComo
       * */
      public class EscuchaNuevo implements ActionListener
      {
    	  GuardarComo Nuevo=new GuardarComo();
  		  public void actionPerformed(ActionEvent e)
      	  {
  			  jtaDatos.setText("");
  			  Nuevo.guardar(nombreFichero, jtaDatos);
  			  nombreFichero=Nuevo.getPathFichero();
      		  setTitle(nombreFichero);
      		  leeFichero(nombreFichero);
      	  }
    	  
      }
      

      
      
      /**
       * 
       */

      public class AlinearTexto implements ActionListener 
      {

      	/**
      	 * 
      	 */
      	
      	//private JTextPane texto=null;
      	private int origen=0;
      	
      	public AlinearTexto(int op) 
      	{	
      		origen=op;
      		
      	}

      	/* (non-Javadoc)
      	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
      	 */
      	public void actionPerformed(ActionEvent ev) 
      	{
      		
      	  if(origen==1)
      	  {
      		  System.out.println("--->Izq");
                alineacion(StyleConstants.ALIGN_LEFT);
      	  }
      	  else if(origen==2)
      	  {
      		  		  
      		  System.out.println("--->Cen");
                alineacion(StyleConstants.ALIGN_CENTER);
      	  }
      	  
      	  else
      	  {
      		  System.out.println("--->Der");
                alineacion(StyleConstants.ALIGN_RIGHT);
      	  }
      	  
      	}
      	
      	private void alineacion(int stc)
    	{
    		StyledDocument sd=jtaDatos.getStyledDocument();
    		SimpleAttributeSet sas = new SimpleAttributeSet();  
            StyleConstants.setAlignment(sas, stc);  
            sd.setParagraphAttributes(0,jtaDatos.getText().length(), sas, false);  
            jtaDatos.updateUI();
    	}

      }

      
      
      /**
       * Clase que implementa la interf�z
       * ActionListener, para copiar un texto seleccionado
       * */
      public class EscuchaCopia implements ActionListener
      {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		jtaDatos.copy();    		  
    	  }
      }
      
      /**
       * Clase que implementa la interf�z
       * ActionListener, para pegar un texto copiado
       * */
      public class EscuchaPega implements ActionListener
      {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		  jtaDatos.paste();
    	  }
      }
      
      /**
       * Clase que implementa la interf�z
       * ActionListener, para cortar un texto seleccionado
       * */
      public class EscuchaCorta implements ActionListener
      {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		jtaDatos.cut();  
    	  }
      }
      
      /**
       * Clase que implementa la interf�z
       * ActionListener, para reemplazar un texto seleccionado
       * */
      public class EscuchaReemplaza implements ActionListener
      {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		  try
    		  {
    		    String resguardo=jtaDatos.getSelectedText();
    			String reemplaza=(String) JOptionPane.showInputDialog(null,"Reemplazar por:","Reemplazar",JOptionPane.OK_CANCEL_OPTION,new ImageIcon("textfield_rename.png"),null,null);
    			
    			if(reemplaza!=null)
    			{
    				resguardo=reemplaza;
    			}
    			 
    		    jtaDatos.replaceSelection(resguardo);
    		  }
    		  catch(Exception ex)
    		  {
    			  System.out.println("-->error de conversión");
    		  }
    	  }
      }
      
      /**
       * Instancia un objeto de la clase AcercaDeEditor
       * @see AcercaDeEditor
       * */
      public class EscuchaAcercaDe implements ActionListener
      {
    	  public void actionPerformed(ActionEvent e)
    	  {
              
    	  }
      }
              
      public class EscuchaFuente implements ActionListener
      {
    	  
    	  public void actionPerformed(ActionEvent e)
    	  {
    	
    		  fFuente=new Fuente(ventana,"Fuente");
    		
    		  
    	  }
    	  
    	 
      }
      
      
      /*-----------------------------------------FUENTE--------------------------------*/
      
      public class Fuente 
      {
      	/*------------Contenedores--------*/
      	JDialog jdInterfaz;
      	JPanel jpPrincipal;
      	JPanel jpTipoLetra;
      	JPanel jpConfiguracion;
      	JPanel jpTamanio;
      	JScrollPane jpEjemplo;
      	JPanel jpBotones;
      	JSplitPane jspPanelUno;
      	JSplitPane jspPanelDos;
      		
      	/*------------Componentes---------*/
      	JList jlTipoLetra;
      	JList jlConfiguracion;
      	JSlider jsTamanioLetra;
      	JTextArea jtaEjemplo;
      	JButton jbAceptar;
      	JButton jbCancelar;
      	
      	String[] sListaTipo={"Italic","Plain","Roman"};
      	String[] sListaConfiguracion={"Negrita","Cursiva"};
      	
      	Font fuente;
      	Font fuenteDefecto;
      	
      	int tamanio=12;
      	int tipoLetra=Font.TRUETYPE_FONT;
      	
      	boolean ok=false;
      
      	
      	public Fuente(JFrame padre,String titulo)
      	{
      		jdInterfaz=new JDialog(padre,titulo);
      		jdInterfaz.setSize(500,300);
      		jdInterfaz.setVisible(true);
      		jdInterfaz.setResizable(false);
      		jpPrincipal=new JPanel();
      		jpPrincipal=(JPanel) jdInterfaz.getContentPane();
      		inicializaPaneles();
      	}
      	
      	public Fuente()
      	{
      		jdInterfaz=new JDialog();
      		jdInterfaz.setSize(500,300);
      		jdInterfaz.setVisible(true);
      		jdInterfaz.setResizable(false);
      		jpPrincipal=new JPanel();
      		jpPrincipal=(JPanel) jdInterfaz.getContentPane();
      		inicializaPaneles();
      	}
      	
      	public void inicializaPaneles()
      	{
      		jpPrincipal.setLayout(new BorderLayout());
      				
      		inicializaTiposLetra();
      		inicializaConfLetra();
      		inicilizaTamanioLetra();
      		inicializaEjemplo();
      		inicializaBotones();
      		
      		jspPanelUno=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
      		jspPanelUno.add(jpTipoLetra);
      		jspPanelUno.add(jpConfiguracion);
      		
      		jspPanelDos=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
      		jspPanelDos.add(jpTamanio);
      		jspPanelDos.add(jpEjemplo);
      		
      		jpPrincipal.add(jspPanelUno,BorderLayout.WEST);
      		jpPrincipal.add(jspPanelDos,BorderLayout.CENTER);
      		jpPrincipal.add(jpBotones,BorderLayout.SOUTH);
      		
      		
      	}
      	
      	public void inicializaTiposLetra()
      	{
      		jpTipoLetra=new JPanel();
      		jpTipoLetra.setLayout(new BoxLayout(jpTipoLetra,BoxLayout.Y_AXIS));
      		jpTipoLetra.setBackground(new Color(238,232,170));
      	
      		jlTipoLetra=new JList(sListaTipo);
      		jlTipoLetra.setBackground(new Color(238,232,170));
      		jlTipoLetra.addListSelectionListener(new EscuchaTipoLetra ());
      		
      		jpTipoLetra.add(jlTipoLetra);
      		
      		
      	}
      	
      	public void inicializaConfLetra()
      	{

      		jpConfiguracion=new JPanel();
      		jpConfiguracion.setBackground(new Color(238,232,170));
      		
      		jlConfiguracion=new JList(sListaConfiguracion);
      		jlConfiguracion.setBackground(new Color(238,232,170));
      		jlConfiguracion.addListSelectionListener(new EscuchaConfLetra());
      		
      		jpConfiguracion.add(jlConfiguracion);
      	}
      	
      	public void inicilizaTamanioLetra()
      	{
      		tamanio=12;
      		jpTamanio=new JPanel();
      		jpTamanio.setBackground(new Color(238,232,170));
      		
      		jsTamanioLetra=new JSlider(JSlider.VERTICAL,5, 100,tamanio);
      		jsTamanioLetra.setMajorTickSpacing(10);
      		jsTamanioLetra.setMinorTickSpacing(2);
      		jsTamanioLetra.setPaintTicks(true);
      		jsTamanioLetra.setPaintLabels(true);
      		jsTamanioLetra.setBackground(new Color(238,232,170));
      		
      		jsTamanioLetra.addChangeListener(new EscuchaSlider());
      		
      		
      		jpTamanio.add(jsTamanioLetra);
      	}
      	
      	public void inicializaEjemplo()
      	{
      		jtaEjemplo=new JTextArea("Texto De ejemplo");
      		jpEjemplo=new JScrollPane(jtaEjemplo);
      		jpEjemplo.setBackground(new Color(238,232,170));
      	}
      		
      	public void inicializaBotones()
      	{
      		jpBotones=new JPanel();
      		jpBotones.setBackground(new Color(70,130,180));
      		
      		jbAceptar=new JButton("Aceptar");
      		jbAceptar.addActionListener(new  EscuhaBotonAceptar());
      		
      		jbCancelar=new JButton("Cancelar");
      		jbCancelar.addActionListener(new  EscuchaBotonCancelar());
      				
      		jpBotones.add(jbAceptar);
      		jpBotones.add(jbCancelar);
      	}
      	
      	public void configuraLetra(String nombre, int estilo, int tamanio)
      	{
      		fuente=new Font(nombre,estilo,tamanio);
      	
      	}
      	
      	public Font getFont()
      	{
      		return fuente;
      		
      	}
      	
      	public Font getFuenteRes()
      	{
      		return jtaEjemplo.getFont();
      	}
      	
      	/**-----------------Escuchadores-------------*/
      	public class EscuchaSlider implements ChangeListener
      	{
      		
      		public void stateChanged(ChangeEvent e)
      		{
      			JSlider jsRecupera=(JSlider) e.getSource();	
      			System.out.println(jsRecupera.getValue());
      			tamanio=jsRecupera.getValue();
      			configuraLetra("",tipoLetra,tamanio);
      			jtaEjemplo.setFont(getFont());
      			jtaDatos.setFont(getFont());
      		}
      	}
      	
      	
      	
      	public boolean getEstado()
      	{
      		System.out.println("--->entro en estado..");
      		return ok;
      	}
      	
      	public class EscuchaBotonCancelar implements ActionListener
      	{
      		public void actionPerformed(ActionEvent e)
      		{
      			
      			fuenteDefecto=new Font("",Font.TRUETYPE_FONT,12);
      			jtaDatos.setFont(fuenteDefecto);
      			jdInterfaz.setVisible(false);
      			
      			
      		}
      	}
      	
      	public class EscuhaBotonAceptar implements ActionListener
      	{
      		public void actionPerformed(ActionEvent e)
      		{
      			System.out.println("-->Aceptar");
      			jtaDatos.setFont(getFont());
      			jdInterfaz.setVisible(false);
      			ok=true;
      		}
      	}
      	
      	public class EscuchaTipoLetra implements ListSelectionListener
      	{
      		public void valueChanged(ListSelectionEvent e) 
      		{
      			JList temp=(JList)e.getSource();
      			
      			switch(temp.getSelectedIndex())
      			{
      				case 0:
      				{
      				  tipoLetra=Font.ITALIC;
      				  configuraLetra("",tipoLetra,tamanio);
      				  jtaEjemplo.setFont(getFont());
      				  break;
      				}
      				
      				case 1:
      				{
      				  tipoLetra=Font.PLAIN;
      				  configuraLetra("",tipoLetra,tamanio);
      				  jtaEjemplo.setFont(getFont());
      				  break;
      				}
      				case 2:
      				{
      				  tipoLetra=Font.TYPE1_FONT;
      				  configuraLetra("",tipoLetra,tamanio);
      				  jtaEjemplo.setFont(getFont());
      				  break;
      				}
      					
      			}
      		  jtaDatos.setFont(getFont());
      			System.out.println(temp.getSelectedIndex());
      		}
      	}
      	
      	public class EscuchaConfLetra implements ListSelectionListener
      	{
      		public void valueChanged(ListSelectionEvent e)
      		{
      			JList temp=(JList)e.getSource();
      			System.out.println(temp.getSelectedIndex());
      		}
      	}
      	
      }
      
             
      /*---------------------MAIN---------------------*/
       public static void main(String[] args)
     {
                        
         @SuppressWarnings("unused")
		EditorGui prueba_1=new EditorGui(); 
        
      }
   
       
   }
