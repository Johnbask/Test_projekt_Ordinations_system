package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class antalOrdinationerPrVægtPrLægemiddel {

    private Controller controller;
    private Laegemiddel laegemiddel1;
    private Laegemiddel laegemiddel2;

    @BeforeEach
    void setup() {
        controller = Controller.getTestController();

        Patient p1 = controller.opretPatient("121256-0512", "Jane Jensen", 63);
        Patient p2 = controller.opretPatient("070985-1153", "Finn Madsen", 88);
        Patient p3 = controller.opretPatient("050972-1233", "Hans Jørgensen", 89);
        Patient p4 = controller.opretPatient("011064-1522", "Ulla Nielsen", 62);

        laegemiddel1 = controller.opretLaegemiddel("Acetylsalicylsyre",  0.1, 0.15, 0.16, "Styk");
        laegemiddel2 = controller.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

        // Patient 1 oprettes: PN Ordination
        controller.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12), p1, laegemiddel2, 123);
        controller.opretPNOrdination(LocalDate.of(2021, 2, 12), LocalDate.of(2021, 2, 14), p1, laegemiddel1, 3);

        // Patient 2 oprettes: PN Ordination
        controller.opretPNOrdination(LocalDate.of(2021, 1, 20), LocalDate.of(2021, 1, 25), p2, laegemiddel1, 5);

        // Patient 3 oprettes: PN Ordination
        controller.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12), p3, laegemiddel1, 123);

        // Patient 4 oprettes: Daglig Fast Ordination
        controller.opretDagligFastOrdination(LocalDate.of(2021, 1, 10), LocalDate.of(2021, 1, 12), p4, laegemiddel1, 2, 0, 1, 0);

        LocalTime[] kl = { LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0), LocalTime.of(18, 45) };

        double[] an = { 0.5, 1, 2.5, 3 };

        // Patient 4 oprettes: Daglig skæv Ordination
        controller.opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23), LocalDate.of(2021, 1, 24), p4, laegemiddel2, kl, an);
    }

    @Test
    void TC1_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(63, 88, laegemiddel1);

        assertEquals(2, result);
    }

    @Test
    void TC2_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel2() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(63, 88, laegemiddel2);

        assertEquals(1, result);
    }

    @Test
    void TC3_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(64, 87, laegemiddel1);

        assertEquals(0, result);
    }

    @Test
    void TC4_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(62, 89, laegemiddel1);

        assertEquals(4, result);
    }

    @Test
    void TC5_antalOrdinationerPrVægtPrLægemiddel_basisData_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(100, 0, laegemiddel1);

        assertEquals(0, result);
    }
}