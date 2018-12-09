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

import com.wpl.tablemap.PaymentDetails;


@Controller
@RequestMapping("/payment")
@Transactional
public class PaymentService {
	@Autowired
	private SessionFactory sf;
	
	private static final Logger log = Logger.getLogger(LoginService.class);

	@RequestMapping(value="/save", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String saveOrder(@RequestBody PaymentDetails pay){

		log.info("Inside Payment Service"+pay.getOrderId());
		
		if(pay.getPaymentStatus() == null){
			pay.setPaymentStatus("PAID");
		}
		sf.getCurrentSession().save(pay);
		
		return "Saved";
	}
	
}
