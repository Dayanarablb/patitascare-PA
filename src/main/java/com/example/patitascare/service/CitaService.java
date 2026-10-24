package com.example.patitascare.service;

import com.example.patitascare.dto.CitaRequest;
import com.example.patitascare.exception.CitaNoEncontradaException;
import com.example.patitascare.exception.MascotaNoEncontradaException;
import com.example.patitascare.model.Cita;
import com.example.patitascare.model.Dueno;
import com.example.patitascare.model.EstadoCita;
import com.example.patitascare.model.Mascota;
import com.example.patitascare.notificacion.Notificador;
import com.example.patitascare.repository.CitaRepository;
import com.example.patitascare.repository.DuenoRepository;
import com.example.patitascare.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.patitascare.aspect.Auditable;

import java.util.List;

@Service
public class CitaService {

    private final CitaRepository repo;
    private final MascotaRepository mascotaRepo;
    private final DuenoRepository duenoRepo;
    private final Notificador notificador;

    public CitaService(CitaRepository repo,
                       MascotaRepository mascotaRepo,
                       DuenoRepository duenoRepo,
                       @Qualifier("email") Notificador notificador) {
        this.repo = repo;
        this.mascotaRepo = mascotaRepo;
        this.duenoRepo = duenoRepo;
        this.notificador = notificador;
    }
    @Auditable(accion = "AGENDAR_CITA")
    public Cita agendar(CitaRequest dto) {
        Mascota m = mascotaRepo.findById(dto.getMascotaId())
                .orElseThrow(() -> new MascotaNoEncontradaException(
                        "No existe la mascota con id " + dto.getMascotaId()));

        Cita c = new Cita();
        c.setMascotaId(dto.getMascotaId());
        c.setFechaHora(dto.getFechaHora());
        c.setMotivo(dto.getMotivo());
        c.setVeterinario(dto.getVeterinario());
        c.setNotas(dto.getNotas());
        c.setEstado(EstadoCita.AGENDADA);
        Cita guardada = repo.save(c);

        duenoRepo.findById(m.getDuenoId())
                .ifPresent(d -> notificador.avisarCita(d, guardada));

        return guardada;
    }
    @Auditable(accion = "CANCELAR_CITA")
    public Cita cancelar(Long id) {
        Cita c = repo.findById(id)
                .orElseThrow(() -> new CitaNoEncontradaException(
                        "Cita no encontrada con id " + id));
        c.setEstado(EstadoCita.CANCELADA);
        return repo.save(c);
    }

    public List<Cita> historial(Long mascotaId) {
        return repo.findByMascotaId(mascotaId);
    }
}