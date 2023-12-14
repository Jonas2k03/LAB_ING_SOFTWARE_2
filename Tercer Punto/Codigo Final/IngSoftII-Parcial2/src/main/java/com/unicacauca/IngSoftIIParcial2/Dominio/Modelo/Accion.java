package com.unicacauca.IngSoftIIParcial2.Dominio.Modelo;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAcciones;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accion")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accionId;
    @Column(unique = true)
    private String nombreAccion;
    private int precioActual;
    private int precioAnterior;

    public Accion(String nombreAccion, int precioActual, int precioAnterior) {
        this.nombreAccion = nombreAccion;
        this.precioActual = precioActual;
        this.precioAnterior = precioAnterior;
    }

    public Accion(){

    }

    @OneToMany(mappedBy = "acciones", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UsuarioAcciones> usuarioAcciones = new HashSet<>();

    public Accion(Long accionId) {
        this.accionId = accionId;
    }


    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public int getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(int precioActual) {
        this.precioActual = precioActual;
    }

    public int getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(int precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public Long getAccionId() {
        return accionId;
    }

    public void setAccionId(Long accionId) {
        this.accionId = accionId;
    }
}
