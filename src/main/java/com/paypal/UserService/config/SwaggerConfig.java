package com.paypal.UserService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("User Service APIs")
                                .version("v1.0")
                                .description("API documentation for User Service")
                                .contact(new Contact().name("Ashutosh Tiwari")
                                        .email("ashutoshtiwari200128@gmail.com"))
                                .license(new License().name("User Service 1.0")
                                        .url("http://springdoc.org")
                                )

                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://192.168.18.66:8088")
                                        .description("Local server."),
                                new Server()
                                        .url("http://localhost:8088")
                                        .description("Live server.")
                        )
                )
                .tags(
                        List.of(
                                new Tag().name("User CRUD APIs"),
                                new Tag().name("Login APIs"),
                                new Tag().name("Test Authentication using JWT")
                        )
                );
    }
}
