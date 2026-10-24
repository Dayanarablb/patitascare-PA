package com.example.patitascare.aspect;

import com.example.patitascare.config.ReglasClinica;
import com.example.patitascare.dto.CitaRequest;
import com.example.patitascare.exception.LimiteCitasExcedidoException;
import com.example.patitascare.repository.CitaRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Aspect
@Component
public class LimiteCitasAspect {

    private final CitaRepository repo;
    private final ReglasClinica reglas;

    public LimiteCitasAspect(CitaRepository repo, ReglasClinica reglas) {
        this.repo = repo;
        this.reglas = reglas;
    }

    @Before("execution(* com.example.patitascare.service.CitaService.agendar(..)) && args(dto)")
    public void validar(CitaRequest dto) {
        LocalDate lunes = dto.getFechaHora().toLocalDate().with(DayOfWeek.MONDAY);
        long activas = repo.contarActivasEntre(
                dto.getMascotaId(), lunes, lunes.plusDays(6));
        if (activas >= reglas.maxCitasSemana()) {
            throw new LimiteCitasExcedidoException(
                    "🐕 ¡Tu peludo ya tiene " + activas
                            + " citas esta semana! Dale un respiro.");
        }
    }
}