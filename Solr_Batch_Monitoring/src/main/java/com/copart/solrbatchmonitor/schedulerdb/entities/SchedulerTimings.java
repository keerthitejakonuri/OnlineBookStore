package com.copart.solrbatchmonitor.schedulerdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name="scheduler_info")
@SecondaryTable(name="Timings",
		pkJoinColumns={
	            @PrimaryKeyJoinColumn(name="object_type"),
	            @PrimaryKeyJoinColumn(name="data_center"),
	            @PrimaryKeyJoinColumn(name="batch_interval"),
	            @PrimaryKeyJoinColumn(name="Start_Time"),
	            @PrimaryKeyJoinColumn(name="End_Time")
	            })	
@IdClass(SchedulerTimingsId.class)

public class SchedulerTimings {
	@Id
	public String object_type;
	@Id
	public String data_center;
	
	public int scheduler_interval;
	@Id
	public int batch_interval;
	@Id
	public String Start_Time;
	@Id
	public String End_Time;
	
	public SchedulerTimings(){}
	
	public SchedulerTimings(String object_type, String data_center, int scheduler_interval, int batch_interval, String Start_Time, String End_Time){
		this.object_type = object_type;
		this.data_center = data_center;
		this.scheduler_interval = scheduler_interval;
		this.batch_interval = batch_interval;
		this.Start_Time = Start_Time;
		this.End_Time = End_Time;
	}
	
	
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
	public int getBatch_interval() {
		return batch_interval;
	}
	public void setBatch_interval(int batch_interval) {
		this.batch_interval = batch_interval;
	}
	public String getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(String start_Time) {
		Start_Time = start_Time;
	}
	public String getEnd_Time() {
		return End_Time;
	}
	public void setEnd_Time(String end_Time) {
		End_Time = end_Time;
	}
	
	
	
	
}
