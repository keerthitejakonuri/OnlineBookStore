package com.copart.solrbatchmonitor.schedulerdb.entities;

import java.io.Serializable;

public class SchedulerTimingsId implements Serializable{
	
	public String object_type;
	
	public String data_center;
	
	public int batch_interval;
	
	public String Start_Time;
	
	public String End_Time;
}
