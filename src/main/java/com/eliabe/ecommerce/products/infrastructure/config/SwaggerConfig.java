package com.eliabe.ecommerce.products.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Ecommerce API - Products")
                        .version("1.0")
                        .description("Documentation for our Gradle-based Spring Boot service."))

                .components(new io.swagger.v3.oas.models.Components()
                        .addResponses("InternalError", new io.swagger.v3.oas.models.responses.ApiResponse()
                                .description("Global 500 Internal Server Error")));
    }


}
