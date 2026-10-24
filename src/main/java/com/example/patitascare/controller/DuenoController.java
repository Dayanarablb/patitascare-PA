package com.example.patitascare.controller;

import com.example.patitascare.dto.DuenoRequest;
import com.example.patitascare.model.Dueno;
import com.example.patitascare.service.DuenoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/duenos")
public class DuenoController {

    private final DuenoService service;

    public DuenoController(DuenoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Dueno> crear(@Valid @RequestBody DuenoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public Dueno buscar(@PathVariable Long id) {
        return service.buscar(id);
    }
}