package com.unicacauca.IngSoftIIParcial2.Modelo;

import com.unicacauca.IngSoftIIParcial2.Modelo.id_class.UsuarioAcciones;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    @Column(unique = true)
    @NonNull
    private String nombreUsuario;

    @NonNull
    private String contrasena;

    @Column(name = "cartera")
    @NonNull
    private Long cartera;

    @Column(name = "cartera_original")
    private Long carteraOriginal;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsuarioAcciones> usuarioAcciones = new HashSet<>();

    public Usuario(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre_usuario() {
        return nombreUsuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombreUsuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId= usuarioId;
    }

    public Long getCartera() {
        return cartera;
    }

    public void setCartera(Long cartera) {
        this.cartera = cartera;
    }

    public Long getCarteraOriginal() {
        return carteraOriginal;
    }

    public void setCarteraOriginal(Long carteraOriginal) {
        this.carteraOriginal = carteraOriginal;
    }
}
