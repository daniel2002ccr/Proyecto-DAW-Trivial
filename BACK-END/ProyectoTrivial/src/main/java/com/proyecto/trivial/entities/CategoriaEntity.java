package com.proyecto.trivial.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Categoria")
	private Integer id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "activo")
	private Integer activo;

	public CategoriaEntity() {
		super();
	}

	public CategoriaEntity(Integer id) {
		super();
		this.id = id;
	}

	public CategoriaEntity(String nombre) {
		super();
		this.nombre = nombre;
	}

	public CategoriaEntity(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public CategoriaEntity(Integer id, String nombre, Integer activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
}