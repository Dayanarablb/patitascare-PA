package com.example.patitascare.repository;

import com.example.patitascare.model.Dueno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DuenoRepository {

    private final Map<Long, Dueno> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public Dueno save(Dueno d) {
        if (d.getId() == null) {
            d.setId(secuencia.getAndIncrement());
        }
        datos.put(d.getId(), d);
        return d;
    }

    public Optional<Dueno> findById(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    public List<Dueno> findAll() {
        return new ArrayList<>(datos.values());
    }

    public boolean existsById(Long id) {
        return datos.containsKey(id);
    }
}