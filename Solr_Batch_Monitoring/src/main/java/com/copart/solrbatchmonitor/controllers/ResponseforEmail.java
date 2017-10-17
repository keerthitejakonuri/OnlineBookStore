package com.copart.solrbatchmonitor.controllers;

import java.util.List;

import com.copart.solrbatchmonitor.schedulerdb.entities.Emails;
public class ResponseforEmail {
	
		private List<Emails> list;

		public List<Emails> getList() {
			return list;
		}

		public void setList(List<Emails> list) {
			this.list = list;
		}
}
