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
public class paqAmazingHawaii extends PaqueteDecorador {
    
     public paqAmazingHawaii(iPaqueteTuristico pt) {
        super(pt);
        pt.getDescripcion().add("Escalar en el Waipio Valley");
        pt.getDescripcion().add("Surfear en  las playas Waikiki o Hanalei Bay");
        pt.getDescripcion().add("Bucear en Hanauma Bay");
        pt.getDescripcion().add("");
    }
    
     @Override
    public double getPrecio() {
        return paqTuristico.getPrecio() + 931;
    }

    @Override
    public int getDuracion() {
        return paqTuristico.getDuracion() + 3;
    }

    @Override
    public List<String> getDescripcion() {
        return paqTuristico.getDescripcion();
    }
}
