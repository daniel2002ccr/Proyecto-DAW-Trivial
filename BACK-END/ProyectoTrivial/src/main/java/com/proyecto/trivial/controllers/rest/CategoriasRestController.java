package com.proyecto.trivial.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trivial.dtos.CategoriasDTO;
import com.proyecto.trivial.entities.CategoriaEntity;
import com.proyecto.trivial.repositories.ICategoriasRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1")
public class CategoriasRestController {

	@Autowired
	private ICategoriasRepository categoriasRepository;

	@GetMapping("/categoria")
	public Iterable<CategoriaEntity> obtenerTodasCategorias() {

		// http://localhost:8080/trivial/v1/categoria

		Iterable<CategoriaEntity> categorias = categoriasRepository.findAll();
		return categorias;
	}

	@GetMapping(value = "/categoria/{id}")
	public Optional<CategoriaEntity> obtenerTodasCategoriasPorID(@PathVariable("id") Integer id) {

//		http://localhost:8080/trivial/v1/categoria/1

		return categoriasRepository.findById(id);
	}

	@GetMapping(value = "/categoria", params = { "id", "nombre", "activo" })
	public List<CategoriasDTO> obtenerTodasCategoriasConFiltros(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "activo", required = false) String activo) {

		// http://localhost:8080/trivial/v1/categoria?id=1&nombre&activo=1

		Integer activoInteger = null;
		if (!activo.equals("")) {
			activoInteger = Integer.parseInt(activo);
		}
		List<CategoriasDTO> c = categoriasRepository.buscaCategorias(id.toString(), nombre, activoInteger);
		return c;
	}
}