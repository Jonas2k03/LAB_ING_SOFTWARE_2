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
public class paqNature extends PaqueteDecorador {
    
     public paqNature(iPaqueteTuristico pt) {
        super(pt);
        pt.getDescripcion().add("Visita Kualoa Ranchv");
        pt.getDescripcion().add("Visita Maui Ocean Center");
        pt.getDescripcion().add("Visita Akaka Falls  State Park.");
        pt.getDescripcion().add("");
    }
    
     @Override
    public double getPrecio() {
        return paqTuristico.getPrecio() + 720;
    }

    @Override
    public int getDuracion() {
        return paqTuristico.getDuracion() + 5;
    }

    @Override
    public List<String> getDescripcion() {
        return paqTuristico.getDescripcion();
    }
    
}
