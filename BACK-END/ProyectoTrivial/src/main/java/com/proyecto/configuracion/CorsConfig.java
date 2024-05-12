package com.proyecto.configuracion;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
////		registry.addMapping("/trivial/v1/categoria").allowedOrigins("http://localhost:8081").allowedMethods("GET");
////		registry.addMapping("/trivial/v1/ranking").allowedOrigins("http://localhost:8081").allowedMethods("GET");
////		registry.addMapping("/trivial/v1/roles").allowedOrigins("http://localhost:8081").allowedMethods("GET");
//		registry.addMapping("/trivial/v1/users").allowedOrigins("http://localhost:8081").allowedMethods("GET", "POST",
//				"PUT", "DELETE");
////		registry.addMapping("/trivial/v1/**").allowedOrigins("http://localhost:8081")
////				.allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(true);
//	}
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/trivial/v1/users/**")
//            .allowedOrigins("http://localhost:8081")
//            .allowedMethods("GET", "POST", "PUT", "DELETE")
//            .allowedHeaders("*")
//            .allowCredentials(true)
//            .maxAge(3600);
//    }
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/trivial/v1/users/**").allowedOrigins("http://localhost:8081")
//				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("Content-Type", "Authorization")
//				.maxAge(3600);
//	}
public class CorsConfig implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response.setHeader("Access-Control-Allow-Max-Age", "3600");

		if (((HttpServletRequest) req).getMethod().equalsIgnoreCase("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}

	}
}