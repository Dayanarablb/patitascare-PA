package com.example.patitascare.controller;

import com.example.patitascare.dto.MascotaRequest;
import com.example.patitascare.model.Especie;
import com.example.patitascare.model.Mascota;
import com.example.patitascare.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService service;

    public MascotaController(MascotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mascota> crear(@Valid @RequestBody MascotaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public List<Mascota> listar(@RequestParam(required = false) Especie especie) {
        return service.listar(especie);
    }

    @GetMapping("/{id}")
    public Mascota buscar(@PathVariable Long id) {
        return service.buscar(id);
    }
}