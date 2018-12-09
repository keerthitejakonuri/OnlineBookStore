package com.wpl.tablemap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "productdetails")
public class ProductDetails {
	@Id
	@Column(name="ProductId")
	private String productId;
	
	@Column(name="ProductName")
	private String productName;
	
	@Column(name="ProductDescription")
	private String productDescription;
	
	@Column(name="ProductPrice")
	private String productPrice;
	
	@Column(name="Category")
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
