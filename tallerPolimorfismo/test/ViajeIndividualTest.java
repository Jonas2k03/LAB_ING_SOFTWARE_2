import java.util.Date;
import Modelo.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class ViajeIndividualTest {

    @Test
    public void testConstructorAndGetters() {
        String origen = "Ciudad A";
        String destino = "Ciudad B";
        int costo = 100;
        Date fechaSalida = new Date();
        Date fechaLlegada = new Date();

        ViajeIndividual viajeIndividual = new ViajeIndividual(origen, destino, costo, fechaSalida, fechaLlegada);

        assertNotNull(viajeIndividual);
        assertEquals(origen, viajeIndividual.getOrigen());
        assertEquals(destino, viajeIndividual.getDestino());
        assertEquals(costo, viajeIndividual.getCosto());
        assertEquals(fechaSalida, viajeIndividual.getFechaSalida());
        assertEquals(fechaLlegada, viajeIndividual.getFechaLlegada());
    }

    @Test
    public void testDescripcion() {
        ViajeIndividual viajeIndividual = new ViajeIndividual("Ciudad A", "Ciudad B", 100,
                new Date(), new Date());

        assertEquals("Metodo implementado en ViajeIndividual", viajeIndividual.descripcion());
    }
}
