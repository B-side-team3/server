package com.bside.server.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.OAS_30)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.bside.server.module"))
        .paths(PathSelectors.any())
        .build();
  }
}
