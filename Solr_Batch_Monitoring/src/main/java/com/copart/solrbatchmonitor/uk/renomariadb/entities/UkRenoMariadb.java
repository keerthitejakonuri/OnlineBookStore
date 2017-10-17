package com.copart.solrbatchmonitor.uk.renomariadb.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "batch_search",schema="gbrprddb")
public class UkRenoMariadb {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int batch_id;
	
	@NotNull
	public Timestamp start_dttm;
	@NotNull
	public Timestamp end_dttm;
	@NotNull
	public Timestamp update_dttm;
	@NotNull
	public String object_count;
	@NotNull
	public String batch_status;
	@NotNull
	public String object_type;
	@NotNull
	public String user;
	@NotNull
	public String param;
	
	public UkRenoMariadb(){}
	
	public UkRenoMariadb(int batch_id, Timestamp start_dttm, Timestamp end_dttm, Timestamp update_dttm, String object_count, String batch_status, String object_type, String param){
		this.object_type = object_type;
		this.batch_id = batch_id;
		if(object_count != null){
			this.object_count = object_count;
		}else{
			this.object_count = object_count;
		}
		this.start_dttm = start_dttm;
		this.end_dttm = end_dttm;
		this.update_dttm = update_dttm;
		this.param = param;
		this.batch_status = batch_status;
	}
	

	public String getobject_type() {
        return object_type;
    }
	
	public void setobject_type(String object_type) {
        this.object_type = object_type;
    }
	
	public int getbatch_id() {
        return batch_id;
    }
	
	public void setbatch_id(int batch_id) {
        this.batch_id = batch_id;
    }
	
	public String getobject_count() {
        return object_count;
    }
	
	public void setobject_count(String object_count) {
			this.object_count = object_count;
		
    }
	
	
	public Timestamp getstart_dttm() {
        return start_dttm;
    }
	
	public void setstart_dttm(Timestamp start_dttm) {
        this.start_dttm = start_dttm;
    }
	
	public Timestamp getend_dttm() {
        return end_dttm;
    }
	
	public void setend_dttm(Timestamp end_dttm) {
        this.end_dttm = end_dttm;
    }
	
	public Timestamp getupdate_dttm() {
        return update_dttm;
    }
	
	public void setupdate_dttm(Timestamp update_dttm) {
        this.update_dttm = update_dttm;
    }
	
	public String getparam() {
        return param;
    }
	
	public void setparam(String param) {
        this.param = param;
    }
	
	public String getbatch_status() {
        return batch_status;
    }
	
	public void setbatch_status(String batch_status) {
        this.batch_status = batch_status.trim();
    }

}
