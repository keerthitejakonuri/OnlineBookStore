package com.copart.solrbatchmonitor.controllers;

import java.util.List;

import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerTimings;

public class ResponseforConditions {
	public List<SchedulerTimings> list;
	public void setList(List<SchedulerTimings> list){
		this.list =list;
	}
	public List<SchedulerTimings> getList(){
		return list;
	}
}
