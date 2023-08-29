/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author INGESIS
 */
public class ViajeIncentivo extends Viaje {

    private String empresa;

    public ViajeIncentivo(String empresa, String origen, int costo, Date fechaSalida, Date fechaLlegada, String destino) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
        this.empresa = empresa;
    }

    @Override
    public String descripcion() {
        return "viaje incentivo";
    }

    @Override
    public String cualquierMetodo2() {
        return "metodo implementado en ViajeIncentivo";
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
