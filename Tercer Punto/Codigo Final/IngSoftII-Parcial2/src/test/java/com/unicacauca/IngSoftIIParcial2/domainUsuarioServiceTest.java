package com.unicacauca.IngSoftIIParcial2;

import com.unicacauca.IngSoftIIParcial2.Aplicacion.Service.DomainAccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iAccionesRepository;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iCrudUsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iUsuariosRepository;
import com.unicacauca.IngSoftIIParcial2.Aplicacion.Service.domainUsuarioService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class domainUsuarioServiceTest {

    @Mock
    private iUsuariosRepository usuariosRepository;

    @Mock
    private iAccionesRepository accionesRepository;

    @Mock
    private iCrudUsuarioAcciones crudUsuarioAcciones;

    @Mock
    private DomainAccionService domainAccionService;

    private domainUsuarioService _domainUsuarioService;

    @BeforeEach
    void setUp() {
        _domainUsuarioService = new domainUsuarioService(usuariosRepository, crudUsuarioAcciones, accionesRepository, domainAccionService);
    }

    @Test
    void agregarAccionUsuarioTest() {
        Long usuarioId = 1L;
        Long accionId = 1L;
        Long cantidadAcciones = 10L;
        Long umbralInferior = 90L;
        Long umbralSuperior = 110L;

        Usuario usuario = new Usuario(usuarioId);
        usuario.setCartera(1000L);
        Accion accion = new Accion("AccionTest", 100, 90);

        when(usuariosRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(accionesRepository.findById(accionId)).thenReturn(Optional.of(accion));

        when(crudUsuarioAcciones.findByUsuarioAndAcciones(usuario, accion)).thenReturn(null);

        ResponseEntity<String> response = _domainUsuarioService.agregarAccionUsuario(usuarioId, accionId, cantidadAcciones, umbralInferior, umbralSuperior);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("Accion Comprada"));

        verify(crudUsuarioAcciones, times(1)).save(any(UsuarioAcciones.class));
    }
}

