package com.example.jwt_auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "BearerAuth";
        
        return new OpenAPI()
                // 1. Add professional title information to your portfolio dashboard
                .info(new Info()
                        .title("JWT Authentication REST API Backend")
                        .version("1.0")
                        .description("Secure Spring Boot API integrated with PostgreSQL and JWT tokens."))
                
                // 2. Tell Swagger that requests to private paths need to include a security token
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                
                // 3. Configure the exact UI layout box for your Bearer Token input field
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Paste your raw JWT Token string below to unlock secure endpoints.")));
    }
}
