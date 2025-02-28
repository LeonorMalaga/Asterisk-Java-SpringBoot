package com.asterisk.API.CORS;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configure CORS globally for all routes starting with /task/
        registry.addMapping("/API/**")
                .allowedOrigins("http://localhost:8000")  // Allow request from localhost:8000
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // methods allowed
                .allowedHeaders("*")  // allow all headers
                .allowCredentials(true);  // allow cookies
    }
}