package com.example.patitascare.notificacion;
import com.example.patitascare.model.Cita;
import com.example.patitascare.model.Dueno;

public interface Notificador {
    void avisarCita(Dueno dueno, Cita cita);
}
