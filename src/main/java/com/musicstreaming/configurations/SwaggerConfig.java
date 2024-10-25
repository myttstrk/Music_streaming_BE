package com.musicstreaming.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI musicStreamingOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Music Streaming API")
                        .description("API documentation for Music Streaming application")
                        .version("1.0"));
    }
}
