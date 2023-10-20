/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsoftii_6;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Fernandez Cuetia
 * @author Jonathan Felipe Hurtado Diaz
 */
public class PaqueteBasico implements iPaqueteTuristico {
    private final double precio = 7000;
    private final int duracion = 5;
    private final List<String> descripcion;
    
     public PaqueteBasico() {
        descripcion = new ArrayList<>();
        descripcion.add("tiquetes aéreos");
        descripcion.add("hotel en habitación estándar");
        descripcion.add("alimentación");
        descripcion.add("vuelta a la isla");
        descripcion.add("recepción con lei hawaiano y camiseta de Millonarios");
        descripcion.add("");
    }

    @Override
    public double getPrecio() {
       return precio;
    }

    @Override
    public int getDuracion() {
        return duracion;
    }

    @Override
    public List<String> getDescripcion() {
        return descripcion;
    }
    
}
