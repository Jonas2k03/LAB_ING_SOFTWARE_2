/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package laboratorio7.labsoft7;

import Model.CompoundPack;
import Model.iPack;


/**
 *
 * @author Juan Carlos Fernandez Cuetia (jcfernandezc@unicauca.edu.co)
 * @author Jonathan Felipe Hurtado Diaz (jfhurtadod@unicauca.edu.co)
 * 
 */
public class Labsoft7 {

    public static void main(String[] args) {
        //Crear un paquete base al cuál se le agragarán sus respectivos hijos. 
        CompoundPack paqBase = new CompoundPack("Paquete Base");

        //EJEMPLO PARA UN PAQUETE AMERICANO.
        CompoundPack America = new CompoundPack("America");
        CompoundPack usa = new CompoundPack("USA");
        usa.addChild(new Cities().NewYork());
        America.addChild(usa);
        paqBase.addChild(America);
        
        //EJEMPLO PARA UN PAQUETE EUROPEO 
        
        CompoundPack Europa = new CompoundPack("Europa");
        CompoundPack Francia = new CompoundPack("Francia");
        Francia.addChild(new Cities().Paris());
        Europa.addChild(Francia);        
        paqBase.addChild(Europa);
        
        //EJEMPLO PARA UN PAQUETE ASIATICO 
        
        CompoundPack Asia = new CompoundPack("Asia");
        CompoundPack Japon = new CompoundPack("Japon");
        
        Japon.addChild(new Cities().Kyoto());
        Asia.addChild(Japon);
        
        paqBase.addChild(Asia);

        //Imprimir la información del paquete obtenido. 
        printContents(paqBase);

    }

    public static void printContents(iPack pack) {
        pack.operation();
        if (pack instanceof CompoundPack) {
            for (iPack pcn : ((CompoundPack) pack).getChildren()) {
                printContents(pcn);
            }
        }
    }

}
