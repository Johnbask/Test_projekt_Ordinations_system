import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.Controller;
import ordination.Ordination;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PNgivDosisTest {

    @Test
    void test_PN_GivDosis() {
        PN pnOrdination = new PN(LocalDate.of(2026,3,9), LocalDate.of(2026,7,15),10);

        boolean ordineret = pnOrdination.givDosis(LocalDate.of(2026,3,9));
        LocalDate dato = LocalDate.of(2026,3,9);
        assertTrue(ordineret);
        assertTrue(pnOrdination.getDatoerIndtaget().contains(dato));

        ordineret = pnOrdination.givDosis(LocalDate.of(2026,7,15));
        dato = LocalDate.of(2026,7,15);
        assertTrue(ordineret);
        assertTrue(pnOrdination.getDatoerIndtaget().contains(dato));

        ordineret = pnOrdination.givDosis(LocalDate.of(2026,4,20));
        dato = LocalDate.of(2026,4,20);
        assertTrue(ordineret);
        assertTrue(pnOrdination.getDatoerIndtaget().contains(dato));

        ordineret = pnOrdination.givDosis(LocalDate.of(2026,3,8));
        dato = LocalDate.of(2026,3,8);
        assertFalse(ordineret);
        assertFalse(pnOrdination.getDatoerIndtaget().contains(dato));

        ordineret = pnOrdination.givDosis(LocalDate.of(2026,7,16));
        dato = LocalDate.of(2026,7,16);
        assertFalse(ordineret);
        assertFalse(pnOrdination.getDatoerIndtaget().contains(dato));
    }
}
