package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {

    /*
    Der laves en hjælpemetode, da klassen der skal testes på er en abstrakt klasse.
    Her oprettes der en konkret instans af den abstrakte klasse
    */
    private Ordination createOrdination(LocalDate startDen, LocalDate slutDen) {
        return new Ordination(startDen, slutDen) {
            @Override public double samletDosis() { return 0; }
            @Override public double doegnDosis() { return 0; }
            @Override public String getType() { return "Test"; }
        };
    }

    // TC1 grænseværdi: startDen = slutDen (samme dag)
    @Test
    void TC1_antalDage_grænseværdi() {
        Ordination ordination = createOrdination(LocalDate.of(2026, 3, 9), LocalDate.of(2026, 3, 9));

        assertEquals(1, ordination.antalDage());
    }

    // TC2 Basisværdi: startDen < slutDen (normal gyldig værdi)
    @Test
    void TC2_antalDage_basisværdi() {
        Ordination ordination = createOrdination(LocalDate.of(2026, 3, 9), LocalDate.of(2026, 3, 10));

        assertEquals(2, ordination.antalDage());
    }

    // TC3 Basisværdi: startDen starter dagen før slutDen, hvilket gør at der forventes 0 dage
    @Test
    void TC3_antalDage_basisværdi() {
        Ordination ordination = createOrdination(LocalDate.of(2026, 3, 9), LocalDate.of(2026, 3, 8));

        assertEquals(0, ordination.antalDage());
    }

    // TC4 Basisværdi: startDen starter lang tid før slutDen, hvilket gør at det er slut før startDen
    @Test
    void TC4_antalDage_basisværdi() {
        Ordination ordination = createOrdination(LocalDate.of(2026, 3, 9), LocalDate.of(2026, 3, 1));

        assertEquals(-7, ordination.antalDage());
    }
}