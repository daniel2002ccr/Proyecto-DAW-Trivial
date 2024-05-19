package com.proyecto.trivial.controllers.rest;

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

import com.proyecto.trivial.dtos.RankingDTO;
import com.proyecto.trivial.entities.CategoriaEntity;
import com.proyecto.trivial.entities.RankingEntity;
import com.proyecto.trivial.repositories.IRankingRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1")
public class RankingRestController {

	@Autowired
	private IRankingRepository rankingRepository;

	@GetMapping("/ranking")
	public Iterable<RankingEntity> obtenerTodosRanking() {

		Iterable<RankingEntity> ranking = rankingRepository.findAll();
		return ranking;
	}

	@GetMapping(value = "/ranking/{id}")
	public Optional<RankingEntity> obtenerTodosRankingPorID(@PathVariable("id") Integer id) {

		return rankingRepository.findById(id);
	}

	@GetMapping(value = "/ranking", params = { "rankingId", "puntuacion", "descripcion" })
	public List<RankingDTO> obtenerTodosRankingConFiltros(
			@RequestParam(value = "rankingId", required = false) Integer rankingId,
			@RequestParam(value = "puntuacion", required = false) String puntuacion,
			@RequestParam(value = "descripcion", required = false) String descripcion) {

		List<RankingDTO> ra = rankingRepository.buscaRanking(rankingId.toString(), puntuacion, descripcion);
		return ra;
	}

	@PostMapping("/ranking")
	public ResponseEntity insertarRanking(@RequestBody RankingEntity ranking) {

		rankingRepository.save(ranking);
		return new ResponseEntity<>("Ranking insertado con éxito.", HttpStatus.OK);
	}

	@PutMapping("/ranking/{id}")
	public ResponseEntity actualizarRanking(@PathVariable("id") Integer id, @RequestBody RankingEntity ranking) {
		Optional<RankingEntity> rankingOptional = rankingRepository.findById(id);
		if (rankingOptional.isPresent()) {
			RankingEntity existingRanking = rankingOptional.get();
			existingRanking.setDescripcion(ranking.getDescripcion());
			rankingRepository.save(existingRanking);
			return new ResponseEntity<>("Ranking actualizado con éxito.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontró el ranking con ID: " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/ranking/{id}")
	public ResponseEntity borrarDescripcion(@PathVariable("id") Integer id) {
		RankingEntity r = rankingRepository.findById(id).get();
		r.setDescripcion("");
		rankingRepository.save(r);
		return new ResponseEntity<>("Descripción borrada con éxito", HttpStatus.OK);
	}
}