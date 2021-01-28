package com.unmsm.operating.system.simulator.apps.wordpad.editor;
//package paqueteFrame;


import java.io.File;

import javax.swing.JFileChooser;

public class AbrirFichero 
{
   JFileChooser escojeFichero;
	String nombreFichero;
	File temp;
	
	public AbrirFichero(String nombreFichero)
	{
		this.nombreFichero=nombreFichero;
	}
	
	public AbrirFichero()
	{
		nombreFichero="default";
	}
    
	public String getNombreFichero()
	{
		return nombreFichero;
	}
	
	public void setNombreFichero(String nombreFichero)
	{
		this.nombreFichero=nombreFichero;
	}
    
    public boolean menu()
   {
    	boolean regresa=false;
    
    	try
       {
         escojeFichero=new JFileChooser();
         escojeFichero.setCurrentDirectory(new File(nombreFichero));
         int ok=escojeFichero.showOpenDialog(null);
         
         System.out.println("-->ok:"+ok);
         temp=new File("temp");
         temp=escojeFichero.getSelectedFile();
         
         
         if(temp.getAbsolutePath()!=null && ok==0)
         {
      	  
      	   nombreFichero=temp.getAbsolutePath();
           regresa=true;
         }
         else
         {
      	   System.out.println("No se ha elejido un fichero");
      	   regresa=false;
      	  }
           
      }
      catch(NullPointerException ev)
      {
           
    	    regresa=false;
            System.out.println("-->No hay fichero seleccionado");   
      }
     return regresa;
               
   }           

}
