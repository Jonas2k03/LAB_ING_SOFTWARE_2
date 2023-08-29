import java.util.Date;
import Modelo.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViajeIncentivoTest {

    @Test
    public void testConstructorAndGetters() {
        String empresa = "Compania X";
        String origen = "Ciudad A";
        String destino = "Ciudad B";
        int costo = 100;
        Date fechaSalida = new Date();
        Date fechaLlegada = new Date();

        ViajeIncentivo viajeIncentivo = new ViajeIncentivo(empresa, origen, costo, fechaSalida, fechaLlegada, destino);

        assertNotNull(viajeIncentivo);
        assertEquals(empresa, viajeIncentivo.getEmpresa());
        assertEquals(origen, viajeIncentivo.getOrigen());
        assertEquals(destino, viajeIncentivo.getDestino());
        assertEquals(costo, viajeIncentivo.getCosto());
        assertEquals(fechaSalida, viajeIncentivo.getFechaSalida());
        assertEquals(fechaLlegada, viajeIncentivo.getFechaLlegada());
    }

    @Test
    public void testDescripcion() {
        ViajeIncentivo viajeIncentivo = new ViajeIncentivo("Compania X", "Ciudad A", 100,
                new Date(), new Date(), "Ciudad B");

        assertEquals("viaje incentivo", viajeIncentivo.descripcion());
    }

    @Test
    public void testCualquierMetodo2() {
        ViajeIncentivo viajeIncentivo = new ViajeIncentivo("Compania X", "Ciudad A", 100,
                new Date(), new Date(), "Ciudad B");

        assertEquals("metodo implementado en ViajeIncentivo", viajeIncentivo.cualquierMetodo2());
    }
}
