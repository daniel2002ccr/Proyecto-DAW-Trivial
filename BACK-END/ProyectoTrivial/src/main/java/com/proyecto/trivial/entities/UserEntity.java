package com.proyecto.trivial.entities;

import java.util.List;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

@Entity
@Table(name = "Users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "user_passwd", nullable = false)
	private String userPasswd;

	@Column(name = "user_email", nullable = false, unique = true)
	private String userEmail;

	@Lob
	@Column(name = "user_image")
	private byte[] userImage;

	@Column(name = "puntuacion")
	private Integer puntuacion;

	@Column(name = "activo")
	private Integer activo;
	
	@ElementCollection(fetch= FetchType.EAGER)
	@CollectionTable(name="roles",joinColumns = @JoinColumn(name="user_id"))
	@Column(name="user_role")
	private List<String> user_role;


	public UserEntity() {
		super();
	}

	public UserEntity(Integer userId, String userName, String userPasswd, String userEmail, byte[] userImage,
			Integer puntuacion, Integer activo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPasswd = userPasswd;
		this.userEmail = userEmail;
		this.userImage = userImage;
		this.puntuacion = puntuacion;
		this.activo = activo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
}