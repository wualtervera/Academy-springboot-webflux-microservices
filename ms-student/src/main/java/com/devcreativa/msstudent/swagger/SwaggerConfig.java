package com.devcreativa.msstudent.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Academy", version = "1.0", description = "Documentation APIs v1.0")
    , servers = @Server(url = "http://localhost:65523")
)
public class SwaggerConfig {

}
