package com.course2go.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amazonaws.HttpMethod;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080/")
                .exposedHeaders("Authorization")
                .allowedHeaders("Authorization")
                .allowCredentials(true);
//                .allowedMethods(
//                		HttpMethod.GET.name(),
//                		HttpMethod.POST.name(),
//                		HttpMethod.DELETE.name(),
//                		HttpMethod.PUT.name(),
//                		HttpMethod.HEAD.name());
    }
}
