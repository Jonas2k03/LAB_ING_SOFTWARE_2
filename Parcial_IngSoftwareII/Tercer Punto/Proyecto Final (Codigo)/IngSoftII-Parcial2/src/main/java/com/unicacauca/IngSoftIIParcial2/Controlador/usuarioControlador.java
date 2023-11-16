package com.unicacauca.IngSoftIIParcial2.Controlador;

import com.unicacauca.IngSoftIIParcial2.Modelo.id_class.UsuarioAcciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unicacauca.IngSoftIIParcial2.Modelo.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class usuarioControlador {
    private final usuarioServicio usuarioServicio;
    private  accionControlador accionControlador;

    @Autowired
    public usuarioControlador(usuarioServicio usuarioServicio, accionControlador accionControlador) {
        this.usuarioServicio = usuarioServicio;
        this.accionControlador = accionControlador;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return this.usuarioServicio.getUsuarios();
    }

    @PostMapping("/agregarUsuario")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario user) {
        return this.usuarioServicio.addUsuario(user);
    }

    @PostMapping("/agregarUsuarioLista")
    public ResponseEntity<String> addUsuarioLista(@RequestBody List<Usuario> usuarios) {
        return this.usuarioServicio.addUsuariosLista(usuarios);
    }

    @DeleteMapping("/eliminarUsuario/{nombreUsuario}/{contrasena}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena){
        return this.usuarioServicio.deleteUsuario(nombreUsuario, contrasena);
    }
    @PostMapping("/agregarAccionUsuario/{nombreUsuario}/{contrasena}/{nombreAccion}/{cantidadAcciones}/{umbralInferior}/{umbralSuperior}")
    public ResponseEntity<String> agregarAccionUsuario(
            @PathVariable String nombreUsuario,
            @PathVariable String contrasena,
            @PathVariable String nombreAccion,
            @PathVariable Long cantidadAcciones,
            @PathVariable Long umbralInferior,
            @PathVariable Long umbralSuperior) {


        Long usuarioId = usuarioServicio.obtenerIdUsuario(nombreUsuario, contrasena);
        Long accionId = accionControlador.obtenerIdAccion(nombreAccion);

        if (umbralInferior > umbralSuperior) {
            return ResponseEntity.status(400).body("Umbral Inferior no puede ser mayor a Umbral Superior");
        }
        if (usuarioId == -1L) {
            return ResponseEntity.status(404).body("Usuario no Registrado");
        }
        if (accionId == -1L) {
            return ResponseEntity.status(404).body("Accion no Registrada");
        }

        return this.usuarioServicio.agregarAccionUsuario(usuarioId, accionId, cantidadAcciones, umbralInferior, umbralSuperior);
    }
    @GetMapping("/obtenerAccionesUsuario/{nombreUsuario}/{contrasena}")
    public List<UsuarioAcciones> obtenerAccionesUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena){
        Long usuarioId = usuarioServicio.obtenerIdUsuario(nombreUsuario, contrasena);
        return this.usuarioServicio.obtenerUsuarioAcciones(usuarioId);
    }

    @DeleteMapping("/eliminarAccionUsuario/{nombreUsuario}/{contrasena}/{nombreAccion}")
    public ResponseEntity<String> eliminarAccionUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena, @PathVariable String nombreAccion){
        return this.usuarioServicio.eliminarAccionUsuario(nombreUsuario, contrasena, nombreAccion);
    }

}
