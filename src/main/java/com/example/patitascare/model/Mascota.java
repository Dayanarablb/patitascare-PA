package com.example.patitascare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    private Long id;
    private String nombre;
    private Especie especie;
    private String raza;
    private LocalDate fechaNacimiento;
    private Double pesoKg;
    private Long duenoId;
}