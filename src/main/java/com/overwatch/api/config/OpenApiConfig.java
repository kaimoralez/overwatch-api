package com.overwatch.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Overwatch Tactical & Roster API")
                        .version("1.0")
                        .description("API REST para gerenciamento de Heróis, Jogadores, Equipes, Mapas e Partidas de Overwatch."));
    }
}
