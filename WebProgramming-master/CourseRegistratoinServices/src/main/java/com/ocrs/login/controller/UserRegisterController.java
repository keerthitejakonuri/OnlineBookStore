package com.ocrs.login.controller;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocrs.login.model.User;
import com.ocrs.login.util.HibernateUtil;
import com.ocrs.login.util.Status;

/**
 * @author katva02 LoginController is used to login to the application and has
 *         methods like login,validateSession etc. TODO: Expand the description
 *         once all information is available
 */
@RestController
@RequestMapping("/registerUser")
public class UserRegisterController {
	private static final Logger logger = Logger.getLogger(UserRegisterController.class);
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status register(@RequestBody User user) {
		Status currentStatus = new Status();

		if (isUserExists(user.getUserName())) {
			currentStatus.setErrorCode(1);// Existing user
			currentStatus.setErrorMsg("User already exists");
			return currentStatus;
		}

		try {
			System.out.println("RegisterController.register()");
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.save(user);
			session.getTransaction().commit();
			System.out.println(user.getId());
			currentStatus.setStatus("SUCCESS");
		} catch (Exception e) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(2);
			currentStatus.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}

		return currentStatus;
	}

	public boolean isUserExists(String uname) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(" from User login where login.userName=:userName");
		query.setString("userName", uname);
		int count = query.list().size();
		return count == 1;

	}
	
	

}
