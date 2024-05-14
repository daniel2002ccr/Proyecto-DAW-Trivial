package com.proyecto.trivial.controllers.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.trivial.dtos.UsersDTO;
import com.proyecto.trivial.entities.UserEntity;
import com.proyecto.trivial.repositories.IUsersRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1")
public class UsersRestController {

	@Autowired
	private IUsersRepository usersRepository;

	@GetMapping("/users")
	public Iterable<UserEntity> obtenerTodosUsuarios() {

		Iterable<UserEntity> users = usersRepository.findAll();
		return users;
	}

	@GetMapping(value = "/users/{id}")
	public Optional<UserEntity> obtenerTodosUsuariosPorID(@PathVariable("id") Integer id) {

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

	@PostMapping("/users")
	public ResponseEntity<?> insertarUsuario(@RequestParam("userName") String userName,
			@RequestParam("userEmail") String userEmail, @RequestParam("userPasswd") String userPasswd,
			@RequestParam("userImage") MultipartFile userImage, @RequestParam("cantidad") Integer cantidad,
			@RequestParam("activo") Integer activo) {
		try {
			UserEntity user = new UserEntity();
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUserPasswd(userPasswd);
			user.setUserImage(userImage.getBytes());
			user.setCantidad(cantidad);
			user.setActivo(activo);

			usersRepository.save(user);

			return new ResponseEntity<>("Usuario insertado con éxito.", HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error al insertar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity actualizarUsuario(@PathVariable Integer userId, @RequestBody UserEntity user) {

		UserEntity existingUser = usersRepository.findById(userId).orElse(null);

		if (existingUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}

		existingUser.setUserName(user.getUserName());
		existingUser.setUserEmail(user.getUserEmail());
		existingUser.setUserImage(user.getUserImage());
		existingUser.setCantidad(user.getCantidad());
		existingUser.setActivo(user.getActivo());

		usersRepository.save(existingUser);

		return ResponseEntity.ok("Usuario actualizado con éxito.");
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity borrarUsuario(@PathVariable("id") Integer id) {

		UserEntity u = usersRepository.findById(id).get();

		u.setActivo(0);

		usersRepository.save(u);
		return new ResponseEntity<>("Usuario borrado con éxito", HttpStatus.OK);
	}
}