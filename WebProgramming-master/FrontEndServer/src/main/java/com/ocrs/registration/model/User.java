package com.ocrs.registration.model;

public class User {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String reenterPassword;
	private String email;
	private int id;
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	private String role;
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", reenterPassword=" + reenterPassword + ", email=" + email
				+ ", id=" + id + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public User(String userName, String password, String firstName,
			String lastName, String reenterPassword, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reenterPassword = reenterPassword;
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getReenterPassword() {
		return reenterPassword;
	}


	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public User() {
	} 
	
}