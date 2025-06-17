package com.nerchuko.questionservice.config;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Question Service API")
                        .version("1.0")
                        .description("Question Service API Information"));

    }

}
