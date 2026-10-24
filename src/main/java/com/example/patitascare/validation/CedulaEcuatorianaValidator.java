package com.example.patitascare.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CedulaEcuatorianaValidator
        implements ConstraintValidator<CedulaEcuatoriana, String> {

    @Override
    public boolean isValid(String c, ConstraintValidatorContext ctx) {
        if (c == null || !c.matches("\\d{10}")) return false;

        int prov = Integer.parseInt(c.substring(0, 2));
        if (prov < 1 || (prov > 24 && prov != 30)) return false;

        if (Character.getNumericValue(c.charAt(2)) > 5) return false;

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int d = Character.getNumericValue(c.charAt(i));
            d = (i % 2 == 0) ? d * 2 : d;
            suma += (d > 9) ? d - 9 : d;
        }
        int verificador = (10 - (suma % 10)) % 10;
        return verificador == Character.getNumericValue(c.charAt(9));
    }
}