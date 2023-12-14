package com.unicacauca.IngSoftIIParcial2;

import com.unicacauca.IngSoftIIParcial2.Aplicacion.Service.DomainAccionService;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iAccionesRepository;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iCrudUsuarioAcciones;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainAccionServiceTest {

    @Mock
    private iAccionesRepository accionesRepository;

    @Mock
    private iCrudUsuarioAcciones crudUsuarioAcciones;

    @InjectMocks
    private DomainAccionService domainAccionService;

    @Test
    void addAccionTest() {

        Accion accion = new Accion("AccionTest", 100, 90);
        when(accionesRepository.findByNombreAccion(accion.getNombreAccion())).thenReturn(Optional.empty());

        ResponseEntity<String> response = domainAccionService.addAccion(accion);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Accion agregada", response.getBody());

        verify(accionesRepository, times(1)).findByNombreAccion(accion.getNombreAccion());
        verify(accionesRepository, times(1)).save(accion);
    }

    @Test
    void deleteAccionTest() {
        String nombreAccion = "AccionTest";
        Accion accion = new Accion(nombreAccion, 100, 90);


        when(accionesRepository.findByNombreAccion(nombreAccion)).thenReturn(Optional.of(accion));
        when(crudUsuarioAcciones.findByAcciones(accion)).thenReturn(Collections.emptyList());

        ResponseEntity<String> response = domainAccionService.deleteAccion(nombreAccion);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Accion eliminada", response.getBody());

        verify(accionesRepository, times(1)).delete(accion);
    }
}
