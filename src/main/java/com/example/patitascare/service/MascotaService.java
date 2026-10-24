package com.example.patitascare.service;

import com.example.patitascare.dto.MascotaRequest;
import com.example.patitascare.exception.DuenoNoEncontradoException;
import com.example.patitascare.exception.MascotaNoEncontradaException;
import com.example.patitascare.model.Especie;
import com.example.patitascare.model.Mascota;
import com.example.patitascare.repository.DuenoRepository;
import com.example.patitascare.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository repo;
    private final DuenoRepository duenoRepo;

    public MascotaService(MascotaRepository repo, DuenoRepository duenoRepo) {
        this.repo = repo;
        this.duenoRepo = duenoRepo;
    }

    public Mascota crear(MascotaRequest dto) {
        if (!duenoRepo.existsById(dto.getDuenoId())) {
            throw new DuenoNoEncontradoException(
                    "No existe el dueño con id " + dto.getDuenoId());
        }
        Mascota m = new Mascota();
        m.setNombre(dto.getNombre());
        m.setEspecie(dto.getEspecie());
        m.setRaza(dto.getRaza());
        m.setFechaNacimiento(dto.getFechaNacimiento());
        m.setPesoKg(dto.getPesoKg());
        m.setDuenoId(dto.getDuenoId());
        return repo.save(m);
    }

    public Mascota buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new MascotaNoEncontradaException(
                        "Mascota no encontrada con id " + id));
    }

    public List<Mascota> listar(Especie especie) {
        return (especie == null) ? repo.findAll() : repo.findByEspecie(especie);
    }
}