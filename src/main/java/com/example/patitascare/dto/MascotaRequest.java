package com.example.patitascare.dto;

import com.example.patitascare.model.Especie;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MascotaRequest {

    @NotBlank(message = "🐾 Tu mascota necesita un nombre")
    @Size(max = 30, message = "Ese nombre es más largo que una cola de zorro")
    private String nombre;

    @NotNull(message = "¿Es perro, gato, ave...? Dinos la especie")
    private Especie especie;

    // ¡ESTA ES LA LÍNEA QUE TE FALTA!
    private String raza;

    @Past(message = "Tu mascota no puede haber nacido en el futuro 🔮")
    private LocalDate fechaNacimiento;

    @Positive(message = "El peso debe ser mayor a 0 (ni los hámsters pesan nada)")
    private Double pesoKg;

    @NotNull(message = "El dueño es obligatorio")
    private Long duenoId;
}