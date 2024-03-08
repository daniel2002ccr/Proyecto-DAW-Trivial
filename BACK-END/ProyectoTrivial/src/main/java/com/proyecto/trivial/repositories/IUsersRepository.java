package com.proyecto.trivial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.trivial.dtos.UsersDTO;
import com.proyecto.trivial.entities.UserEntity;

@Repository
public interface IUsersRepository extends CrudRepository<UserEntity, Integer> {

	@Query("SELECT new com.proyecto.trivial.dtos.UsersDTO(u.userId, u.userName, u.userPasswd, u.userEmail, u.userImage, u.cantidad, u.activo) "
			+ "FROM com.proyecto.trivial.entities.UserEntity u "
			+ "WHERE (:userId is null OR CAST (u.userId AS String) LIKE CONCAT('%', :userId, '%')) "
			+ "AND (:userName is null OR u.userName LIKE CONCAT('%', :userName, '%')) "
			+ "AND (:userPasswd is null OR u.userPasswd LIKE CONCAT('%', :userPasswd, '%')) "
			+ "AND (:userEmail is null OR u.userEmail LIKE CONCAT('%', :userEmail, '%')) "
			+ "AND (:userImage is null OR u.userImage = :userImage) "
			+ "AND (:cantidad is null OR u.cantidad >= :cantidad) " + "AND (:activo is null OR u.activo = :activo)")
	public List<UsersDTO> buscaUsuarios(@Param("userId") String userId, @Param("userName") String userName,
			@Param("userPasswd") String userPasswd, @Param("userEmail") String userEmail,
			@Param("userImage") String userImage, @Param("cantidad") Integer cantidad, @Param("activo") Integer activo);
}