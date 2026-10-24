package com.example.patitascare.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final String INICIO = "inicio";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        req.setAttribute(INICIO, System.currentTimeMillis());
        System.out.println("➡️  " + req.getMethod() + " " + req.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res,
                                Object handler, Exception ex) {
        Long inicio = (Long) req.getAttribute(INICIO);
        long ms = (inicio == null) ? 0 : (System.currentTimeMillis() - inicio);
        System.out.println("⬅️  " + req.getMethod() + " " + req.getRequestURI()
                + " -> " + res.getStatus() + " (" + ms + "ms)");
    }
}