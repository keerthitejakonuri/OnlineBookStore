package com.ocrs.registration.controller;

import java.util.List;

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

import com.ocrs.login.util.Status;
import com.ocrs.registration.model.Course;
import com.ocrs.registration.model.Registration;

/**
 * @author Vamsi Katepalli
 * LoginController is used to login to the application and 
 * has methods like login,validateSession etc. TODO: Expand the description once all information is available
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@RequestMapping(value = "/search", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  List<Course> login(@RequestBody Course course,@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/course/search?sessionKey="+sessionKey);
    //    System.out.println("before call --> "+course);
        List<Course> returns = restTemplate.postForObject(uri, course, List.class);
      //  System.out.println("After call --> "+returns);
		return returns;
	} 
	 
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Status addCourse(@RequestBody Course course,@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/course/addCourse?sessionKey="+sessionKey);
        //System.out.println("before call --> "+course);
        Status returns = restTemplate.postForObject(uri, course, Status.class);
        //System.out.println("After call --> "+returns);
		return returns;
	}
	
	@RequestMapping(value = "/viewCourse", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Course viewCourse(@RequestParam String id,@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/course/getCourse?sessionKey="+sessionKey);
       // System.out.println("before call --> "+id);
        Course c = new Course();
        c.setId(Integer.parseInt(id));
        Course returns = restTemplate.postForObject(uri, c, Course.class);
        //System.out.println("After call --> "+returns);
		return returns;
	}
	
	@RequestMapping(value = "/modifyCourse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Status modifyCourse(@RequestBody Course course,@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/course/updateCourse?sessionKey="+sessionKey);
       // System.out.println("before call --> "+course);
        Status returns = restTemplate.postForObject(uri, course, Status.class);
        //System.out.println("After call --> "+returns);
		return returns;
	}
	
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Status deleteCourse(@RequestBody Course course,@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/course/removeCourse?id="+course.getId()+"&sessionKey="+sessionKey);
        System.out.println("before call --> "+course);
        Status returns = restTemplate.postForObject(uri, course, Status.class);
        System.out.println("After call --> "+returns);
		return returns;
	}
	
	@RequestMapping(value = "/registerCourse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Status registerCourse(@RequestBody Course course,@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		Registration reg = new Registration();
		reg.setCourseId(course.getId());
        String uri = new String("http://localhost:8081/CourseService/registerCourse/add?id="+course.getId()+"&sessionKey="+sessionKey);
        System.out.println("before call --> "+course);
        Status returns = restTemplate.postForObject(uri, reg, Status.class);
        System.out.println("After call --> "+returns);
		return returns;
	}
	
	@RequestMapping(value = "/getRegCourses", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  List<Course> getRegisterCourse(@RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/registerCourse/getRegCourses?myCourse=true&sessionKey="+sessionKey);
        System.out.println("before call in reg courses--> ");
        List<Course> returns = restTemplate.getForObject(uri, List.class);
        System.out.println("After call --> "+returns);
		return returns;
	}
	
	@RequestMapping(value = "/unregisterCourse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public  Status unRegisterCourse(@RequestBody Course course, @RequestParam String sessionKey){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String uri = new String("http://localhost:8081/CourseService/registerCourse/remove?sessionKey="+sessionKey);
        Registration reg = new Registration();
		reg.setCourseId(course.getId());
        System.out.println("before call --> ");
        Status returns = restTemplate.postForObject(uri, reg, Status.class);
        System.out.println("After call --> "+returns);
		return returns;
	}
}
