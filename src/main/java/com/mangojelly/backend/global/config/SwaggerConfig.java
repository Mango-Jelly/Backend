package com.mangojelly.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info =
        @Info(
                title = "Mango-Jelly API Document",
                description = "Mango-Jelly 프로젝트의 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Mango-Jelly 프로젝트 API")
                        .version("1.0.0"));
    }
}