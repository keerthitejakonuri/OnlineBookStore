package com.wpl.services;

import java.util.ArrayList;
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

import com.wpl.model.Product;
import com.wpl.tablemap.ProductDetails;


@Controller
@RequestMapping("/product_search")
@Transactional
public class ProductService {
	@Autowired
	private SessionFactory sf;
	
	private static final Logger log = Logger.getLogger(LoginService.class);

	@RequestMapping(value="/search", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductDetails> checkLogin(@RequestBody Product product){
		List<ProductDetails> prod = new ArrayList<>();
		log.info("Inside Product Service"+product.getProductName());
		if(product.getCategory() == null && product.getProductName() != null){
			//Criteria cr = sf.getCurrentSession().createCriteria(ProductDetails.class);
			Query query = sf.getCurrentSession().createQuery("FROM ProductDetails pd WHERE pd.productName like '%"+product.getProductName()+"%'" );
			prod = query.list();
		} 
		else if(product.getCategory() != null && product.getProductName() != null){
			Query query = sf.getCurrentSession().createQuery("FROM ProductDetails pd WHERE pd.productName like '%"+product.getProductName()+"%' AND pd.category='"+ product.getCategory()+"'" );
			prod = (query.list());
		}
		else if(product.getCategory() == null && product.getProductName() == null){
			Query query = sf.getCurrentSession().createQuery("FROM ProductDetails" );
			prod = (query.list());
		}
		log.info("Prod Size Service: "+prod.size());
		return prod;
	}

}
