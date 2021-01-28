package com.unmsm.operating.system.simulator.apps.wordpad.editor;
//package paqueteFrame;


import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.*;


/**
 *      GuardarComo.java
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
 *  	Abre un JFileChooser, para  poder escoger el nombre y formato del fichero a guardar.
 *      
 *      @author frlx
 *      @version 1.0
 *      @since 1.6  
 *      @see JFileChooser 
 */	
public class GuardarComo 
{
	String nombreFichero;
	
	/**
	 * @param nombreFichero , nombre del fichero 
	 * @param jtaDatos ,JTextArea de d�nde vamos a coger los datos aguardar
	 * @exception NullPointerException
	 * */	
	public void guardar(String nombreFichero,JTextPane jtaDatos)
	  {
		  this.nombreFichero=nombreFichero;
		  JFileChooser guardar=new JFileChooser();
		  int ok=guardar.showSaveDialog(null);
		  System.out.println("-->ok: "+ok);
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
	  						  this.nombreFichero=nueva.getAbsolutePath();
	  						   			    						  
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
			  System.out.println("\n-->Cancelar");
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
	
}
