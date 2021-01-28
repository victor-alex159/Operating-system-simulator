package com.unmsm.operating.system.simulator.apps.wordpad.creadPdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GuardaPdf 
{
	
	/*Nombre del fichero*/
	private String nombreFichero=null;
	private Boolean ok=false;
	
	/**
	 * @param String MiDoc
	 * */
	public GuardaPdf(String MiDoc)
	{	  	
		    if((MiDoc==null) || (MiDoc.equals("")))
		    {
		       nombreFichero="default.pdf";
		    }
			else
			{
				nombreFichero=MiDoc+".pdf";
			}
	}
	
	/**
	 * Constructor
	 * */
	public GuardaPdf()
	{
		 nombreFichero="default.pdf";
	}
	
	/**
	 * @param String[] lineas
	 * */
	public void escribeEnDocumento(String[] lineas)
	{
		    Document documento = new Document();
	        try 
	        {
	            PdfWriter.getInstance(documento, new FileOutputStream(nombreFichero));
	            documento.open();
	            
	            for(int i=0;i<lineas.length;i++)
	            {
	            	documento.add(new Paragraph(lineas[i]+"\n"));
	            }
	            ok=true;
	        } 
	   
	        catch (DocumentException ex) 
	        {
	            ex.printStackTrace();
	            ok=false;
	        } 
	        
	        catch (IOException ex) 
	        {
		         ex.printStackTrace();  
		         ok=false;
	        }
	        
	        documento.close();
	}
	
	
	/**
	 * @return String
	 * */
    public String getFileName()
    {
    	return nombreFichero;
    }
    
    public Boolean getOk()
    {
    	return ok;
    }
   
}
