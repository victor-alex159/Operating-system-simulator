package com.unmsm.operating.system.simulator.apps.wordpad.escuchadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.unmsm.operating.system.simulator.apps.wordpad.creadPdf.GuardaPdf;
import com.unmsm.operating.system.simulator.apps.wordpad.editor.EditorGui;

public class EscuchaGuardaPdf implements ActionListener 
{
	EditorGui editor;
	
	public EscuchaGuardaPdf(EditorGui ed)
	{
		editor=ed;
	}
		
	
	public void actionPerformed(ActionEvent arg0) 
	{
		GuardaPdf guardaPdf=new GuardaPdf(editor.getPathFile());
		guardaPdf.escribeEnDocumento(editor.getTexto());
		if(guardaPdf.getOk())
		{
		
			JOptionPane.showMessageDialog(editor.getVentana(), "Se ha guardado el documento en PDF", "Guardar PDF", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(editor.getVentana(), "No se ha podido guardar el documento en PDF", "Guardar PDF", JOptionPane.ERROR_MESSAGE);
		}
	}

}
