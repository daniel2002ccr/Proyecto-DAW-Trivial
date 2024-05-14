package com.proyecto.trivial.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trivial.dtos.RolesDTO;
import com.proyecto.trivial.entities.RolEntity;
import com.proyecto.trivial.repositories.IRolesRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1")
public class RolesRestController {

	@Autowired
	private IRolesRepository rolesRepository;

	@GetMapping("/roles")
	public Iterable<RolEntity> obtenerTodosRoles() {

		Iterable<RolEntity> roles = rolesRepository.findAll();
		return roles;
	}

	@GetMapping(value = "/roles/{id}")
	public Optional<RolEntity> obtenerTodosRolesPorID(@PathVariable("id") Integer id) {

		return rolesRepository.findById(id);
	}

	@GetMapping(value = "/roles", params = { "roleId", "userRole" })
	public List<RolesDTO> obtenerTodosRolesConFiltros(@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "userRole", required = false) String userRole) {

		List<RolesDTO> r = rolesRepository.buscaRoles(roleId.toString(), userRole);
		return r;
	}

	@PostMapping("/roles")
	public ResponseEntity insertarRol(@RequestBody RolEntity rol) {

		rolesRepository.save(rol);
		return new ResponseEntity<>("Rol insertado con éxito.", HttpStatus.OK);
	}

	@PutMapping("/roles")
	public ResponseEntity actualizarRol(@RequestBody RolEntity rol) {

		rolesRepository.save(rol);
		return new ResponseEntity<>("Rol actualizado con éxito.", HttpStatus.OK);
	}
}