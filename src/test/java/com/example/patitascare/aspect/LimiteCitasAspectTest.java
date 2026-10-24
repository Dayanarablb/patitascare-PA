package com.example.patitascare.aspect;

import com.example.patitascare.dto.CitaRequest;
import com.example.patitascare.dto.DuenoRequest;
import com.example.patitascare.dto.MascotaRequest;
import com.example.patitascare.exception.LimiteCitasExcedidoException;
import com.example.patitascare.model.Especie;
import com.example.patitascare.service.CitaService;
import com.example.patitascare.service.DuenoService;
import com.example.patitascare.service.MascotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LimiteCitasAspectTest {

    @Autowired private DuenoService duenoService;
    @Autowired private MascotaService mascotaService;
    @Autowired private CitaService citaService;

    @Test
    void laCuartaCitaEnLaMismaSemanaEsRechazada() {
        // 1. Crear dueño y mascota
        DuenoRequest dr = new DuenoRequest();
        dr.setNombreCompleto("Ana Torres");
        dr.setCedula("1710034065");
        dr.setEmail("ana@test.com");
        dr.setTelefono("0991234567");
        Long duenoId = duenoService.crear(dr).getId();

        MascotaRequest mr = new MascotaRequest();
        mr.setNombre("Luna");
        mr.setEspecie(Especie.PERRO);
        mr.setFechaNacimiento(LocalDate.of(2021, 3, 1));
        mr.setPesoKg(8.0);
        mr.setDuenoId(duenoId);
        Long mascotaId = mascotaService.crear(mr).getId();

        // 2. Agendar 3 citas en la misma semana
        LocalDate lunes = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        for (int i = 0; i < 3; i++) {
            CitaRequest cr = new CitaRequest();
            cr.setMascotaId(mascotaId);
            cr.setFechaHora(lunes.plusDays(i).atTime(10, 0));
            cr.setMotivo("Chequeo");
            cr.setVeterinario("Dr. House");
            citaService.agendar(cr);
        }

        // 3. La cuarta debe lanzar la excepción por el Aspecto
        CitaRequest cuarta = new CitaRequest();
        cuarta.setMascotaId(mascotaId);
        cuarta.setFechaHora(lunes.plusDays(3).atTime(10, 0));
        cuarta.setMotivo("Vacuna");
        cuarta.setVeterinario("Dr. House");

        LimiteCitasExcedidoException ex = assertThrows(
                LimiteCitasExcedidoException.class,
                () -> citaService.agendar(cuarta));
        assertTrue(ex.getMessage().contains("3 citas"));
    }
}