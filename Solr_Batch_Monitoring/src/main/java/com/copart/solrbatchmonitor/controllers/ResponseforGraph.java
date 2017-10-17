package com.copart.solrbatchmonitor.controllers;

import java.util.List;

import com.copart.solrbatchmonitor.uk.colomariadb.entities.UkColoMariadb;
import com.copart.solrbatchmonitor.uk.db2.entities.UkDb2;
import com.copart.solrbatchmonitor.uk.renomariadb.entities.UkRenoMariadb;
import com.copart.solrbatchmonitor.us.colomariadb.entities.UsColoMariadb;
import com.copart.solrbatchmonitor.us.db2.entities.UsDb2;
import com.copart.solrbatchmonitor.us.renomariadb.entities.UsRenoMariadb;

public class ResponseforGraph {
	
	private List<UsRenoMariadb> list1;
	private List<UsColoMariadb> list2;
	private List<UkRenoMariadb> list3;
	private List<UkColoMariadb> list4;
	private List<UsDb2> list5;
	private List<UkDb2> list6;
		public void setList1(List<UsRenoMariadb> list1){
			this.list1 =list1;
		}
		public List<UsRenoMariadb> getList1(){
			return list1;
		}
		
		public void setList2(List<UsColoMariadb> list2){
			this.list2 =list2;
		}
		public List<UsColoMariadb> getList2(){
			return list2;
		}
		
		public void setList3(List<UkRenoMariadb> list3){
			this.list3 =list3;
		}
		public List<UkRenoMariadb> getList3(){
			return list3;
		}
		
		public void setList4(List<UkColoMariadb> list4){
			this.list4 =list4;
		}
		public List<UkColoMariadb> getList4(){
			return list4;
		}
		
		public void setList5(List<UsDb2> list5){
			this.list5 = list5;
		}
		public List<UsDb2> getList5(){
			return list5;
		}
		
		public void setList6(List<UkDb2> list6){
			this.list6 = list6;
		}
		public List<UkDb2> getList6(){
			return list6;
		}
	
}
