package com.unicacauca.IngSoftIIParcial2.Controlador;

import com.unicacauca.IngSoftIIParcial2.Modelo.*;
import com.unicacauca.IngSoftIIParcial2.Repositories.CrudAcciones;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class accionControladorTest {

    @Autowired
    accionServicio accionServicio;

    @Autowired
    CrudAcciones crudAccionesMock = Mockito.mock(CrudAcciones.class);

    @Autowired
    accionControlador accionControlador = new accionControlador(accionServicio);

    @BeforeEach
    void setUp() {
        accionServicio = new accionServicio(crudAccionesMock, null);
        accionControlador = new accionControlador(accionServicio);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAcciones() {
        List<Accion> listaAccionesMockito = new ArrayList<>();
        Accion accionMockito = new Accion("AccionMockito", 100, 100);
        listaAccionesMockito.add(accionMockito);
        Mockito.when(accionServicio.getAcciones()).thenReturn(listaAccionesMockito);
        assertEquals(1, accionControlador.getAcciones().size(), "No se obtuvieron las acciones");

    }
    @Test
    void addAccion(){
        List<Accion> listaAccionesMockito = new ArrayList<>();
        Accion accionMockito = new Accion("NuevaAccion", 100, 100);
        listaAccionesMockito.add(accionMockito);
        Mockito.when(accionServicio.getAcciones()).thenReturn(listaAccionesMockito);
        ResponseEntity<String> respuesta = accionControlador.addAccion(accionMockito);
        assertEquals("Accion agregada", respuesta.getBody(), "No se agregó la acción");
        assertEquals(1, accionServicio.getAcciones().size(), "No se agregó la acción");
    }

}