package com.proyecto.trivial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.trivial.dtos.RolesDTO;
import com.proyecto.trivial.entities.RolEntity;

@Repository
public interface IRolesRepository extends CrudRepository<RolEntity, Integer> {

	@Query("SELECT new com.proyecto.trivial.dtos.RolesDTO(r.roleId, r.userRole) "
			+ "FROM com.proyecto.trivial.entities.RolEntity r "
			+ "WHERE (:roleId is null OR CAST (r.roleId AS String) LIKE CONCAT('%', :roleId, '%')) "
			+ "AND (:userRole is null OR r.userRole LIKE CONCAT('%', :userRole, '%'))")

	public List<RolesDTO> buscaRoles(@Param("roleId") String roleId, @Param("userRole") String userRole);
}