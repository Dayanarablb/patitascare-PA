package com.example.patitascare.service;

import com.example.patitascare.dto.DuenoRequest;
import com.example.patitascare.exception.DuenoNoEncontradoException;
import com.example.patitascare.model.Dueno;
import com.example.patitascare.repository.DuenoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DuenoService {

    private final DuenoRepository repo;

    public DuenoService(DuenoRepository repo) {
        this.repo = repo;
    }

    public Dueno crear(DuenoRequest dto) {
        Dueno d = new Dueno();
        d.setNombreCompleto(dto.getNombreCompleto());
        d.setCedula(dto.getCedula());
        d.setEmail(dto.getEmail());
        d.setTelefono(dto.getTelefono());
        d.setFechaRegistro(LocalDate.now());
        return repo.save(d);
    }

    public Dueno buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new DuenoNoEncontradoException(
                        "🐾 Dueño no encontrado con id " + id));
    }
}