package com.unicacauca.IngSoftIIParcial2.Dominio.Puerto;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iAccionesRepository extends JpaRepository<Accion, Long> {
    Optional<Accion> findByNombreAccion(String nombreAccion);

}
