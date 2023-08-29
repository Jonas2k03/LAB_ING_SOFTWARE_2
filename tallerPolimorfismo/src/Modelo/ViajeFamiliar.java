/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Juan Carlos Fernandez Cuetia <jcfernandezc@unicauca.edu.co>
 * @author Jonathan Felipe Hurtado Diaz <jfhurtadod@unicauca.edu.co>
 */
public class ViajeFamiliar extends Viaje {

    private int familia;

    public ViajeFamiliar(String origen, String destino, int familia, Date fechaSalida, Date fechaLlegada, int costo) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
        this.familia = familia;
    }

    @Override
    public String descripcion() {
        return "viaje familiar";
    }

    @Override
    public String cualquierMetodo2() {
        return "metodo implementado en ViajeFamiliar";
    }

    public int getFamilia() {
        return familia;
    }

    public void setFamilia(int familia) {
        this.familia = familia;
    }

}
