package com.proyecto.trivial.dtos;

public class CategoriasDTO {

	private Integer id;
	private String nombre;
	private Integer activo;

	public CategoriasDTO() {
		super();
	}

	public CategoriasDTO(Integer id) {
		super();
		this.id = id;
	}

	public CategoriasDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public CategoriasDTO(Integer id, String nombre, Integer activo) {
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