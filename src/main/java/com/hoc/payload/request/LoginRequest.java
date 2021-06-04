package com.hoc.payload.request;

import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String username;
	
	@NotBlank
	private String passowrd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	
	
}
