package com.proyecto.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/trivial/v1/categoria").allowedOrigins("http://localhost:8081").allowedMethods("GET");
//		registry.addMapping("/trivial/v1/ranking").allowedOrigins("http://localhost:8081").allowedMethods("GET");
//		registry.addMapping("/trivial/v1/roles").allowedOrigins("http://localhost:8081").allowedMethods("GET");
		registry.addMapping("/trivial/v1/users").allowedOrigins("http://localhost:8081").allowedMethods("GET", "POST", "PUT", "DELETE");
//		registry.addMapping("/trivial/v1/**").allowedOrigins("http://localhost:8081")
//				.allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(true);
	}
}