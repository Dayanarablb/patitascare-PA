package com.example.patitascare.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.patitascare.service..*(..))")
    public Object medir(ProceedingJoinPoint pjp) throws Throwable {
        long t = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            System.out.println("⏱ " + pjp.getSignature().toShortString()
                    + " tardó " + (System.currentTimeMillis() - t) + "ms");
        }
    }
}