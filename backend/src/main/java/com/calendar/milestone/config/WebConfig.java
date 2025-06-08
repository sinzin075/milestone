package com.calendar.milestone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000")
        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
        // TODO: Replace wildcard allowedHeaders("*") with explicit header list before production
        // Reason: Allowing all headers may introduce security risks (e.g., unexpected custom headers)
        .allowedHeaders("*")
        .allowCredentials(true);
    }
}
    

