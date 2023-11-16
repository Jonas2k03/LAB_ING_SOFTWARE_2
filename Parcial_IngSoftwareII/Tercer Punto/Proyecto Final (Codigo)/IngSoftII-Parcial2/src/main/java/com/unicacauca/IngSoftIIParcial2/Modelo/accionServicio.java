package com.unicacauca.IngSoftIIParcial2.Modelo;

import com.unicacauca.IngSoftIIParcial2.Repositories.CrudAcciones;
import com.unicacauca.IngSoftIIParcial2.Repositories.CrudUsuarioAcciones;
import com.unicacauca.IngSoftIIParcial2.Repositories.iObservado;
import com.unicacauca.IngSoftIIParcial2.Repositories.iObservador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class accionServicio implements iObservado {

    private final CrudAcciones crudAcciones;
    private final CrudUsuarioAcciones crudUsuarioAcciones;
    private List<iObservador> Observadores = new ArrayList<>();

    @Autowired
    public accionServicio(CrudAcciones crudAcciones, CrudUsuarioAcciones crudUsuarioAcciones){
        this.crudAcciones = crudAcciones;
        this.crudUsuarioAcciones = crudUsuarioAcciones;
    }

    @Override
    public void asociarObservador(iObservador o) {
        if (Observadores.isEmpty()) {
            Observadores = new ArrayList<>();
        }
        Observadores.add(o);
    }

    @Override
    public void retirarObservador(iObservador o) {
        if (!Observadores.isEmpty()) {
            Observadores.remove(o);
        }
    }

    @Override
    public boolean existeObservador(iObservador prmOb) {
        for (iObservador o : Observadores) {
            if(o==prmOb)
                return true;
        }
        return false;
    }

    @Override
    public void notificar(boolean cambioEstado) {
        if (cambioEstado == true) {
            for (iObservador o : Observadores) {
                o.actualizar();
            }
        }

    }

    public List<Accion> getAcciones() {
        return this.crudAcciones.findAll();
    }

    public ResponseEntity<String> addAccion(Accion accion) {
        Optional<Accion> respuesta = crudAcciones.findByNombreAccion(accion.getNombreAccion());
        if(respuesta.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La accion ya existe");
        }
        crudAcciones.save(accion);
        return ResponseEntity.status(HttpStatus.CREATED).body("Accion agregada");
    }

    public ResponseEntity<String> deleteAccion(String  nombreAccion) {
        Optional<Accion> respuesta = crudAcciones.findByNombreAccion(nombreAccion);
        if(respuesta.isPresent()){
            if (!crudUsuarioAcciones.findByAcciones(respuesta.get()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("La accion ha sido comprada por al menos un usuario, no se puede eliminar.");
            }
            crudAcciones.delete(respuesta.get());
            return ResponseEntity.status(HttpStatus.OK).body("Accion eliminada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La accion no existe");
    }

    public Long obtenerIdAccion(String nombreAccion){
        Optional<Accion> respuesta = crudAcciones.findByNombreAccion(nombreAccion);
        if(respuesta.isPresent()){
            return respuesta.get().getAccionId();
        }
        return -1L;
    }

    public ResponseEntity<String> actualizarPrecioAccion(String nombreAccion, int precioActual){
        Optional<Accion> respuesta = crudAcciones.findByNombreAccion(nombreAccion);
        if(respuesta.isPresent()){
            Accion accion = respuesta.get();
            accion.setPrecioAnterior(accion.getPrecioActual());
            accion.setPrecioActual(precioActual);
            crudAcciones.save(accion);
            notificar(true);
            return ResponseEntity.status(HttpStatus.OK).body("Precio actualizado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La accion no existe");
    }
}
