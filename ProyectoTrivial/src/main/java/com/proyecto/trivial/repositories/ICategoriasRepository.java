package com.proyecto.trivial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.trivial.dtos.CategoriasDTO;
import com.proyecto.trivial.entities.CategoriaEntity;

@Repository
public interface ICategoriasRepository extends CrudRepository<CategoriaEntity, Integer> {

	@Query(value = " SELECT new com.proyecto.trivial.dtos.CategoriasDTO(c.id, c.nombre, c.activo) "
			+ " FROM com.proyecto.trivial.entities.CategoriaEntity c "
			+ " WHERE CAST (c.id AS String) LIKE CONCAT('%', :id, '%') "
			+ " AND c.nombre LIKE CONCAT ('%', :nombre, '%') " + " AND c.activo =:activo ")

	public List<CategoriasDTO> buscaCategorias(@Param("id") String id, @Param("nombre") String nombre,
			@Param("activo") Integer activo);
}