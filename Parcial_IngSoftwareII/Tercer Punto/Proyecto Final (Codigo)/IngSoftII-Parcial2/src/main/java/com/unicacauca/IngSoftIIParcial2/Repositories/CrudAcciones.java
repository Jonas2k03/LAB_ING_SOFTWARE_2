package com.unicacauca.IngSoftIIParcial2.Repositories;

import com.unicacauca.IngSoftIIParcial2.Modelo.Accion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudAcciones extends JpaRepository<Accion, Long> {
    Optional<Accion> findByNombreAccion(String nombreAccion);

}
