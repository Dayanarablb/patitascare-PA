package com.example.patitascare.controller;

import com.example.patitascare.dto.CitaRequest;
import com.example.patitascare.dto.CitaResponse;
import com.example.patitascare.model.Cita;
import com.example.patitascare.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CitaResponse> agendar(@Valid @RequestBody CitaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toResponse(service.agendar(dto)));
    }

    @PutMapping("/{id}/cancelar")
    public CitaResponse cancelar(@PathVariable Long id) {
        return toResponse(service.cancelar(id));
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<CitaResponse> historial(@PathVariable Long mascotaId) {
        return service.historial(mascotaId).stream().map(this::toResponse).toList();
    }

    private CitaResponse toResponse(Cita c) {
        return new CitaResponse(
                c.getId(), c.getMascotaId(), c.getFechaHora(),
                c.getMotivo(), c.getEstado(), c.getVeterinario(), c.getNotas());
    }
}