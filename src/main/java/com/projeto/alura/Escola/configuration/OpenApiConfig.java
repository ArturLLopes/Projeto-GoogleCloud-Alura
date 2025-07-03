package com.projeto.alura.Escola.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão Escolar Imersão Alura")
                        .version("1.0.0") // Versão da sua API
                        .description("Esta API fornece endpoints para gerenciar alunos, cursos e turmas em uma instituição de ensino disponiblizada pela alura.") // Descrição da sua API
                        .termsOfService("http://swagger.io/terms/") // Termos de serviço (opcional)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))); // Licença (opcional)
    }
}