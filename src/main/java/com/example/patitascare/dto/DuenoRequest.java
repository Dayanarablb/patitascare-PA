package com.example.patitascare.dto;

import com.example.patitascare.validation.CedulaEcuatoriana;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DuenoRequest {

    @NotBlank(message = "🐾 El nombre del dueño es obligatorio")
    @Size(max = 80, message = "Ese nombre es muy largo")
    private String nombreCompleto;

    @NotBlank(message = "🪪 La cédula es obligatoria")
    @CedulaEcuatoriana
    private String cedula;

    @NotBlank(message = "📧 El email es obligatorio")
    @Email(message = "Ese correo no existe ni en el mundo canino")
    private String email;

    @NotBlank(message = "📱 El teléfono es obligatorio")
    @Pattern(regexp = "^09\\d{8}$", message = "Celular ecuatoriano: 09 + 8 dígitos")
    private String telefono;
}