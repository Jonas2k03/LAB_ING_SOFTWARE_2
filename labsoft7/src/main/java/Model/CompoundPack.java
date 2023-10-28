/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Carlos Fernandez Cuetia (jcfernandezc@unicauca.edu.co)
 * @author Jonathan Felipe Hurtado Diaz (jfhurtadod@unicauca.edu.co)
 * 
 */
public  class  CompoundPack implements iPack {
    private List<iPack> children;
    private String namePack;

    public CompoundPack( String namePack) {
        this.namePack = namePack;
    }

  
    
    
    @Override
    public void operation() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~"+namePack+"~~~~~~~~~~~~~~~~~~~~~~");
        
    }
    
    public void addChild(iPack component) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(component);
    }
    
    public boolean removeChild(iPack component) {
        if (children == null) {
            return false;
        }
        children.remove(component);
        return true;
    }
    
    public List<iPack> getChildren() {
        return children;
    }

    public void setChildren(List<iPack> children) {
        this.children = children;
    }

    
}
