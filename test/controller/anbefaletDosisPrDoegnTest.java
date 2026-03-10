package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static controller.Controller.getController;

class anbefaletDosisPrDoegnTest {
    private Patient p;
    private Laegemiddel lm;
    // variable, der består af det forventede resultat
    double forventet;
    // variable, der består af det aktuelle resultat
    double aktuelle;
    @BeforeEach
    void setUp() {
        p = new Patient("240697-0825", "Aksel", 24);
        lm = new Laegemiddel("Paracetamol", 1,1.5,2,"ml");
    }

    //Vi tester om beregning af doegnDosis er korrekt alt afhængig af patientens vægt

    @Test
    @Order(1)
    void test_anbefaletDosisPrDoegn_Vaegt24() {
        //ACT - ASSERT
        forventet = 24;
        aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(2)
    void test_anbefaletDosisPrDoegn_Vaegt25() {
        // ACT - ASSERT
        p.setVaegt(25);
        double forventet = 37.5;
        double aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(3)
    void test_anbefaletDosisPrDoegn_Vaegt120() {
        // ACT - ASSERT
        p.setVaegt(120);
        double forventet = 180;
        double aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(4)
    void test_anbefaletDosisPrDoegn_Vaegt121() {
        // ACT - ASSERT
        p.setVaegt(121);
        double forventet = 242;
        double aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(5)
    void test_anbefaletDosisPrDoegn_Vaegt15() {
        // ACT - ASSERT
        p.setVaegt(15);
        double forventet = 15;
        double aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(6)
    void test_anbefaletDosisPrDoegn_Vaegt90() {
        // ACT - ASSERT
        p.setVaegt(90);
        double forventet = 135;
        double aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }

    @Test
    @Order(7)
    void test_anbefaletDosisPrDoegn_Vaegt130() {
        // ACT - ASSERT
        p.setVaegt(130);
        double forventet = 260;
        double aktuelle = getController().anbefaletDosisPrDoegn(p, lm);
        assertEquals(forventet, aktuelle);
    }
}