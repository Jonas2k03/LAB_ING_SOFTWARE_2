package com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(UsuarioAccionesKey.class)
@Table(name = "usuario_acciones")
public class UsuarioAcciones {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario usuario;

    @Id
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accion", nullable = false)
    private Accion acciones;

    @Column(name = "cantidad_acciones")
    private Long cantidadAcciones;

    @Column (name = "umbral_inferior")
    private Long umbralInferior;

    @Column (name = "umbral_superior")
    private Long umbralSuperior;

    @Column (name = "estado_Accion")
    private String estadoAccion;

    public UsuarioAcciones(Usuario usuario, Accion acciones, Long cantidadAcciones, Long umbralInferior, Long umbralSuperior) {
        this.usuario = usuario;
        this.acciones = acciones;
        this.cantidadAcciones = cantidadAcciones;
        this.umbralInferior = umbralInferior;
        this.umbralSuperior = umbralSuperior;
        this.estadoAccion = "Dentro de los umbrales";
    }

    public UsuarioAcciones(){
    }

    public Long getUmbralInferior() {
        return umbralInferior;
    }

    public Long getUmbralSuperior() {
        return umbralSuperior;
    }

    public void setEstadoAccion(String venta) {
        this.estadoAccion = venta;
    }

    public String getEstadoAccion() { return estadoAccion; }

    public Long getCantidadAcciones() {
        return cantidadAcciones;
    }

    public void setCantidadAcciones(Long cantidadAcciones) {
        this.cantidadAcciones = cantidadAcciones;
    }
}
