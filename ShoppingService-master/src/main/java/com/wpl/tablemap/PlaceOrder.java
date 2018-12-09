package com.wpl.tablemap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "placeorder")
public class PlaceOrder {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="OrderId")
		private String orderId;
		
		@Column(name="UserName")
		private String userName;
		
		@Column(name="ProductId")
		private String productId;
		
		@Column(name="ProductQty")
		private String productQty;
		
		@Column(name="TotalPrice")
		private String totalPrice;
		
		@Column(name="OrderStatus")
		private String orderStatus;

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductQty() {
			return productQty;
		}

		public void setProductQty(String productQty) {
			this.productQty = productQty;
		}

		public String getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}


}
