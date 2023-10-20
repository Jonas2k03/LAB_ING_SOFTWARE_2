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
public abstract class PaqueteDecorador implements iPaqueteTuristico {

    protected iPaqueteTuristico paqTuristico;

    public PaqueteDecorador(iPaqueteTuristico pt) {
        this.paqTuristico = pt;
    }

    @Override
    public double getPrecio() {
        return paqTuristico.getPrecio();
    }

    @Override
    public int getDuracion() {
        return paqTuristico.getDuracion();
    }

    @Override
    public List<String> getDescripcion() {
        return paqTuristico.getDescripcion();
    }

}
