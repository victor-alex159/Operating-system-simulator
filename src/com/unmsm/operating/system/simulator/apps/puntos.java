/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unmsm.operating.system.simulator.apps;

import java.util.LinkedList;

/**
 *
 * @author Leyla
 */
public class puntos {
    LinkedList<Integer> PuntosX=new LinkedList();
    LinkedList<Integer> PuntosY=new LinkedList();
    public void GuardarPuntos(int x,int y){
    PuntosX.add(x);
    PuntosY.add(y);

    }
    
    public LinkedList <Integer> listax(){
       return PuntosX; 
    }
    public LinkedList <Integer> listay(){
       return PuntosY; 
    }
    
    public void  VaciarLista(){
        while(!PuntosX.isEmpty()){
            PuntosX.remove();
            PuntosY.remove();
        }
    }
}
