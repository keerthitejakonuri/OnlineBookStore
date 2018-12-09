package com.ocrs.login.controller;


import java.util.HashMap;
import java.util.Map;

import com.ocrs.login.model.User;


public class LoginSessionBean {
	private Map<String,User> sessionMap;
	
	public LoginSessionBean(){
		sessionMap = new HashMap<String,User>();
	}

	public Map<String, User> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, User> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	
}
