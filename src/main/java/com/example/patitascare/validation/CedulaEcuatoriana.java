package com.example.patitascare.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CedulaEcuatorianaValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CedulaEcuatoriana {
    String message() default "🪪 Cédula inválida: ni un perro policía la aceptaría";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}