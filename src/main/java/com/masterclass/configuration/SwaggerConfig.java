package com.masterclass.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("springshop-public")
//                .pathsToMatch("v1/checkin/book-seat/**")
//                .build();
//    }

//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("springshop-admin")
//                .pathsToMatch("/admin/**")
//                .packagesToScan("*")
//                .build();
//    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("System design masterclass API")
                        .description("System design masterclass application")
                        .version("v0.1")
                        .license(new License().name("nitishkumar").url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                        .description("API Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }



}
