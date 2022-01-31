package com.example.demo.auth;

public class UserDTO {
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private int statusCode;
	private String status;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

}