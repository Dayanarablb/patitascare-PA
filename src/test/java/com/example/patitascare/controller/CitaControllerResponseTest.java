package com.example.patitascare.controller;

import com.example.patitascare.dto.DuenoRequest;
import com.example.patitascare.dto.MascotaRequest;
import com.example.patitascare.model.Especie;
import com.example.patitascare.service.DuenoService;
import com.example.patitascare.service.MascotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CitaControllerResponseTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private DuenoService duenoService;
    @Autowired private MascotaService mascotaService;

    @Test
    void elEndpointDevuelveCitaResponseConLosCamposEsperados() throws Exception {
        // Preparar datos
        DuenoRequest dr = new DuenoRequest();
        dr.setNombreCompleto("Luis Vera");
        dr.setCedula("1710034065");
        dr.setEmail("luis@test.com");
        dr.setTelefono("0987654321");
        Long duenoId = duenoService.crear(dr).getId();

        MascotaRequest mr = new MascotaRequest();
        mr.setNombre("Rocky");
        mr.setEspecie(Especie.PERRO);
        mr.setFechaNacimiento(LocalDate.of(2019, 1, 1));
        mr.setPesoKg(20.0);
        mr.setDuenoId(duenoId);
        Long mascotaId = mascotaService.crear(mr).getId();

        String body = String.format("""
                {
                  "mascotaId": %d,
                  "fechaHora": "%s",
                  "motivo": "Chequeo",
                  "veterinario": "Dr. Strange"
                }
                """, mascotaId, LocalDateTime.now().plusDays(2));

        mockMvc.perform(post("/api/citas")
                        .header("X-Vet-Pass", "VET-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.mascotaId").value(mascotaId))
                .andExpect(jsonPath("$.estado").value("AGENDADA"))
                .andExpect(jsonPath("$.veterinario").value("Dr. Strange"));
    }
}