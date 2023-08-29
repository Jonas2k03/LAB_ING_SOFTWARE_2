import Modelo.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViajeTodoIncluidoTest {
    @Test
    public void testDescripcion() {
        ViajeTodoIncluido viaje = new ViajeTodoIncluido("Ciudad A", "Ciudad B", 1000, null, null);

        String descripcionEsperada = "metodo implementado en ViajeTodoIncluido";
        String descripcionObtenida = viaje.descripcion();

        assertEquals(descripcionEsperada, descripcionObtenida);
    }
}
