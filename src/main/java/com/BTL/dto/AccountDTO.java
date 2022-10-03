package com.BTL.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;


public class AccountDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	String username;
	@NotEmpty
	String password;
	
	Boolean isEdit= false;

	public AccountDTO(@NotEmpty String username, @NotEmpty String password, Boolean isEdit) {
		super();
		this.username = username;
		this.password = password;
		this.isEdit = isEdit;
	}

	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(@NotEmpty String username, @NotEmpty String password) {
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

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}
	
	
	
	
}