package com.unmsm.operating.system.simulator.apps.wordpad.imprimir;

import java.awt.Desktop;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.unmsm.operating.system.simulator.apps.wordpad.editor.EditorGui;
import com.unmsm.operating.system.simulator.apps.wordpad.editor.OperacionesFichero;

public class Imprimir 
{
	
	public Imprimir(EditorGui ed)
	{
				
		OperacionesFichero op=new OperacionesFichero(".temp_@.txt");
		op.ficheroEscritura(op.getNombre(), ed.getJtaDatos().getText(),false);  
			
		try
		{
			Desktop.getDesktop().print(op.getFichero());
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			
			
		}
		
		finally
		{
			if(System.getProperty("os.name").toUpperCase().contains("LINUX"))
			{
				  JOptionPane.showMessageDialog(ed.getVentana(),"Operación no soprtada para Linux. \n :-( , I'm sorry", "Imprimir", JOptionPane.ERROR_MESSAGE);
			}
			 op.getFichero().delete();
		}
				
	}
	
	
}
