package com.unmsm.operating.system.simulator.apps.wordpad.editor;
//package paqueteFrame;
  
   import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

   /**
    *      PanelBorder.java
    *         
    *      Copyright 2010 
    * </br>
    * </br>     
    * </br>
    * <A HREF="http://frlx.wordpress.com">Frlx</A>
    * </br>
    * </br>
    *      This program is free software; you can redistribute it and/or modify
    *      it under the terms of the GNU General Public License as published by
    *      the Free Software Foundation; either version 2 of the License, or
    *      (at your option) any later version.
    * </br>     
    *      This program is distributed in the hope that it will be useful,
    *      but WITHOUT ANY WARRANTY; without even the implied warranty of
    *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    *      GNU General Public License for more details.
    * </br>     
    *      You should have received a copy of the GNU General Public License
    *      along with this program; if not, write to the Free Software
    *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
    *      MA 02110-1301, USA.
    * </br>     
    * </br>     
    * </br>      
    *      Clase PanelBorder, solo tiene constructores hereda de TitledBorder.
    *      Dibuja un marco alrededor de un panel, se invoca de la siguiente manera
    * </br>
    * </br>     
    *      JPanel jpMiPanel=new JPanel();
    * </br>
    *      jpMiPanel.setBorder(new PanelBorder("Titulo Panel"));
    * </br>
    *      jpMiPanel.setBorder(new PanelBorder(Color.BLUE,"Titulo Panel"));
    * </br>   
    *      jpMiPanel.setBorder(new PanelBorder(Color.YELLOW,50,"Titulo Panel"));
    * </br> 
    *      jpMiPanel.setBorder(new PanelBorder(Color.YELLOW,Color.ORANGE,10,"Titulo Panel"));
    * </br>
    *      jpMiPanel.setBorder(new PanelBorder(Color.GREEN,6,TitledBorder.CENTER,"Titulo Panel"));
    *      
    *      
    *      @see TitledBorder
    *      @author frlx
    *      @version 2.0
    *      @since 1.6
    *      	*/
     
public class PanelBorder extends TitledBorder 
{
   	
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   *Pone color y nombre al borde
   */  
       public PanelBorder(Color color,String nombre)
      {
         super(new LineBorder(color),nombre);
      }
   
     /**
      * Un borde de color negro y tama�o 1 por defecto, con un t�tulo.
      * */
       public PanelBorder(String nombre)
       {
    	   super(new LineBorder(Color.black),nombre);
    	   
       }
     
   /**
   *Da color al borde,al titulo del borde y pone nombre al borde
   */
       public PanelBorder(Color color,Color letra,String nombre )
      {
         super(new LineBorder(color),nombre);
         super.setTitleColor(letra);
      }
      
   /**
   *Pone color al borde, da un ancho y pone un nombre la borde
   */
       public PanelBorder(Color color,int ancho,String nombre)   
      {
         super(new LineBorder(color, ancho),nombre);            
      }
   
   	
   	/**
   	*Da color al borde,al titulo del borde, da ancho a la linea del borde
   	*y pone nombre al borde
   	*/
       public PanelBorder(Color color,Color letra,int ancho,String nombre)
      {
         super(new LineBorder(color, ancho),nombre);
         super.setTitleColor(letra);
      
      }
           
       /**
   	 *Pone color al borde, da un ancho, una posicion y le da un nombre
   	 */            
       public PanelBorder(Color color,int ancho,int posicion,String nombre)   
      {
         super(new LineBorder(color, ancho),nombre);
         super.setTitleJustification(posicion);
      }
        
   /**
   *Da color al borde, un color de letra,un ancho,una posicion, y un nombre
   */
       public PanelBorder(Color color,Color letra,int ancho,int posicion,String nombre)
      {
         super(new LineBorder(color,ancho),nombre);
         super.setTitleColor(letra);
         super.setTitleJustification(posicion);
      }
   
   
   }
   

