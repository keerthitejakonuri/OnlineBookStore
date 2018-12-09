package com.ocrs.registration.controller;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ocrs.login.util.Status;
import com.ocrs.registration.model.User;

/**
 * @author Vamsi Katepalli
 * LoginController is used to login to the application and 
 * has methods like login,validateSession etc. TODO: Expand the description once all information is available
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Status register(@RequestBody User user){
		
		System.out.println("reached here");
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		System.out.println("before call 111 --> "+user);
        String uri = new String("http://localhost:8081/CourseService/registerUser/signup");
        user.setRole("Student");
        System.out.println("before call --> "+user);
        Status returns = restTemplate.postForObject(uri, user, Status.class);
        System.out.println("After call --> "+returns.getStatus());
		return returns;
	} 
	 
}
