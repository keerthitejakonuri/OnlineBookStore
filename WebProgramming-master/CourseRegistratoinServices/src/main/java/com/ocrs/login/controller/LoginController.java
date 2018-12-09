package com.ocrs.login.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocrs.login.model.Registration;
import com.ocrs.login.model.User;
import com.ocrs.login.util.HibernateUtil;
import com.ocrs.login.util.Status;

/**
 * @author katva02 LoginController is used to login to the application and has
 *         methods like login,validateSession etc. TODO: Expand the description
 *         once all information is available
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
	private LoginSessionBean loginSessionBean;

	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
	
	@Autowired
	private UserRegisterController userRegisterController;
	
	

	public UserRegisterController getUserRegisterController() {
		return userRegisterController;
	}

	public void setUserRegisterController(UserRegisterController userRegisterController) {
		this.userRegisterController = userRegisterController;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status login2(@RequestBody User user) {
		Status currentStatus = new Status();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(
				"select count(*) from User login where login.userName=:userName and login.password=:password");
		query.setString("userName", user.getUserName());
		query.setString("password", user.getPassword());
		Long count = (Long) query.uniqueResult();
		if (count == 1) {
			String userSessionKey = nextSessionId();
			User user1 = getUser(user.getUserName());
			loginSessionBean.getSessionMap().put(userSessionKey, user1);
			currentStatus.setStatus("SUCCESS");
			currentStatus.setSession(userSessionKey);
			currentStatus.setRole(user1.getRole());

		} else {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(0);// Invalid user
			currentStatus.setErrorMsg("Invalid Username / Password");
		}
		return currentStatus;
	}
	
	@RequestMapping(value = "/autoLogin", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status autologin2(@RequestBody User user) {
		Status currentStatus = new Status();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(
				"select count(*) from User login where login.userName=:userName");
		query.setString("userName", user.getUserName());
		Long count = (Long) query.uniqueResult();
		if (count == 1) {
			user.setPassword("12345");
			user.setEmail("vamsi910@gmail.com");
			user.setFirstName(user.getUserName());
			user.setLastName(user.getUserName());
			user.setRole("Student");
			return login2(user);
		} else {
			//user is not present in DB. so register the user.
			user.setPassword("12345");
			user.setEmail("vamsi910@gmail.com");
			user.setFirstName(user.getUserName());
			user.setLastName(user.getUserName());
			user.setRole("Student");
			Status status = userRegisterController.register(user);
			return login2(user);
			
		}
	}
	
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	@ResponseBody
	public Status logOut( @RequestParam String sessionKey) {
		Status currentStatus = new Status();
		User userObj = (User)loginSessionBean.getSessionMap().remove(sessionKey);
		currentStatus.setStatus("SUCCESS");
		currentStatus.setErrorMsg("User Logged Out Successfully");
		return currentStatus;
	}

	private User getUser(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String queryString = "from User where userName = :userName";
		Query query = session.createQuery(queryString);
		query.setString("userName", username);
		Object queryResult = query.uniqueResult();
		User user = (User) queryResult;
		session.getTransaction().commit();
		return user;
	}
	
	

}
