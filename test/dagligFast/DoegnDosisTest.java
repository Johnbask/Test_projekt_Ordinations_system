package dagligFast;

import ordination.DagligFast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

class DoegnDosisTest {
    DagligFast df;
    // variable for det forventede resultat
    double forventet;
    // variable for det aktuelle resultat
    double aktuelle;

    @BeforeEach
    void setUp() {
        df = new DagligFast(LocalDate.of(2026,3,9), LocalDate.of(2026,3,20),1,1,1,1);
    }

    @Test
    @Order(1)
    void test_DoegnDosis_TC1_BasisVaerdi() {
        // ACT - ARRANGE
        forventet = 4;
        aktuelle = df.doegnDosis();
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(2)
    void test_DoegnDosis_TC2_BasisVaerdi() {
        df = new DagligFast(LocalDate.of(2026,3,9), LocalDate.of(2026,3,20), 0,0,2,1);
        forventet = 3;
        aktuelle = df.doegnDosis();
        assertEquals(forventet,aktuelle);
    }

    @Test
    @Order(3)
    void test_DoegnDosis_TC3_BasisVaerdi() {
        df = new DagligFast(LocalDate.of(2026,3,9), LocalDate.of(2026,3,20), 1,0,1,0);
        forventet = 2;
        aktuelle = df.doegnDosis();
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(4)
    void test_DoegnDosis_TC4_BasisVaerdi() {
        df = new DagligFast(LocalDate.of(2026,3,9), LocalDate.of(2026,3,20), 0,1,0,0);
        forventet = 1;
        aktuelle = df.doegnDosis();
        assertEquals(forventet,aktuelle);
    }

    @Test
    @Order(5)
    void test_DoegnDosis_TC5_GraenseVaerdi() {
        df = new DagligFast(LocalDate.of(2026,3,9), LocalDate.of(2026,3,20), 0,0,0,0);
        forventet = 0;
        aktuelle = df.doegnDosis();
        assertEquals(forventet, aktuelle);
    }
}