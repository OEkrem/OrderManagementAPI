package com.oekrem.SpringMVCBackEnd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Management API")
                        .version("1.0")
                        .description("its an Order Management API with RESTful services.")
                        .contact(new Contact()
                                .name("OEkrem")
                                .email("oekremyildirim@outlook.com")
                        )
                );
    }
}