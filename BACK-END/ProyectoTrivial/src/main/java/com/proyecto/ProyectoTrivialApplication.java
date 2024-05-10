package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan(basePackages = {"com.proyecto.configuracion"})
public class ProyectoTrivialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTrivialApplication.class, args);
	}
}