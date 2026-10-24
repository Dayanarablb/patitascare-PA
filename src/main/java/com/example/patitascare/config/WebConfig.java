package com.example.patitascare.config;

import com.example.patitascare.interceptor.LogInterceptor;
import com.example.patitascare.interceptor.VetPassInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LogInterceptor log;
    private final VetPassInterceptor vet;

    public WebConfig(LogInterceptor log, VetPassInterceptor vet) {
        this.log = log;
        this.vet = vet;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(log).addPathPatterns("/api/**");
        registry.addInterceptor(vet)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/publico/**", "/error");
    }
}