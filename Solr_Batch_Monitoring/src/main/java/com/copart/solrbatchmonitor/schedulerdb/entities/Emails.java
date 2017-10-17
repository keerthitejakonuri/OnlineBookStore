package com.copart.solrbatchmonitor.schedulerdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Emails_Info",schema="guthite")
public class Emails {
	@Id
	public String email_id;
	public String type;
	
	public Emails(){};
	
	public Emails(String email_id, String type) {
		super();
		this.email_id = email_id;
		this.type = type;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
