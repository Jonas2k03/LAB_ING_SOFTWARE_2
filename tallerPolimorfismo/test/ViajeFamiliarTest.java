import java.util.Date;
import Modelo.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class ViajeFamiliarTest {

    @Test
    public void testConstructorParametrizado() {
        String origen = "Ciudad A";
        String destino = "Ciudad B";
        int familia = 4;
        Date fechaSalida = new Date();
        Date fechaLlegada = new Date();
        int costo = 1000;

        ViajeFamiliar viaje = new ViajeFamiliar(origen, destino, familia, fechaSalida, fechaLlegada, costo);

        assertEquals(origen, viaje.getOrigen());
        assertEquals(destino, viaje.getDestino());
        assertEquals(familia, viaje.getFamilia());
        assertEquals(fechaSalida, viaje.getFechaSalida());
        assertEquals(fechaLlegada, viaje.getFechaLlegada());
        assertEquals(costo, viaje.getCosto());
    }

    @Test
    public void testDescripcion() {
        ViajeFamiliar viaje = new ViajeFamiliar("Ciudad A", "Ciudad B", 4, new Date(), new Date(), 1000);
        assertEquals("viaje familiar", viaje.descripcion());    
    }

    @Test
    public void testCualquierMetodo2() {
        ViajeFamiliar viaje = new ViajeFamiliar("Ciudad A", "Ciudad B", 4, new Date(), new Date(), 1000);
        assertEquals("metodo implementado en ViajeFamiliar", viaje.cualquierMetodo2());
    }

    @Test
    public void testGetterSetterFamilia() {
        ViajeFamiliar viaje = new ViajeFamiliar("Ciudad A", "Ciudad B", 4, new Date(), new Date(), 1000);
        viaje.setFamilia(3);
        assertEquals(3, viaje.getFamilia());
    }
}
