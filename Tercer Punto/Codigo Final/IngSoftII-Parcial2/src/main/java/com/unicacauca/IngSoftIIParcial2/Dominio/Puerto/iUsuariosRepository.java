package com.unicacauca.IngSoftIIParcial2.Dominio.Puerto;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface iUsuariosRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuarioId(Long usuarioId);
    Optional<Usuario> findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
}
