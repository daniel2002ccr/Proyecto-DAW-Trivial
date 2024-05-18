package com.proyecto.trivial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.trivial.dtos.RankingDTO;
import com.proyecto.trivial.entities.RankingEntity;

@Repository
public interface IRankingRepository extends CrudRepository<RankingEntity, Integer> {

	@Query("SELECT new com.proyecto.trivial.dtos.RankingDTO(ra.rankingId, ra.puntuacion, ra.descripcion) "
			+ "FROM com.proyecto.trivial.entities.RankingEntity ra "
			+ "WHERE (:rankingId is null OR CAST (ra.rankingId AS String) LIKE CONCAT('%', :rankingId, '%')) "
			+ "AND (:puntuacion is null OR ra.puntuacion >= :puntuacion) "
			+ "AND (:descripcion is null OR ra.descripcion >= :descripcion) ")

	public List<RankingDTO> buscaRanking(@Param("rankingId") String rankingId, @Param("puntuacion") String puntuacion,
			@Param("descripcion") String descripcion);
}