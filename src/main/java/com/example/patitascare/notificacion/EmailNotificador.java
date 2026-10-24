package com.example.patitascare.notificacion;

import com.example.patitascare.model.Cita;
import com.example.patitascare.model.Dueno;
import org.springframework.stereotype.Component;

@Component("email")
public class EmailNotificador implements Notificador {
    @Override
    public void avisarCita(Dueno d, Cita c) {
        System.out.println("Email a " + d.getEmail()
                + ": ¡cita lista para tu peludo!");
    }
}