package com.unicacauca.IngSoftIIParcial2.Infraestructura.controlador;

import com.unicacauca.IngSoftIIParcial2.Aplicacion.Service.DomainAccionService;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/accion")
public class accionControlador {

    private final DomainAccionService domainAccionService;

    @Autowired
    public accionControlador(DomainAccionService domainAccionService){
        this.domainAccionService = domainAccionService;
    }

    @GetMapping
    public List<Accion> getAcciones(){
        return this.domainAccionService.getAcciones();
    }

    @PostMapping("/agregarAccion")
    public ResponseEntity<String> addAccion(@RequestBody Accion accion){
        return this.domainAccionService.addAccion(accion);
    }
    @DeleteMapping("/eliminarAccion/{nombreAccion}")
    public ResponseEntity<String> eliminarAccion(@PathVariable String nombreAccion){
        return this.domainAccionService.deleteAccion(nombreAccion);
    }

    public Long obtenerIdAccion(String nombreAccion){
        return this.domainAccionService.obtenerIdAccion(nombreAccion);
    }

    @PatchMapping("/actualizarAccion/{nombreAccion}/{valorAccion}")
    public ResponseEntity<String> actualizarAccion(@PathVariable String nombreAccion, @PathVariable int valorAccion){
        return this.domainAccionService.actualizarPrecioAccion(nombreAccion, valorAccion);
    }
}
