package com.proyecto.trivial.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trivial.dtos.RankingDTO;
import com.proyecto.trivial.entities.RankingEntity;
import com.proyecto.trivial.repositories.IRankingRepository;

@RestController
@RequestMapping("/v1")
public class RankingRestController {

	@Autowired
	private IRankingRepository rankingRepository;

	@GetMapping("/ranking")
	public Iterable<RankingEntity> obtenerTodosRanking() {

		// http://localhost:8080/trivial/v1/ranking

		Iterable<RankingEntity> ranking = rankingRepository.findAll();
		return ranking;
	}

	@GetMapping(value = "/ranking/{id}")
	public Optional<RankingEntity> obtenerTodosRankingPorID(@PathVariable("id") Integer id) {

//		http://localhost:8080/trivial/v1/ranking/1

		return rankingRepository.findById(id);
	}

	@GetMapping(value = "/ranking", params = { "rankingId", "puntuacion" })
	public List<RankingDTO> obtenerTodosRankingConFiltros(
			@RequestParam(value = "rankingId", required = false) Integer rankingId,
			@RequestParam(value = "puntuacion", required = false) String puntuacion) {

		// http://localhost:8080/trivial/v1/ranking?id=1&nombre&activo=1

		List<RankingDTO> ra = rankingRepository.buscaRanking(rankingId.toString(), puntuacion);
		return ra;
	}
}