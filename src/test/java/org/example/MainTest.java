package org.example;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @org.junit.jupiter.api.Test
    void convArrEnMtz() {
        String[] dnaArray = {
                "ATCGAT",
                "TACGTA",
                "CGATCG",
                "ATCGAT",
                "TACGTA",
                "CGATCG"
        };
        String[][] expectedMtz = {
                {"A", "T", "C", "G", "A", "T"},
                {"T", "A", "C", "G", "T", "A"},
                {"C", "G", "A", "T", "C", "G"},
                {"A", "T", "C", "G", "A", "T"},
                {"T", "A", "C", "G", "T", "A"},
                {"C", "G", "A", "T", "C", "G"}
        };
        assertArrayEquals(expectedMtz, Main.ConvArrEnMtz(dnaArray));
    }

    @org.junit.jupiter.api.Test
    void isValidADNLength() {
        assertTrue(Main.isValidADNLength("ATCGAT"));
        assertFalse(Main.isValidADNLength("ATCG"));
    }

    @org.junit.jupiter.api.Test
    void isValidDNASequence() {
        assertTrue(Main.isValidDNASequence("ATCGAT"));
        assertTrue(Main.isValidDNASequence("AAAAAA"));
        assertFalse(Main.isValidDNASequence("ATCGAX"));
    }

    @org.junit.jupiter.api.Test
    public void testIsMutantMutant() {
        String[] mutantDna = {"AAACAA", "ATAAAA", "AATTAA","AAAAAA","AAAAAA","ACCCCA"};;
        assertTrue(Main.isMutant(mutantDna));
    }

    @org.junit.jupiter.api.Test
    public void testIsMutantNonMutant() {
        String[] nonMutantDna =  {"TTGCGA", "AAGTGC", "TTATTT","AGATGG","GCGTCA","TCACTG"};
        assertFalse(Main.isMutant(nonMutantDna));
    }
}