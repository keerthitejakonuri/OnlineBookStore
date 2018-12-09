package com.ocrs.registration.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ocrs.registration.model.DBUtil;
import com.ocrs.registration.model.User;

/**
 * @author Vamsi Katepalli
 * LoginController is used to login to the application and 
 * has methods like login,validateSession etc. TODO: Expand the description once all information is available
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  String login(@RequestBody User user){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		System.out.println("before call --> "+user);
        String uri = new String("http://localhost:8081/CourseService/login/signin");
        System.out.println("before call --> "+user);
        String returns = restTemplate.postForObject(uri, user, String.class);
        System.out.println("After call --> "+returns);
		return returns;
	} 
	
	@RequestMapping(value = "/autoLogin", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  String autologin(@RequestBody User user){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/login/autoLogin");
        System.out.println("before call --> "+user);
        String returns = restTemplate.postForObject(uri, user, String.class);
        System.out.println("After call --> "+returns);
		return returns;
	} 
	 
	@RequestMapping(value = "/logoutUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  String logout(@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/login/logOut?sessionKey="+sessionKey);
        String returns = restTemplate.getForObject(uri, String.class);
        System.out.println("After call --> "+returns);
		return returns;
	} 
}
