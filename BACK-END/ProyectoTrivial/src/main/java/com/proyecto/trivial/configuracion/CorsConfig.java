package com.proyecto.trivial.configuracion;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Bean
	  public CorsConfigurationSource corsConfiguration() {
	    return request -> {
	      org.springframework.web.cors.CorsConfiguration config = 
	          new org.springframework.web.cors.CorsConfiguration();
	      config.setAllowedHeaders(Collections.singletonList("*"));
	      config.setAllowedMethods(Collections.singletonList("*"));
	      config.setAllowedOriginPatterns(Collections.singletonList("*"));
	      config.setAllowCredentials(true);
	      return config;
	    };
	  }
}