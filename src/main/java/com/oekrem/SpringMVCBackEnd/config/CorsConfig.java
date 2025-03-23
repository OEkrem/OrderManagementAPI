package com.oekrem.SpringMVCBackEnd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Frontend'inizin bulunduğu adres
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metotları
                .allowedHeaders("*") // Tüm başlıklara izin ver
                .allowCredentials(true) // Kimlik doğrulama bilgilerine izin ver
                .maxAge(3600); // Preflight öncesi CORS sonucu ne kadar süre geçerli olsun
    }
}
