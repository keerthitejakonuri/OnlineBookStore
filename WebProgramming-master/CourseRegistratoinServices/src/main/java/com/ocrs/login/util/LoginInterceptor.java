package com.ocrs.login.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ocrs.login.controller.LoginSessionBean;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private LoginSessionBean loginSessionBean;
	
	public LoginSessionBean getLoginSessionBean() {
		return loginSessionBean;
	}

	public void setLoginSessionBean(LoginSessionBean loginSessionBean) {
		this.loginSessionBean = loginSessionBean;
	}

	private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler)
	    throws Exception {
		
		String sessionKey = request.getParameter("sessionKey");
		System.out.println(sessionKey);
		if(loginSessionBean.getSessionMap().containsKey(sessionKey))
			return true;
		else{
			response.sendError(response.SC_FORBIDDEN);
			return false;
		}
		
	}

	//after the handler is executed
	public void postHandle(
		HttpServletRequest request, HttpServletResponse response, 
		Object handler, ModelAndView modelAndView)
		throws Exception {
	}
}

