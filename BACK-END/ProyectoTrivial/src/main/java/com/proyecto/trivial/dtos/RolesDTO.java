package com.proyecto.trivial.dtos;

public class RolesDTO {

	private Integer roleId;
	private String userRole;

	public RolesDTO() {
		super();
	}

	public RolesDTO(Integer roleId, String userRole) {
		super();
		this.roleId = roleId;
		this.userRole = userRole;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}