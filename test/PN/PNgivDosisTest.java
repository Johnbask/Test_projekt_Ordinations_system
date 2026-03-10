package PN;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ordination.Ordination;
import ordination.PN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class PNgivDosisTest {
    // pnOrdination er den oprettede ordination
    PN pnOrdination;
    // variable for givDosis metoden på den pågældende dato
    boolean ordineret;
    // variable for datoen vi vil sammenligne med, så vi kan tjekke at dato for dosis er indsat i listen
    LocalDate dato;

    @BeforeEach
    void setUp() {
        pnOrdination = new PN(LocalDate.of(2026,3,9), LocalDate.of(2026,7,15),10);
    }

    @Test
    @Order(1)
    void test_PN_GivDosis_TC1_GraenseVaerdi() {
        //ACT - ASSERT
        ordineret = pnOrdination.givDosis(LocalDate.of(2026,3,9));
        dato = LocalDate.of(2026,3,9);
        assertTrue(ordineret);
        assertTrue(pnOrdination.getDatoerIndtaget().contains(dato));
    }

    @Test
    @Order(2)
    void test_PN_GivDosis_TC2_GraenseVaerdi() {
        // ACT - ASSERT
        ordineret = pnOrdination.givDosis(LocalDate.of(2026,7,15));
        dato = LocalDate.of(2026,7,15);
        assertTrue(ordineret);
        assertTrue(pnOrdination.getDatoerIndtaget().contains(dato));
    }

    @Test
    @Order(3)
    void test_PN_GivDosis_TC3_BasisVaedi() {
        // ACT - ASSERT
        ordineret = pnOrdination.givDosis(LocalDate.of(2026,4,20));
        dato = LocalDate.of(2026,4,20);
        assertTrue(ordineret);
        assertTrue(pnOrdination.getDatoerIndtaget().contains(dato));
    }

    @Test
    @Order(4)
    void test_PN_GivDosis_TC4_Ugyldig() {
        // ACT - ASSERT
        ordineret = pnOrdination.givDosis(LocalDate.of(2026,3,8));
        dato = LocalDate.of(2026,3,8);
        assertFalse(ordineret);
        assertFalse(pnOrdination.getDatoerIndtaget().contains(dato));
    }

    @Test
    @Order(5)
    void test_PN_GivDosis_TC5_Ugyldig() {
        // ACT - ASSERT
        ordineret = pnOrdination.givDosis(LocalDate.of(2026,7,16));
        dato = LocalDate.of(2026,7,16);
        assertFalse(ordineret);
        assertFalse(pnOrdination.getDatoerIndtaget().contains(dato));
    }
}
