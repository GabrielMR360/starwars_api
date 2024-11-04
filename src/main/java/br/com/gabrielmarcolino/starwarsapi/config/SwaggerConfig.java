package br.com.gabrielmarcolino.starwarsapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Star Wars Resistence Social Network")
                        .version("1.0.0")
                        .description("Documentação da API Star Wars Resistence Social Network")
                        .contact(new Contact()
                                .name("GitHub da API")
                                .url("https://github.com/GabrielMR360/starwars_api")
                        )
                );
    }
}
