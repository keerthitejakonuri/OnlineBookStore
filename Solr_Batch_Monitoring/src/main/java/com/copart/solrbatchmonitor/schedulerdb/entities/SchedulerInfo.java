package com.copart.solrbatchmonitor.schedulerdb.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "scheduler_info",schema="guthite")
@IdClass(SchedulerInfoId.class)
public class SchedulerInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String object_type;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String data_center;
	
	public int scheduler_interval;
	
	public Date last_ran_at;
	
	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public String getData_center() {
		return data_center;
	}

	public void setData_center(String data_center) {
		this.data_center = data_center;
	}

	public int getScheduler_interval() {
		return scheduler_interval;
	}

	public void setScheduler_interval(int scheduler_interval) {
		this.scheduler_interval = scheduler_interval;
	}

	public Date getLast_ran_at() {
		return last_ran_at;
	}

	public void setLast_ran_at(Date last_ran_at) {
		this.last_ran_at = last_ran_at;
	}

	
}
