package com.proyecto.trivial.dtos;

public class RankingDTO {

	private Integer rankingId;
	private Integer puntuacion;
	private String descripcion;

	public RankingDTO() {
		super();
	}

	public RankingDTO(Integer rankingId, Integer puntuacion) {
		super();
		this.rankingId = rankingId;
		this.puntuacion = puntuacion;
	}

	public RankingDTO(Integer rankingId, Integer puntuacion, String descripcion) {
		super();
		this.rankingId = rankingId;
		this.puntuacion = puntuacion;
		this.descripcion = descripcion;
	}

	public Integer getRankingId() {
		return rankingId;
	}

	public void setRankingId(Integer rankingId) {
		this.rankingId = rankingId;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}