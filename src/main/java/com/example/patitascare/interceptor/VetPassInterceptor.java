package com.example.patitascare.interceptor;

import com.example.patitascare.exception.VetPassInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class VetPassInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String pass = req.getHeader("X-Vet-Pass");
        if (pass == null || !pass.startsWith("VET-")) {
            throw new VetPassInvalidoException(
                    "🐾 Sin pase de veterinario no entras al consultorio.");
        }
        return true;
    }
}