package ua.nure.cpp.kasapova.practice6;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Access to the static resources:
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);

    }
}
