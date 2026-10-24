package com.example.patitascare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Long id;
    private Long mascotaId;
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;
    private String veterinario;
    private String notas;
}