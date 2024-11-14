package com.auth.autheti.security.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/new/CreateUser") // Especifica la ruta o usa "/**" para todas
                .allowedOrigins("http://localhost:3000") // Origen permitido
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);

    }
}
