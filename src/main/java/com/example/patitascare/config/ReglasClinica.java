package com.example.patitascare.config;

public class ReglasClinica {

    private final int maxCitasSemana;

    public ReglasClinica(int maxCitasSemana) {
        this.maxCitasSemana = maxCitasSemana;
    }

    public int maxCitasSemana() {
        return maxCitasSemana;
    }
}