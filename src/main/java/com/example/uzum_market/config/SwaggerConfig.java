package com.example.uzum_market.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Component;

@SecuritySchemes(value = {
        @SecurityScheme(
                name = "bearerAuth",
                description = "Bearer Tokkenni kiriting",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer",
                in = SecuritySchemeIn.HEADER
        )
})
@OpenAPIDefinition(
        info = @Info(title = "Uzum market clone Application",
                description = "BY B29 Team ✔️",
                contact = @Contact(
                        url = "https://uzum.uz",
                        email = "no0404ir@gmail.com",
                        extensions = {@Extension(name = "uzum market clone",
                                properties = {@ExtensionProperty(name = "FirstExtension",
                                        value = "Value extension")})}
                )),
        servers = {
                @Server(url = "http://localhost:8080", description = "Localhost"),
        },
        security = {
                @SecurityRequirement(name = "bearerAuth"),
        }
)
@Component
public class SwaggerConfig {
}
