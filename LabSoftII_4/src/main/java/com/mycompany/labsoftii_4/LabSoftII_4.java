package com.mycompany.labsoftii_4;

/**
 *
 * @author Juan Carlos Fernandez Cuetia (jcfernandezc@unicauca.edu.co)
 * @author Jonathan Felipe Hurtado Díaz (jfhurtadod@unicauca.edu.co)
 */
public class LabSoftII_4 {

    public static void main(String[] args) {

        DBConnection db1 = DBConnection.getDBInstance();
        db1.setNameDB("Nomina");
        db1.connect();
        System.out.println("La base de datos en la instancia 1 es :" + db1.getNameDB() + " y su referencia es: " + db1.hashCode());
        db1.disconnect();

        DBConnection db2 = DBConnection.getDBInstance();
        db2.setNameDB("Empleados");
        db2.connect();
        System.out.println("La base de datos en la instancia 2 es :" + db2.getNameDB() + " y su referencia es: " + db2.hashCode());
        db2.disconnect();

    }
}
