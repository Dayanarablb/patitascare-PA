package com.example.patitascare.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MascotaNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> mascota(MascotaNoEncontradaException e, HttpServletRequest r) {
        return build(HttpStatus.NOT_FOUND, e.getMessage(), r, null);
    }

    @ExceptionHandler(DuenoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> dueno(DuenoNoEncontradoException e, HttpServletRequest r) {
        return build(HttpStatus.NOT_FOUND, e.getMessage(), r, null);
    }

    @ExceptionHandler(CitaNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> cita(CitaNoEncontradaException e, HttpServletRequest r) {
        return build(HttpStatus.NOT_FOUND, e.getMessage(), r, null);
    }

    @ExceptionHandler(LimiteCitasExcedidoException.class)
    public ResponseEntity<ErrorResponse> limite(LimiteCitasExcedidoException e, HttpServletRequest r) {
        return build(HttpStatus.CONFLICT, e.getMessage(), r, null);
    }

    @ExceptionHandler(VetPassInvalidoException.class)
    public ResponseEntity<ErrorResponse> pass(VetPassInvalidoException e, HttpServletRequest r) {
        return build(HttpStatus.UNAUTHORIZED, e.getMessage(), r, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException e, HttpServletRequest r) {
        List<String> det = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();
        return build(HttpStatus.BAD_REQUEST, "Hay datos que no cuadran 🐾", r, det);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus s, String msg,
                                                HttpServletRequest r, List<String> det) {
        return ResponseEntity.status(s).body(new ErrorResponse(
                LocalDateTime.now(), s.value(), s.getReasonPhrase(),
                msg, r.getRequestURI(), det));
    }
}