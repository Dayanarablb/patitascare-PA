package com.example.patitascare.repository;

import com.example.patitascare.model.Cita;
import com.example.patitascare.model.EstadoCita;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CitaRepository {

    private final Map<Long, Cita> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public Cita save(Cita c) {
        if (c.getId() == null) {
            c.setId(secuencia.getAndIncrement());
        }
        datos.put(c.getId(), c);
        return c;
    }

    public Optional<Cita> findById(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    public List<Cita> findByMascotaId(Long mascotaId) {
        return datos.values().stream()
                .filter(c -> c.getMascotaId().equals(mascotaId))
                .collect(Collectors.toList());
    }

    /** Cuenta citas AGENDADAS de una mascota dentro de un rango de fechas (inclusive). */
    public long contarActivasEntre(Long mascotaId, LocalDate desde, LocalDate hasta) {
        return datos.values().stream()
                .filter(c -> c.getMascotaId().equals(mascotaId))
                .filter(c -> c.getEstado() == EstadoCita.AGENDADA)
                .filter(c -> {
                    LocalDate f = c.getFechaHora().toLocalDate();
                    return !f.isBefore(desde) && !f.isAfter(hasta);
                })
                .count();
    }
}