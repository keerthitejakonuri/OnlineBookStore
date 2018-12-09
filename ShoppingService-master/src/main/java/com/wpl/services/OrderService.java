package com.wpl.services;

import java.util.List;

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

import com.wpl.tablemap.PlaceOrder;


@Controller
@RequestMapping("/order")
@Transactional
public class OrderService {
	@Autowired
	private SessionFactory sf;
	
	private static final Logger log = Logger.getLogger(LoginService.class);

	@RequestMapping(value="/save", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String saveOrder(@RequestBody PlaceOrder order){

		log.info("Inside Order Service"+order.getProductId());
		if(order.getOrderStatus() == null){
			order.setOrderStatus("CONFIRMED");
		}
		String orderId = (String) sf.getCurrentSession().save(order);
		
		return orderId;
	}
	@RequestMapping(value="/get", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PlaceOrder> getOrders(@RequestBody String userName){
		userName = userName.trim();
		log.info("Inside Get Order Service: "+userName);
		
		Query query = sf.getCurrentSession().createQuery("FROM PlaceOrder p WHERE p.userName='"+userName+"'");
		List<PlaceOrder> orderList = query.list();
		return orderList;
	}

}
