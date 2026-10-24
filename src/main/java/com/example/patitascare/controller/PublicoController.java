package com.example.patitascare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/publico")
public class PublicoController {

    @GetMapping("/salud")
    public Map<String, String> salud() {
        return Map.of("status", "UP", "servicio", "PatitasCare 🐾");
    }
}