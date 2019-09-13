package com.prasanna.restservices.dtos;

public class UserMsDto {
	
	private Long userId;
	private String userName;
	private String email;
	private String roleName;
	
	public UserMsDto() {
		
	}
	
	
	public UserMsDto(Long userId, String userName, String email, String roleName) {
		
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.roleName = roleName;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
