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

    /*
    TC1 grænseværdi: vægte præcis på grænsen (63 og 88), laegemiddel1
    Forventer 2: patient 1 har vægt = 63, og patient 2 har vægt = 88 begge har laegemiddel1
    */
    @Test
    void TC1_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(63, 88, laegemiddel1);

        assertEquals(2, result);
    }

    /*
    TC2 grænseværdi: vægte præcis på grænsen (63 og 88), laegemiddel2
    Forventer 1: kun patient 1 med vægt = 63 har laegemiddel2
    */
    @Test
    void TC2_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel2() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(63, 88, laegemiddel2);

        assertEquals(1, result);
    }

    /*
    TC3 Grænseværdi: vægte er på den eksklusive grænse (64 til 87), ingen patienter er på intervallet
    Forventer 0: ingen patienter har vægt mellem 64 og 87
    */
    @Test
    void TC3_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(64, 87, laegemiddel1);

        assertEquals(0, result);
    }

    /*
    TC4 grænseværdi: Her er der bredt interval på vægte (62 til 89), alle 4 patienter er inkluderet
    Forventer 4: patient 1, 2, 3 og 4 har alle laegemiddel1
    */
    @Test
    void TC4_antalOrdinationerPrVægtPrLægemiddel_grænseværdi_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(62, 89, laegemiddel1);

        assertEquals(4, result);
    }

    /*
    TC5 basisdata: vægtStart > vægtSlut (100 > 0)
    Forventer 0: ingen patienter opfylder den omvendte interval
    */
    @Test
    void TC5_antalOrdinationerPrVægtPrLægemiddel_basisData_laegemiddel1() {
        int result = controller.antalOrdinationerPrVægtPrLægemiddel(100, 0, laegemiddel1);

        assertEquals(0, result);
    }
}