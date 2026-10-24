package com.example.patitascare.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditoriaAspect {

    @AfterReturning(pointcut = "@annotation(aud)", returning = "res")
    public void auditar(JoinPoint jp, Auditable aud, Object res) {
        System.out.println("📋 AUDITORÍA [" + aud.accion() + "] -> " + res);
    }
}