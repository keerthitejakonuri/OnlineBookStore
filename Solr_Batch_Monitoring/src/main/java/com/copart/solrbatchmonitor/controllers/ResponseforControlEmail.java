package com.copart.solrbatchmonitor.controllers;

import java.util.List;

import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerInfo;

public class ResponseforControlEmail {
	
		private List<SchedulerInfo> list;

		public List<SchedulerInfo> getList() {
			return list;
		}

		public void setList(List<SchedulerInfo> list) {
			this.list = list;
		}
		
		
}
