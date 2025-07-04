package com.projeto.alura.Escola.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsGlobalConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // A URL do seu próprio serviço Cloud Run
                        .allowedOrigins("https://api-cors-1-798462238118.southamerica-east1.run.app")
                        // Se você estiver testando localmente, pode adicionar:
                        // .allowedOrigins("https://api-cors-1-798462238118.southamerica-east1.run.app", "http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Mantenha se sua API usa autenticação baseada em sessão/cookies/Bearer Token
            }
        };
    }
}