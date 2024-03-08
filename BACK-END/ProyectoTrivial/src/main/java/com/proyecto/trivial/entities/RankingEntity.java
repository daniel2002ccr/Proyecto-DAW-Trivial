package com.proyecto.trivial.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ranking")
public class RankingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ranking_id")
	private Integer rankingId;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserEntity userId;

	@ManyToOne
	@JoinColumn(name = "ID_Categoria", referencedColumnName = "ID_Categoria")
	private CategoriaEntity id;

	@Column(name = "puntuacion")
	private Integer puntuacion;

	public RankingEntity() {
		super();
	}

	public RankingEntity(Integer rankingId, UserEntity userId, CategoriaEntity id, Integer puntuacion) {
		super();
		this.rankingId = rankingId;
		this.userId = userId;
		this.id = id;
		this.puntuacion = puntuacion;
	}

	public Integer getRankingId() {
		return rankingId;
	}

	public void setRankingId(Integer rankingId) {
		this.rankingId = rankingId;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public CategoriaEntity getId() {
		return id;
	}

	public void setId(CategoriaEntity id) {
		this.id = id;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
}