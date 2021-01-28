package com.unmsm.operating.system.simulator.apps.wordpad.editor;
//package paqueteFrame;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *      EscribeFichero.java
 *      
 *      Copyright 2010 
 *      
 *      </br>
 * </br>     
 * </br>
 * <A HREF="http://frlx.wordpress.com">Frlx</A>
 * </br>
 * </br>
 *     This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *</br>      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 * </br>     
 * </br>     
 * </br>     
 *  	Esta clase guarda un fichero, si el fichero ya ha sido creado
 *  	lo guarda a�adiendo el el contenid anterior.
 *  </br>
 *  	Si el fichero no existe muestra un JFileChooser, que nos permite guardar el fichero con 
 *  	un nombre.
 *        
 *      @author frlx
 *      @version 1.0
 *      @since 1.6  
 *      @see JFileChooser 
 */	

public class EscribeFichero 
{
	private  String nombreFichero;
	private  String resguarda;
	
	/**
	 * Guarda el fichero, si el fichero no existe pedimos mediante un JFileChooser, 
	 * el nombre y resto de datos para guardara el fichero
	 * @param nombreFichero Nombre del fichero
	 * @param jtaDatos Objeto del que vamos a obtener los datos.
	 * */
	 public void guardar(String nombreFichero,JTextPane jtaDatos)
	  {
		 this.nombreFichero=nombreFichero;
		  System.out.println("-.-.->Nombre Fichero: "+nombreFichero);
		  if(nombreFichero.contains("default"))
		  {
			  System.out.println("-->lanzar escojer fichero y guardar");
			  preguntaNombreGuarda(jtaDatos);
		  }
		  else
		  {
			  System.out.println("-->lanzar solo guardar");
			  guardaFichero(nombreFichero,jtaDatos);
		  }
		  
      }
	  
	 /**
	  * Presenta JFileChooser, para preguntarnos los datos de un fichero a guardar
	  * @param jtaDatos objeto de donde provienen los datos a guardar.
	  * @exception NullPointerException
	  * @exception IOException 
	  * */
	  public void preguntaNombreGuarda(JTextPane jtaDatos)
	  {
		  resguarda=jtaDatos.getText();
		  JFileChooser guardar=new JFileChooser();
		  int ok=guardar.showSaveDialog(null);
		  if(ok==0)
		  {
			  File nueva =guardar.getSelectedFile();
			  if(!nueva.exists())
			  {
				  try
				  {
   				  if(nueva.createNewFile())
   				  {
   					  try
   					  {
   						  new OperacionesFichero().ficheroEscritura(nueva.getAbsolutePath(), jtaDatos.getText(),true);
   						  nombreFichero=nueva.getAbsolutePath();			  
   						  System.out.println("-->Path guardar fichero: "+nueva.getAbsolutePath());
   					  }
   					  catch(NullPointerException ex)
   					  {
   						new OperacionesFichero().ficheroEscritura(nueva.getAbsolutePath(), "",true);
   					  }
   					  
   				  }
   				 
				   }
				   catch(IOException ex)
				   {
					  System.out.println("-->Error al crear fichero para guardar");
				   }
			   }
			  
			   else
			  {
				  JOptionPane.showMessageDialog(null,"Error, el fichero ya existe.","Guardar",JOptionPane.ERROR_MESSAGE);
				  System.out.println("-->Error el fichero ya existe");
			   }
				  
		  }
		  else
		  {
			  System.out.println("\n-->ok:"+ok);
			  jtaDatos.setText(resguarda);
		  }
		 
	  }
	  
	  /**
	   * Regresa el path del fichero
	   * @return String
	   * */
	  public String getPathFichero()
	  {
		  return nombreFichero; 
	  }
	  
	  
	  /**
	   * Guarda en un fichero los datos.
	   * @param nombreFichero
	   * @param jtaDatos Fuente de datos
	   * */
	  public void guardaFichero(String nombreFichero,JTextPane jtaDatos)
	  {
		  new OperacionesFichero().ficheroEscritura(nombreFichero, jtaDatos.getText(),false);
	  }
	  
	  


}
