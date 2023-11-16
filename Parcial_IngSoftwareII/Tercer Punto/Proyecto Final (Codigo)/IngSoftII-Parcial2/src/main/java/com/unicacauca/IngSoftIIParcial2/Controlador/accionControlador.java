package com.unicacauca.IngSoftIIParcial2.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unicacauca.IngSoftIIParcial2.Modelo.*;

import java.util.List;

@RestController
@RequestMapping(path = "/accion")
public class accionControlador {
    private final accionServicio accionServicio;

    @Autowired
    public accionControlador(accionServicio accionServicio){
        this.accionServicio = accionServicio;
    }

    @GetMapping
    public List<Accion> getAcciones(){
        return this.accionServicio.getAcciones();
    }

    @PostMapping("/agregarAccion")
    public ResponseEntity<String> addAccion(@RequestBody Accion accion){
        return this.accionServicio.addAccion(accion);
    }
    @DeleteMapping("/eliminarAccion/{nombreAccion}")
    public ResponseEntity<String> eliminarAccion(@PathVariable String nombreAccion){
        return this.accionServicio.deleteAccion(nombreAccion);
    }

    public Long obtenerIdAccion(String nombreAccion){
        return this.accionServicio.obtenerIdAccion(nombreAccion);
    }

    @PatchMapping("/actualizarAccion/{nombreAccion}/{valorAccion}")
    public ResponseEntity<String> actualizarAccion(@PathVariable String nombreAccion, @PathVariable int valorAccion){
        return this.accionServicio.actualizarPrecioAccion(nombreAccion, valorAccion);
    }
}
