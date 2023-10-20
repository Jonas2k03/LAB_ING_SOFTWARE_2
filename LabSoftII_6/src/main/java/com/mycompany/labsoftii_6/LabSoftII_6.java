/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.labsoftii_6;

import java.util.Scanner;

/**
 *
 * @author Juan Carlos Fernandez Cuetia
 * @author Jonathan Felipe Hurtado Diaz
 */
public class LabSoftII_6 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iPaqueteTuristico paqBase = new PaqueteBasico();

        int opcion;

        do {

            menuOpciones();
            System.out.print("\n\tDigite una opcion: ");
            opcion = leerEntero(scanner);

            switch (opcion) {

                case 1:
                    cotizarIndividual(paqBase);
                    break;

                case 2:
                    cotizarPorFamilia(paqBase);
                    break;

                case 3:
                    paqBase = new paqPearlHarbor(paqBase);
                    System.out.println("\n\t¡Paquete Pearl Harbor añadido!\n");
                    break;

                case 4:
                    paqBase = new paqAmazingHawaii(paqBase);
                    System.out.println("\n\t¡Paquete Amazing Hawaii añadido!\n");
                    break;

                case 5:
                    paqBase = new paqNature(paqBase);
                    System.out.println("\n\t¡Paquete Nature añadido!\n");
                    break;

                default:
                    System.out.println("\n\tPor favor, digite una opción correcta.\n");
                    break;

            }

        } while (opcion != 6);

    }

    public static void menuOpciones() {
        System.out.println("\n~~~~~~~~~~MENU DE OPCIONES~~~~~~~~~~");
        System.out.println("\t1. Cotizar el plan actual de forma individual");
        System.out.println("\t2. Cotizar el plan actual por familia");
        System.out.println("\t3. Añadir al paquete básico el paquete Pearl Harbor");
        System.out.println("\t4. Añadir al paquete básico el paquete AmazingHawaii");
        System.out.println("\t5. Añadir al paquete básico el paquete Nature");
        System.out.println("\t6. Salir");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                // Lee el entero desde la entrada estándar
                int numero = scanner.nextInt();
                System.out.println("\t\t");
                scanner.nextLine(); // Consumir el salto de línea

                return numero;
            } catch (java.util.InputMismatchException e) {
                System.out.print("\t\tEntrada no válida. Ingresa un número entero: ");
                scanner.nextLine(); // Consumir la entrada incorrecta
            }
        }
    }

    public static void cotizarPorFamilia(iPaqueteTuristico pt) {
        System.out.print("\t\tDigite la cantidad de personas en la familia: ");
        int cantidadPersonas = leerEntero(scanner);
        int precioTotal = (int) (pt.getPrecio() * cantidadPersonas);
         System.out.print("\t\t¿Desea moficar la duracion del paquete?\t(1: SI)\t(2:NO):\t");
        int opcion = leerEntero(scanner);
            switch (opcion) {
                case 1:
                    int duracionModificada = modificarDuracion(pt);
                    precioTotal = (int) (precioTotal + (precioTotal / duracionModificada));
                    System.out.println("\t\tCotización por familia de " + cantidadPersonas + " personas:");
                    System.out.println("\t\tPrecio total por familia: USD $" + precioTotal);
                    System.out.println("\t\tDuración total por familia: " + duracionModificada + " días");
                    System.out.println();
                    System.out.println("\t\tDescripcion: ");
                    for (String descripcion : pt.getDescripcion()) {
                        System.out.println("\t\t\t" + descripcion);
                    }

                    break;
                case 2:
                    System.out.println("\t\tCotización por familia de " + cantidadPersonas + " personas:");
                    System.out.println("\t\tPrecio total por familia: USD $" + precioTotal);
                    System.out.println("\t\tDuración total por familia: " + pt.getDuracion() + " días");
                    System.out.println("\t\tDescripcion: ");
                    for (String descripcion : pt.getDescripcion()) {
                        System.out.println("\t\t\t" + descripcion);
                    }
                    System.out.println();
                    break;
                default:
                    System.out.print("\t\tEscoja una opcion entre 1 y 2. ");

            }
            

    }
    
    public static int modificarDuracion(iPaqueteTuristico pt) {
        System.out.print("\t\tDigite los dias a adicionar al plan: ");
        int nuevaDuracion = leerEntero(scanner);
        nuevaDuracion = nuevaDuracion + pt.getDuracion();
        System.out.println("\n\t\tLa duracion ha sido modificada de " + pt.getDuracion() + " a " + nuevaDuracion + " dias.\n");
        return nuevaDuracion;
    }
    
    public static void cotizarIndividual(iPaqueteTuristico pt) {
         System.out.print("\t\t¿Desea moficar la duracion del paquete?\t(1: SI)\t(2:NO):\t");
        int opcion = leerEntero(scanner);


            switch (opcion) {
                case 1:
                    
                    int duracionModificada = modificarDuracion(pt);
                    int precioTotal = (int) (pt.getPrecio() + ( pt.getPrecio() / duracionModificada));
                   
                    System.out.println("\t\tCotizacion individual: ");
                    System.out.println("\t\tPrecio total: USD $" + precioTotal);
                    System.out.println("\t\tDuracion total: " + duracionModificada + " días.");
                    System.out.println("\t\tDescripcion: ");
                    for (String descripcion : pt.getDescripcion()) {
                        System.out.println("\t\t\t" + descripcion);
                    }

                    break;
                case 2:
                    System.out.println("\t\tCotizacion individual: ");
                    System.out.println("\t\tPrecio total: USD $" + pt.getPrecio());
                    System.out.println("\t\tDuracion total: " + pt.getDuracion() + " días.");
                    System.out.println("\t\tDescripcion: ");
                    for (String descripcion : pt.getDescripcion()) {
                        System.out.println("\t\t\t" + descripcion);
                    }
                    break;
                default:
                    System.out.print("\t\tEscoja una opcion entre 1 y 2. ");

            }
    }

}
