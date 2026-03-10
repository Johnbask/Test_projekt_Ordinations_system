package controller;

import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class opretDagligSkaev {

    private Patient patient;
    private Laegemiddel laegemiddel;
    private Controller controller;

    @BeforeEach
    void setup() {
        controller = new Controller();
        patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
    }

    // TC1 grænseværdi: startDen = slutDen (samme dag)
    @Test
    void test_opretDagligSkaevOrdination_TC1_grænseværdi() {
        LocalDate dato = LocalDate.of(2026, 3, 9);
        LocalTime[] klokkeSlet = { LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(18, 0) };
        double[] antalEnheder = { 1, 1, 1 };

        DagligSkaev result = controller.opretDagligSkaevOrdination(dato, dato, patient, laegemiddel, klokkeSlet, antalEnheder);

        assertNotNull(result);
        assertEquals(laegemiddel, result.getLaegemiddel());
        assertTrue(patient.getOrdinationer().contains(result));
    }

    // TC2 basisværdi: startDen < slutDen (normal gyldig værdi)
    @Test
    void test_opretDagligSkaevOrdination_TC2_normal() {
        LocalDate startDen = LocalDate.of(2026, 3, 9);
        LocalDate slutDen = LocalDate.of(2026, 3, 10);
        LocalTime[] klokkeSlet = { LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(18, 0) };
        double[] antalEnheder = { 1, 1, 1 };

        DagligSkaev result = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);

        assertNotNull(result);
        assertEquals(laegemiddel, result.getLaegemiddel());
        assertTrue(patient.getOrdinationer().contains(result));
    }

    @Test
    void test_opretDagligSkaevOrdination_TC3_Ugyldig() {
        LocalDate startDen = LocalDate.of(2026, 3, 10);
        LocalDate slutDen = LocalDate.of(2026, 3, 9);
        LocalTime[] klokkeSlet = { LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(18, 0) };
        double[] antalEnheder = { 1, 1, 1 };

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretDagligSkaevOrdination(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder)
        );

        assertEquals("End date is before start date", exception.getMessage());
    }

    @Test
    void test_opretDagligSkaevOrdination_TC4_Ugyldig() {
        LocalDate dato = LocalDate.of(2026, 3, 9);
        LocalTime[] klokkeSlet = { LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(18, 0), LocalTime.of(23, 0) };
        double[] antalEnheder = { 1, 1, 1 };

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretDagligSkaevOrdination(dato, dato, patient, laegemiddel, klokkeSlet, antalEnheder)
        );

        assertEquals("number of elements in arrays \"KlokkeSlet\" and \"AntalEnheder\" is not the same", exception.getMessage());
    }

    @Test
    void test_opretDagligSkaevOrdination_TC5_Ugyldig() {
        LocalDate dato = LocalDate.of(2026, 3, 9);
        LocalTime[] klokkeSlet = { LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(18, 0) };
        double[] antalEnheder = { 1, 1, 1, 1 };

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretDagligSkaevOrdination(dato, dato, patient, laegemiddel, klokkeSlet, antalEnheder)
        );

        assertEquals("number of elements in arrays \"KlokkeSlet\" and \"AntalEnheder\" is not the same", exception.getMessage());
    }

}