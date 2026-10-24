package com.example.patitascare.repository;

import com.example.patitascare.model.Especie;
import com.example.patitascare.model.Mascota;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MascotaRepository {

    private final Map<Long, Mascota> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public Mascota save(Mascota m) {
        if (m.getId() == null) {
            m.setId(secuencia.getAndIncrement());
        }
        datos.put(m.getId(), m);
        return m;
    }

    public Optional<Mascota> findById(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    public List<Mascota> findAll() {
        return new ArrayList<>(datos.values());
    }

    public List<Mascota> findByEspecie(Especie especie) {
        return datos.values().stream()
                .filter(m -> m.getEspecie() == especie)
                .collect(Collectors.toList());
    }
}