package com.unmsm.operating.system.simulator.apps.wordpad.editor;
//package paqueteFrame;

   import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

   /**
    *      OperacionesFichero.java
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
    *      Esta clase realiza las siguientes operaciones sobre fichero:
    * </br>	-Verificar si existe un fichero, si no existe lo crea.
    * </br> -Escritura sobre un fichero.
    *      
    *      @author frlx
    *      @version 2.0
    *      @since 1.6   
    */	
    public class OperacionesFichero
   {
     
    
      private String nombre;
      private File fichero;
 
      /**
       * Constructor
       * */
       public OperacionesFichero()
      {
         nombre="default";
         fichero=new File(nombre);
         verificaFichero();
      
      }
   	
       /**
        * Constructor, recibe el nombre del fichero y lo crea
        * @param nombre
        * */
       public OperacionesFichero(String nombre)
      {
         this.nombre=nombre;
         fichero=new File(nombre);
         verificaFichero(nombre);
      }
       
       
      /**
       * Verifica si el fichero de la clase existe
       * si no existe lo crea.
       * @exception IOException
       * */
       public void verificaFichero()
      {
         if(!fichero.exists())
         {
            try
            {
               if(fichero.createNewFile())
               {
                  System.out.println("-->Fichero" +nombre+" creado Ok");
               }
            }
                catch(IOException e)
               {
                  System.err.println("-->Error al crear fichero"+ nombre + "\n"+e.getMessage());
                
                  
               }
         }
      }
   
       /**
        * verificaFichero, recibe el nombre del fichero y verfica si existe, si no existe lo crea
        * @param nomFich
        * @exception IOException
        * */
       public void verificaFichero(String nomFich)
      {
         File temp=new File(nomFich);
         if(!temp.exists())
         {
            try
            {
               if(temp.createNewFile())
               {
                  System.out.println("-->Fichero" +nomFich+" creado Ok");
               }
            }
                catch(IOException e)
               {
                  System.err.println("-->Error al crear fichero "+ nomFich + "\n"+e.getMessage());
               }
         }
         else
         {
            System.out.println("-->El fichero ya existe, leyendo...");
         }
      }
   
       /**
        * Cambia el nombre del fichero de la clase
        * @param nombre
        * */
       public void setNombre(String nombre)
      {
         this.nombre=nombre;
      }
   
       /**
        * Regresa el nombre del fichero de la clase
        * @return nombre
        * */
       public String getNombre()
      {
         return nombre;
      }
   
       /**
        * Regresa el path del fichero
        * @return String
        * */
       public String getPath()
      {
         return fichero.getAbsolutePath();
      }
   
       
       /**
        * Escribe sobre un fichero.
        * @param fichero , nombre del fichero.
        * @param escribir , datos a escribir en el fichero.
        * @param afir , true=sobreescribir, false=no reescribir.
        * @exception IOException , por si hay ag�n error al escribir en el fichero.
        * @return boolean , true=guardar ok, false=error al guardar.
        * */
       public boolean ficheroEscritura(String fichero, String escribir, boolean afir) 
       { 
               
          try 
          { 
             FileWriter escribe= new FileWriter(fichero, afir); 
             escribe.write(escribir);
             escribe.close();
             System.out.println("\n---Se han guardado los datos");
             return true;
          } 
              catch (IOException e)
             { 
                System.out.println("\n---Se ha producido un error durante la escritura del archivo ");
                return false;
             } 
       }

	/**
	 * @return the fichero
	 */
	public File getFichero() {
		return fichero;
	}

	/**
	 * @param fichero the fichero to set
	 */
	public void setFichero(File fichero) {
		this.fichero = fichero;
	}
       
       
       
   }
