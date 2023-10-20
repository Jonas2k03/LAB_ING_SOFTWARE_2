/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsoftii_6;

import java.util.List;

/**
 *
 * @author Juan Carlos Fernandez Cuetia
 * @author Jonathan Felipe Hurtado Diaz
 */
public class paqPearlHarbor extends PaqueteDecorador {
    
    public paqPearlHarbor(iPaqueteTuristico pt) {
        super(pt);
        pt.getDescripcion().add("Visita museo Pearl Harbor");
        pt.getDescripcion().add("Visita Palacio Iloani");
        pt.getDescripcion().add("Visita museo Bishop");
        pt.getDescripcion().add("Visita USS Arizona Memorial");
        pt.getDescripcion().add("");
    }
    
     @Override
    public double getPrecio() {
        return paqTuristico.getPrecio() + 653;
    }

    @Override
    public int getDuracion() {
        return paqTuristico.getDuracion() + 2;
    }

    @Override
    public List<String> getDescripcion() {
        return paqTuristico.getDescripcion();
    }
    
    
    
}
