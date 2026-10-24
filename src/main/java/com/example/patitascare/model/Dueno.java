package com.example.patitascare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dueno {
    private Long id;
    private String nombreCompleto;
    private String cedula;
    private String email;
    private String telefono;
    private LocalDate fechaRegistro;
}