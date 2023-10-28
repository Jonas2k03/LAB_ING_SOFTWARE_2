/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author Juan Carlos Fernandez Cuetia (jcfernandezc@unicauca.edu.co)
 * @author Jonathan Felipe Hurtado Diaz (jfhurtadod@unicauca.edu.co)
 * 
 */
public class LeafPack implements iPack{

    private String vueloLlegada;
    private String hotel;
    private List<String> city_tour;
    private String planAlimentacion;
    private String fechaCheckIn;
    private String fechaCheckOut;
    private String name;

    public LeafPack(String vueloLlegada, String hotel, List<String> city_tour, String planAlimentacion, String fechaCheckIn, String fechaCheckOut, String name) {
        this.vueloLlegada = vueloLlegada;
        this.hotel = hotel;
        this.city_tour = city_tour;
        this.planAlimentacion = planAlimentacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.name = name;
    }
    

    
    
    
    @Override
    public void operation() {
        System.out.println("\nNombre de la ciudad: " + name);
        System.out.println(hotel);
        System.out.println("Vuelo de llegada: " + vueloLlegada);
        System.out.println("City tour: ");
        for (String list : city_tour) {
            
            System.out.println("\t"+ list);
        }
        
    }

    public String getVueloLlegada() {
        return vueloLlegada;
    }

    public void setVueloLlegada(String vueloLlegada) {
        this.vueloLlegada = vueloLlegada;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public List<String> getCity_tour() {
        return city_tour;
    }

    public void setCity_tour(List<String> city_tour) {
        this.city_tour = city_tour;
    }

    
    
    
    
    
    
}
