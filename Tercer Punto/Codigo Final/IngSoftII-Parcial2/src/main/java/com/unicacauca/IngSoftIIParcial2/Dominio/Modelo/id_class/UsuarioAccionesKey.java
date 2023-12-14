package com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioAccionesKey implements Serializable {


    private Usuario usuario;
    private Accion acciones;

    public UsuarioAccionesKey(Usuario usuario, Accion acciones) {
        this.usuario = usuario;
        this.acciones = acciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        UsuarioAccionesKey that = (UsuarioAccionesKey) o;

        if(!Objects.equals(usuario, that.usuario)) return false;
        return Objects.equals(acciones, that.acciones);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(usuario);
        result = 31 * result + Objects.hash(acciones);
        return result;
    }

}
