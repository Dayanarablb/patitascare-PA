package com.example.patitascare.notificacion;

import com.example.patitascare.model.Cita;
import com.example.patitascare.model.Dueno;
import org.springframework.stereotype.Component;

@Component("sms")
public class SmsNotificador implements Notificador {
    @Override
    public void avisarCita(Dueno d, Cita c) {
        System.out.println("SMS a " + d.getTelefono()
                + ": cita " + c.getFechaHora());
    }
}