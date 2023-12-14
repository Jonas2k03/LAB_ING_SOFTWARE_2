package com.unicacauca.IngSoftIIParcial2.Infraestructura.controlador;

import com.unicacauca.IngSoftIIParcial2.Aplicacion.Service.DomainAccionService;
import com.unicacauca.IngSoftIIParcial2.Aplicacion.Service.domainUsuarioService;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAcciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class usuarioControlador {
    private final DomainAccionService domainAccionService;
    private final domainUsuarioService _domainUsuarioService;

    @Autowired
    public usuarioControlador(domainUsuarioService _domainUsuarioService, DomainAccionService domainAccionService) {
        this._domainUsuarioService = _domainUsuarioService;
        this.domainAccionService = domainAccionService;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return this._domainUsuarioService.getUsuarios();
    }

    @PostMapping("/agregarUsuario")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario user) {
        return this._domainUsuarioService.addUsuario(user);
    }

    @PostMapping("/agregarUsuarioLista")
    public ResponseEntity<String> addUsuarioLista(@RequestBody List<Usuario> usuarios) {
        return this._domainUsuarioService.addUsuariosLista(usuarios);
    }

    @DeleteMapping("/eliminarUsuario/{nombreUsuario}/{contrasena}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena){
        return this._domainUsuarioService.deleteUsuario(nombreUsuario, contrasena);
    }
    @PostMapping("/agregarAccionUsuario/{nombreUsuario}/{contrasena}/{nombreAccion}/{cantidadAcciones}/{umbralInferior}/{umbralSuperior}")
    public ResponseEntity<String> agregarAccionUsuario(
            @PathVariable String nombreUsuario,
            @PathVariable String contrasena,
            @PathVariable String nombreAccion,
            @PathVariable Long cantidadAcciones,
            @PathVariable Long umbralInferior,
            @PathVariable Long umbralSuperior) {


        Long usuarioId = _domainUsuarioService.obtenerIdUsuario(nombreUsuario, contrasena);
        Long accionId = domainAccionService.obtenerIdAccion(nombreAccion);

        if (umbralInferior > umbralSuperior) {
            return ResponseEntity.status(400).body("Umbral Inferior no puede ser mayor a Umbral Superior");
        }
        if (usuarioId == -1L) {
            return ResponseEntity.status(404).body("Usuario no Registrado");
        }
        if (accionId == -1L) {
            return ResponseEntity.status(404).body("Accion no Registrada");
        }

        return this._domainUsuarioService.agregarAccionUsuario(usuarioId, accionId, cantidadAcciones, umbralInferior, umbralSuperior);
    }
    @GetMapping("/obtenerAccionesUsuario/{nombreUsuario}/{contrasena}")
    public List<UsuarioAcciones> obtenerAccionesUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena){
        Long usuarioId = _domainUsuarioService.obtenerIdUsuario(nombreUsuario, contrasena);
        return this._domainUsuarioService.obtenerUsuarioAcciones(usuarioId);
    }

    @DeleteMapping("/eliminarAccionUsuario/{nombreUsuario}/{contrasena}/{nombreAccion}")
    public ResponseEntity<String> eliminarAccionUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena, @PathVariable String nombreAccion){
        return this._domainUsuarioService.eliminarAccionUsuario(nombreUsuario, contrasena, nombreAccion);
    }

}
