package br.com.erp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://erp-front-evert90.vercel.app",
                        "https://erp-front-fn6e0ofsj-evert90.vercel.app")
                .allowCredentials(true);
    }
}
