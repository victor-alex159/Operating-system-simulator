package com.unmsm.operating.system.simulator.apps.wordpad.escuchadores;

import com.unmsm.operating.system.simulator.apps.wordpad.imprimir.Imprimir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.unmsm.operating.system.simulator.apps.wordpad.editor.EditorGui;

public class EscuchaImprimir implements ActionListener 
{
	private EditorGui editor;
	
	public EscuchaImprimir(EditorGui ed)
	{
		editor=ed;	
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		new Imprimir(editor);
		

	}

}
