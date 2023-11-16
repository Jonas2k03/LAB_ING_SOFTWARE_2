package com.unicacauca.IngSoftIIParcial2.Modelo;

import com.unicacauca.IngSoftIIParcial2.Modelo.id_class.UsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Modelo.id_class.UsuarioAccionesKey;
import com.unicacauca.IngSoftIIParcial2.Repositories.CrudUsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Repositories.CrudUsuarios;
import com.unicacauca.IngSoftIIParcial2.Repositories.CrudAcciones;
import com.unicacauca.IngSoftIIParcial2.Repositories.iObservador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class usuarioServicio implements iObservador {

    private final CrudUsuarios crudUsuarios;
    private final CrudUsuarioAcciones crudUsuarioAcciones;

    private final CrudAcciones crudAcciones;

    private final accionServicio accionServicio;

    @Autowired
    public usuarioServicio(CrudUsuarios crudUsuarios, CrudUsuarioAcciones crudUsuarioAcciones, CrudAcciones crudAcciones, accionServicio accionServicio) {
        this.crudUsuarios = crudUsuarios;
        this.crudUsuarioAcciones = crudUsuarioAcciones;
        this.crudAcciones = crudAcciones;
        this.accionServicio = accionServicio;
        this.accionServicio.asociarObservador(this);
    }

    public List<Usuario> getUsuarios(){
        return this.crudUsuarios.findAll();
    }

    public ResponseEntity<String> addUsuario(Usuario user) {
        if (user.getCartera() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cartera no debe ser menor a 0");
        }
        user.setCarteraOriginal(user.getCartera());
        Optional<Usuario> respuesta = crudUsuarios.findByUsuarioId(user.getUsuarioId());
        if(respuesta.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        crudUsuarios.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario agregado");
    }

    public ResponseEntity<String> addUsuariosLista(List<Usuario> usuarios) {
        for (Usuario user : usuarios) {
            if (user.getCartera() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cartera no debe ser menor a 0");
            }

            Optional<Usuario> respuesta = crudUsuarios.findByUsuarioId(user.getUsuarioId());
            if (respuesta.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario '" + user.getNombre_usuario() + "' ya existe");
            }

            user.setCarteraOriginal(user.getCartera());
            crudUsuarios.save(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuarios agregados");
    }


    public ResponseEntity<String> deleteUsuario(String nombreUsuario, String contrasena){
        Long usuarioId = obtenerIdUsuario(nombreUsuario, contrasena);
        Optional<Usuario> respuesta = crudUsuarios.findByUsuarioId(usuarioId);
        if(respuesta.isPresent()){
            crudUsuarios.delete(respuesta.get());
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    public Long obtenerIdUsuario(String nombreUsuario, String contrasena){
        Optional<Usuario> respuesta = crudUsuarios.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        if(respuesta.isPresent()){
            return respuesta.get().getUsuarioId();
        }
        return -1L;
    }

    public ResponseEntity<String> agregarAccionUsuario(Long usuarioId, Long accionId, Long cantidadAcciones, Long umbralInferior, Long umbralSuperior){
        Optional<Usuario> usuarioOptional = crudUsuarios.findById(usuarioId);
        Optional<Accion> accionOptional = crudAcciones.findById(accionId);

        if (usuarioOptional.isPresent() && accionOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Accion accion = accionOptional.get();

            if (umbralInferior > umbralSuperior) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Umbral Inferior no puede ser mayor a Umbral Superior");
            }

            if (umbralInferior > accion.getPrecioActual() || umbralSuperior < accion.getPrecioActual()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La acción no se encuentra en el rango de los umbrales");
            }
            if (cantidadAcciones <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cantidad de acciones debe ser mayor a 0");
            }

            UsuarioAcciones userTemporal = crudUsuarioAcciones.findByUsuarioAndAcciones(usuario, accion);
            Long presupuesto = presupuestoCartera(usuario, accion, cantidadAcciones);

            if (presupuesto == -1L) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No tiene presupuesto suficiente para comprar la acción");
            }

            if (userTemporal != null) {
                usuario.setCartera(presupuesto);
                crudUsuarios.save(usuario);
                Long suma = userTemporal.getCantidadAcciones() + cantidadAcciones;
                userTemporal.setCantidadAcciones(suma);
                crudUsuarioAcciones.save(userTemporal);

                return ResponseEntity.status(HttpStatus.CREATED).body("Se agregaron " + cantidadAcciones + " a la acción " + accion.getNombreAccion());
            } else {
                usuario.setCartera(presupuesto);
                crudUsuarios.save(usuario);
                usuario.setCartera(presupuesto);
                crudUsuarios.save(usuario);
                crudUsuarioAcciones.save(new UsuarioAcciones(usuario, accion, cantidadAcciones, umbralInferior, umbralSuperior));

                return ResponseEntity.status(HttpStatus.CREATED).body("Accion Comprada");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o Accion no encontrados");
        }
    }
    public Long presupuestoCartera(Usuario usuario, Accion accion, Long cantidadAcciones){
        if (usuario.getCartera() <= 0 || usuario.getCartera() < accion.getPrecioActual() * cantidadAcciones){
            return -1L;
        }
        return usuario.getCartera() - (accion.getPrecioActual() * cantidadAcciones);
    }
    public List<UsuarioAcciones> obtenerUsuarioAcciones(Long usuarioId) {
        Optional<Usuario> usuarioOptional = crudUsuarios.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<UsuarioAcciones> usuarioAccionesList = crudUsuarioAcciones.findByUsuario(usuario);

            if (usuarioAccionesList != null && !usuarioAccionesList.isEmpty()) {
                return usuarioAccionesList;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    public Long valorGanancias(Usuario usuario, Accion accion, UsuarioAcciones usuarioAcciones){
        Long ganancias = (accion.getPrecioActual() - accion.getPrecioAnterior()) * usuarioAcciones.getCantidadAcciones();
        return usuario.getCartera() + ganancias;
    }
    @Transactional
    public ResponseEntity<String> eliminarAccionUsuario(String nombreUsuario, String contrasena, String nombreAccion) {
        Long usuarioId = obtenerIdUsuario(nombreUsuario, contrasena);
        Long accionId = accionServicio.obtenerIdAccion(nombreAccion);

        Optional<Usuario> usuarioOptional = crudUsuarios.findById(usuarioId);
        Optional<Accion> accionOptional = crudAcciones.findById(accionId);

        Usuario usuario = usuarioOptional.get();
        Accion accion = accionOptional.get();

        if (usuarioId == -1L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if (accionId == -1L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Accion no encontrada");
        }

        Optional<UsuarioAcciones> usuarioAccionesOptional = crudUsuarioAcciones.findById(new UsuarioAccionesKey(new Usuario(usuarioId), new Accion(accionId)));

        if (usuarioAccionesOptional.isPresent()) {
            if (valorGanancias(usuario, accion, usuarioAccionesOptional.get()) < 0) {
                usuario.setCartera(0L);
                usuario.setCarteraOriginal(0L);
                crudUsuarios.save(usuario);
            } else {
                Long variable = usuario.getCarteraOriginal() + valorGanancias(usuario, accion, usuarioAccionesOptional.get());
                usuario.setCarteraOriginal(variable);
                usuario.setCartera(variable);
                crudUsuarios.save(usuario);
            }
            crudUsuarioAcciones.deleteByUsuarioAndAcciones(usuarioId, accionId);
            return ResponseEntity.status(HttpStatus.OK).body("Accion eliminada para el usuario");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La accion no estaba asociada al usuario");
        }
    }

    @Override
    public void actualizar() {
        List<Accion> acciones = accionServicio.getAcciones();
        for (Accion accion : acciones) {
            List<UsuarioAcciones> usuarioAccionesList = crudUsuarioAcciones.findByAcciones(accion);
            if (usuarioAccionesList != null && !usuarioAccionesList.isEmpty()) {
                for (UsuarioAcciones usuarioAcciones : usuarioAccionesList) {
                    if (accion.getPrecioActual() <= usuarioAcciones.getUmbralInferior()) {
                        usuarioAcciones.setEstadoAccion("La accion " + accion.getNombreAccion() + " ha alcanzado el umbral inferior");
                        crudUsuarioAcciones.save(usuarioAcciones);
                    } else if (accion.getPrecioActual() >= usuarioAcciones.getUmbralSuperior()) {
                        usuarioAcciones.setEstadoAccion("La accion " + accion.getNombreAccion() + " ha alcanzado el umbral superior");
                        crudUsuarioAcciones.save(usuarioAcciones);
                    }

                    else {
                        usuarioAcciones.setEstadoAccion("Dentro de los umbrales");
                        crudUsuarioAcciones.save(usuarioAcciones);
                    }
                }
            }
        }
    }
}

