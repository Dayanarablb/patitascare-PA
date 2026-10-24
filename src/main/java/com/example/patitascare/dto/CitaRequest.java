package com.example.patitascare.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaRequest {

    @NotNull(message = "La mascota es obligatoria")
    private Long mascotaId;

    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "Las citas son hacia adelante, no hay máquina del tiempo 🕰️")
    private LocalDateTime fechaHora;

    @NotBlank(message = "¿Por qué viene tu peludo?")
    @Size(max = 200, message = "El motivo es demasiado largo")
    private String motivo;

    @NotBlank(message = "El veterinario es obligatorio")
    private String veterinario;

    private String notas;
}