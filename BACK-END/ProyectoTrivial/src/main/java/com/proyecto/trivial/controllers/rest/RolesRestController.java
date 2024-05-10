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

		// http://localhost:8080/trivial/v1/roles

		Iterable<RolEntity> roles = rolesRepository.findAll();
		return roles;
	}

	@GetMapping(value = "/roles/{id}")
	public Optional<RolEntity> obtenerTodosRolesPorID(@PathVariable("id") Integer id) {

//		http://localhost:8080/trivial/v1/roles/1

		return rolesRepository.findById(id);
	}

	@GetMapping(value = "/roles", params = { "roleId", "userRole" })
	public List<RolesDTO> obtenerTodosRolesConFiltros(@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "userRole", required = false) String userRole) {

		// http://localhost:8080/trivial/v1/roles?id=1&nombre&activo=1

		List<RolesDTO> r = rolesRepository.buscaRoles(roleId.toString(), userRole);
		return r;
	}
}