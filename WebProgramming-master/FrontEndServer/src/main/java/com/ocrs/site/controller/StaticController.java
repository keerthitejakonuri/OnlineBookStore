package com.ocrs.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vamsi Katepalli
 * LoginController is used to login to the application and 
 * has methods like login,validateSession etc. TODO: Expand the description once all information is available
 */
@Controller
@RequestMapping("/static")
public class StaticController {
	
	@RequestMapping(value = "/{pagePath}")
	public  String getPage(@PathVariable String pagePath){
		return pagePath;
	}
	
}
