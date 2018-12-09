package com.wpl.tablemap;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logindetails")
public class LoginDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UserName")
	private String userName;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Name")
	private String name;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
