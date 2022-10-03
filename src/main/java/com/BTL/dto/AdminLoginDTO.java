package com.BTL.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminLoginDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	String username;
	@NotEmpty
	String password;
	
	Boolean rememberMe= false;

	public AdminLoginDTO(@NotEmpty String username, @NotEmpty String password, Boolean rememberMe) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
	}

	public AdminLoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminLoginDTO(@NotEmpty String username, @NotEmpty String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}