package com.wpl.services;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpl.model.UserDetails;
import com.wpl.tablemap.LoginDetails;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

@Controller
@RequestMapping("/login")
@Transactional
public class LoginService {
	
	@Autowired
	private SessionFactory sf;
	
	private static final Logger log = Logger.getLogger(LoginService.class);

	@RequestMapping(value="/checklogin", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserDetails checkLogin(@RequestBody UserDetails userDetails){
		
		log.info("Inside checkLogin Service"+userDetails.getUserName());
		//userDetails.setFirstName("It Works");
		try{
			
		
		MemcachedClient mc = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));

		LoginDetails ld = (LoginDetails)mc.get(userDetails.getUserName());
		if(ld != null){
			log.info("Cache HIT");
		} else{
			log.info("Cache Miss");
			ld = (LoginDetails) sf.getCurrentSession().get(LoginDetails.class, userDetails.getUserName());
			mc.set(userDetails.getUserName(), 30, ld);
		}
			
		userDetails.setStatus(ld.getStatus());
		userDetails.setName(ld.getName());
		} catch (Exception e){
			e.printStackTrace();
		}
		return userDetails;
	}
}
