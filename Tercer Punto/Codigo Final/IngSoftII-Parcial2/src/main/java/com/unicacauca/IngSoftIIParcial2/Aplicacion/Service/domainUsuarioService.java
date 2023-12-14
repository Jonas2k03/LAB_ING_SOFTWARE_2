package com.unicacauca.IngSoftIIParcial2.Aplicacion.Service;

import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Accion;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.Usuario;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Dominio.Modelo.id_class.UsuarioAccionesKey;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iCrudUsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iUsuariosRepository;
import com.unicacauca.IngSoftIIParcial2.Dominio.Puerto.iAccionesRepository;
import com.unicacauca.IngSoftIIParcial2.Publisher;
import com.unicacauca.IngSoftIIParcial2.Repositories.iObservador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class domainUsuarioService implements iObservador {

    @Autowired
    private Publisher publisher;
    private static final Logger log = LoggerFactory.getLogger(Publisher.class);

    private final iUsuariosRepository iUsuariosRepository;
    private final iCrudUsuarioAcciones iCrudUsuarioAcciones;

    private final iAccionesRepository iAccionesRepository;

    private final DomainAccionService domainAccionService;

    @Autowired
    public domainUsuarioService(iUsuariosRepository iUsuariosRepository, iCrudUsuarioAcciones iCrudUsuarioAcciones, iAccionesRepository iAccionesRepository, DomainAccionService domainAccionService) {
        this.iUsuariosRepository = iUsuariosRepository;
        this.iCrudUsuarioAcciones = iCrudUsuarioAcciones;
        this.iAccionesRepository = iAccionesRepository;
        this.domainAccionService = domainAccionService;
        this.domainAccionService.asociarObservador(this);
    }

    public List<Usuario> getUsuarios(){
        return this.iUsuariosRepository.findAll();
    }

    public ResponseEntity<String> addUsuario(Usuario user) {
        if (user.getCartera() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cartera no debe ser menor a 0");
        }
        user.setCarteraOriginal(user.getCartera());
        Optional<Usuario> respuesta = iUsuariosRepository.findByUsuarioId(user.getUsuarioId());
        if(respuesta.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        iUsuariosRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario agregado");
    }

    public ResponseEntity<String> addUsuariosLista(List<Usuario> usuarios) {
        for (Usuario user : usuarios) {
            if (user.getCartera() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cartera no debe ser menor a 0");
            }

            Optional<Usuario> respuesta = iUsuariosRepository.findByUsuarioId(user.getUsuarioId());
            if (respuesta.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario '" + user.getNombre_usuario() + "' ya existe");
            }

            user.setCarteraOriginal(user.getCartera());
            iUsuariosRepository.save(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuarios agregados");
    }


    public ResponseEntity<String> deleteUsuario(String nombreUsuario, String contrasena){
        Long usuarioId = obtenerIdUsuario(nombreUsuario, contrasena);
        Optional<Usuario> respuesta = iUsuariosRepository.findByUsuarioId(usuarioId);
        if(respuesta.isPresent()){
            iUsuariosRepository.delete(respuesta.get());
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    public Long obtenerIdUsuario(String nombreUsuario, String contrasena){
        Optional<Usuario> respuesta = iUsuariosRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        if(respuesta.isPresent()){
            return respuesta.get().getUsuarioId();
        }
        return -1L;
    }

    public ResponseEntity<String> agregarAccionUsuario(Long usuarioId, Long accionId, Long cantidadAcciones, Long umbralInferior, Long umbralSuperior){
        Optional<Usuario> usuarioOptional = iUsuariosRepository.findById(usuarioId);
        Optional<Accion> accionOptional = iAccionesRepository.findById(accionId);

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

            UsuarioAcciones userTemporal = iCrudUsuarioAcciones.findByUsuarioAndAcciones(usuario, accion);
            Long presupuesto = presupuestoCartera(usuario, accion, cantidadAcciones);

            if (presupuesto == -1L) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No tiene presupuesto suficiente para comprar la acción");
            }

            if (userTemporal != null) {
                usuario.setCartera(presupuesto);
                iUsuariosRepository.save(usuario);
                Long suma = userTemporal.getCantidadAcciones() + cantidadAcciones;
                userTemporal.setCantidadAcciones(suma);
                iCrudUsuarioAcciones.save(userTemporal);

                return ResponseEntity.status(HttpStatus.CREATED).body("Se agregaron " + cantidadAcciones + " a la acción " + accion.getNombreAccion());
            } else {
                usuario.setCartera(presupuesto);
                iUsuariosRepository.save(usuario);
                usuario.setCartera(presupuesto);
                iUsuariosRepository.save(usuario);
                iCrudUsuarioAcciones.save(new UsuarioAcciones(usuario, accion, cantidadAcciones, umbralInferior, umbralSuperior));

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
        Optional<Usuario> usuarioOptional = iUsuariosRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<UsuarioAcciones> usuarioAccionesList = iCrudUsuarioAcciones.findByUsuario(usuario);

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
        Long accionId = domainAccionService.obtenerIdAccion(nombreAccion);

        Optional<Usuario> usuarioOptional = iUsuariosRepository.findById(usuarioId);
        Optional<Accion> accionOptional = iAccionesRepository.findById(accionId);

        Usuario usuario = usuarioOptional.get();
        Accion accion = accionOptional.get();

        if (usuarioId == -1L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if (accionId == -1L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Accion no encontrada");
        }

        Optional<UsuarioAcciones> usuarioAccionesOptional = iCrudUsuarioAcciones.findById(new UsuarioAccionesKey(new Usuario(usuarioId), new Accion(accionId)));

        if (usuarioAccionesOptional.isPresent()) {
            if (valorGanancias(usuario, accion, usuarioAccionesOptional.get()) < 0) {
                usuario.setCartera(0L);
                usuario.setCarteraOriginal(0L);
                iUsuariosRepository.save(usuario);
            } else {
                Long variable = usuario.getCarteraOriginal() + valorGanancias(usuario, accion, usuarioAccionesOptional.get());
                usuario.setCarteraOriginal(variable);
                usuario.setCartera(variable);
                iUsuariosRepository.save(usuario);
            }
            iCrudUsuarioAcciones.deleteByUsuarioAndAcciones(usuarioId, accionId);
            return ResponseEntity.status(HttpStatus.OK).body("Accion eliminada para el usuario");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La accion no estaba asociada al usuario");
        }
    }

    @Override
    public void actualizar() {
        List<Accion> acciones = domainAccionService.getAcciones();
        for (Accion accion : acciones) {
            List<UsuarioAcciones> usuarioAccionesList = iCrudUsuarioAcciones.findByAcciones(accion);
            if (usuarioAccionesList != null && !usuarioAccionesList.isEmpty()) {
                for (UsuarioAcciones usuarioAcciones : usuarioAccionesList) {
                    if (accion.getPrecioActual() <= usuarioAcciones.getUmbralInferior()) {
                        usuarioAcciones.setEstadoAccion("La accion " + accion.getNombreAccion() + " ha alcanzado el umbral inferior");
                        iCrudUsuarioAcciones.save(usuarioAcciones);
                        log.info("Sending message: {}", usuarioAcciones.getEstadoAccion());
                        publisher.send(usuarioAcciones.getEstadoAccion());
                    } else if (accion.getPrecioActual() >= usuarioAcciones.getUmbralSuperior()) {
                        usuarioAcciones.setEstadoAccion("La accion " + accion.getNombreAccion() + " ha alcanzado el umbral superior");
                        iCrudUsuarioAcciones.save(usuarioAcciones);
                        log.info("Sending message: {}", usuarioAcciones.getEstadoAccion());
                        publisher.send(usuarioAcciones.getEstadoAccion());
                    }

                    else {
                        usuarioAcciones.setEstadoAccion("Dentro de los umbrales");
                        iCrudUsuarioAcciones.save(usuarioAcciones);
                        log.info("Sending message: {}", usuarioAcciones.getEstadoAccion());
                        publisher.send(usuarioAcciones.getEstadoAccion());
                    }
                }
            }
        }
    }
}