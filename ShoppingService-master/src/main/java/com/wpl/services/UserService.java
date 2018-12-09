package com.wpl.services;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpl.tablemap.Registration;


@Controller
@RequestMapping("/user")
@Transactional
public class UserService {
	@Autowired
	private SessionFactory sf;
	
	private static final Logger log = Logger.getLogger(UserService.class);

	
	@RequestMapping(value="/get", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registration getOrders(@RequestBody String userName){
		userName = userName.trim();
		log.info("Inside Get User Service: "+userName);
		
		Query query = sf.getCurrentSession().createQuery("FROM Registration p WHERE p.userName='"+userName+"'");
		Registration ud = (Registration) query.uniqueResult();
		return ud;
	}

}
