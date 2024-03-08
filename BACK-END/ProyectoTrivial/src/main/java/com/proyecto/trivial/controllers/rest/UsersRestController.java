package com.proyecto.trivial.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trivial.dtos.UsersDTO;
import com.proyecto.trivial.entities.UserEntity;
import com.proyecto.trivial.repositories.IUsersRepository;

@RestController
@RequestMapping("/v1")
public class UsersRestController {

	@Autowired
	private IUsersRepository usersRepository;

	@GetMapping("/users")
	public Iterable<UserEntity> obtenerTodosUsuarios() {

		// http://localhost:8080/trivial/v1/users

		Iterable<UserEntity> users = usersRepository.findAll();
		return users;
	}

	@GetMapping(value = "/users/{id}")
	public Optional<UserEntity> obtenerTodosUsuariosPorID(@PathVariable("id") Integer id) {

//		http://localhost:8080/trivial/v1/users/1

		return usersRepository.findById(id);
	}

	@GetMapping(value = "/users", params = { "userId", "userName", "userPasswd", "userEmail", "userImage", "cantidad",
			"activo" })
	public List<UsersDTO> obtenerTodosUsuariosConFiltros(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userPasswd", required = false) String userPasswd,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userImage", required = false) String userImage,
			@RequestParam(value = "cantidad", required = false) String cantidad,
			@RequestParam(value = "activo", required = false) String activo) {

		// http://localhost:8080/trivial/v1/users?id=1&nombre&activo=1

		Integer cantidadInteger = null;
		if (!cantidad.equals("")) {
			cantidadInteger = Integer.parseInt(cantidad);
		}

		Integer activoInteger = null;
		if (!activo.equals("")) {
			activoInteger = Integer.parseInt(activo);
		}
		List<UsersDTO> u = usersRepository.buscaUsuarios(userId.toString(), userName, userPasswd, userEmail, userImage,
				cantidadInteger, activoInteger);
		return u;
	}
}