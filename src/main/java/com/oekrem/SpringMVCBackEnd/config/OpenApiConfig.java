package com.oekrem.SpringMVCBackEnd.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
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
                        .license(new License().name("OEkrem Lisans"))
                );
    }

    /*@Bean
    public OpenApiCustomizer customizeGlobalHeaders() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
                .forEach(operation -> operation.addParametersItem(new Parameter()
                        .in("header")
                        .schema(new StringSchema())
                        .name("Accept-Language")
                        .description("Language preference")
                        .required(false)
                        .example("en"))));
    }*/

}