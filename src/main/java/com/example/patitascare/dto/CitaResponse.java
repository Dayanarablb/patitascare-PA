package com.example.patitascare.dto;

import com.example.patitascare.model.EstadoCita;

import java.time.LocalDateTime;

public record CitaResponse(
        Long id,
        Long mascotaId,
        LocalDateTime fechaHora,
        String motivo,
        EstadoCita estado,
        String veterinario,
        String notas) {
}