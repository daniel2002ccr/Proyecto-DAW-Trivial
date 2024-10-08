package com.proyecto.trivial.dtos;

public class UsersDTO {

	private Integer userId;
	private String userName;
	private String userPasswd;
	private String userEmail;
	private byte[] userImage;
	private Integer puntuacion;
	private Integer activo;

	public UsersDTO() {
		super();
	}

	public UsersDTO(Integer userId, String userName, String userPasswd, String userEmail, byte[] userImage,
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