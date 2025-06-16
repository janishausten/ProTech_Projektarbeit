package Fisch_Package;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FischTest {

    @Test
    public void testAusgeben() {
        Fisch fisch = new Fisch("Hecht", 80.0, 3200.0, "Bodensee", "01.06.2025", true);
        String erwartet = "Fischart: Hecht, Größe: 80.0 cm, Gewicht: 3200.0 g, Ort: Bodensee, Datum: 01.06.2025, Gefangen: behalten";
        assertEquals(erwartet, fisch.ausgeben());
    }

  
}