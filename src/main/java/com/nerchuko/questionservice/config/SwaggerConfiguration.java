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
                        .description("This document provides a detailed overview of the Question Microservice API. This service is responsible for managing a repository of quiz questions, including their creation, retrieval, organization by category, and the dynamic generation of quizzes. It also provides functionality for scoring user responses."));

    }

}
