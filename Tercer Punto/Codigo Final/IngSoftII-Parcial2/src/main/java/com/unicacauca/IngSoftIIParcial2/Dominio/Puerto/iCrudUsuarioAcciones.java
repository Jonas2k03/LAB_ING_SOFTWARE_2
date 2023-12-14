package com.unicacauca.IngSoftIIParcial2.Dominio.Puerto;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAccionesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface iCrudUsuarioAcciones extends JpaRepository<UsuarioAcciones, UsuarioAccionesKey> {

    List<UsuarioAcciones> findByUsuario(Usuario usuario);

    List<UsuarioAcciones> findByAcciones(Accion accion);

    UsuarioAcciones findByUsuarioAndAcciones(Usuario usuario, Accion accion);

    @Modifying
    @Transactional
    @Query("DELETE FROM UsuarioAcciones u WHERE u.id.usuario.usuarioId = ?1 AND u.id.acciones.accionId = ?2")
    void deleteByUsuarioAndAcciones(@Param("usuarioId") Long usuarioId, @Param("accionId") Long accionId);

}
