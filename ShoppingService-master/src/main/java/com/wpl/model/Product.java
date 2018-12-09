package com.wpl.model;

import java.io.Serializable;

public class Product implements Serializable{
	private String productName;
	private String category;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
	
}
