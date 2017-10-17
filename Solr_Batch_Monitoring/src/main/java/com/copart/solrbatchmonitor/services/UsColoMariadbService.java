package com.copart.solrbatchmonitor.services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.copart.solrbatchmonitor.us.colomariadb.entities.UsColoMariadb;
import com.copart.solrbatchmonitor.us.colomariadb.repositories.UsColoMariadbRepository;



@Service
public class UsColoMariadbService {
	@Autowired
	private UsColoMariadbRepository usColoMariadbRepository;
	
	private int noOfRecords;
	
	@Value("${spring.datasource.uscolomariadb.conf_flag}")
	private boolean usColoMariadb_conf_flag;
public UsColoMariadbService(){}
	
	public List<UsColoMariadb> viewTable(){
    		UsColoMariadb dr1 = usColoMariadbRepository.findTableLot();	
    		if(dr1 == null || !(usColoMariadb_conf_flag)){
    			//System.out.println(dr1);
    			java.util.Date date= new java.util.Date();
    			 dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
    		}
    		UsColoMariadb dr2 = usColoMariadbRepository.findTableBuyer();
    		if(dr2 == null || !(usColoMariadb_conf_flag)){
    			//System.out.println(dr2);
    			java.util.Date date= new java.util.Date();
    			 dr2 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "buyer", "-1");
    		}
    		UsColoMariadb dr3 = usColoMariadbRepository.findTableSeller();	
    		if(dr3 == null || !(usColoMariadb_conf_flag)){
    			//System.out.println(dr3);
    			java.util.Date date= new java.util.Date();
    			 dr3 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "seller", "-1");
    		}
    		UsColoMariadb dr4 = usColoMariadbRepository.findTableVideo();	
    		if(dr4 == null || !(usColoMariadb_conf_flag)){
    			//System.out.println(dr4);
    			java.util.Date date= new java.util.Date();
    			 dr4 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "video", "-1");
    		}
    		UsColoMariadb dr5 = usColoMariadbRepository.findTableAttr();	
    		if(dr5 == null || !(usColoMariadb_conf_flag)){
    			//System.out.println(dr5);
    			java.util.Date date= new java.util.Date();
    			 dr5 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "attr", "-1");
    		}
    		List<UsColoMariadb> list = new ArrayList<UsColoMariadb>();
    		list.add(dr1);
    		list.add(dr2);
    		list.add(dr3);
    		list.add(dr4);
    		list.add(dr5);
    		this.noOfRecords = 5;
		return list;
	}
	
	public List<UsColoMariadb> makePagination(int offset, int noOfRecords, String pass){
		if(pass.equals("1") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list = usColoMariadbRepository.findPageLot(offset, noOfRecords); 
			this.noOfRecords = usColoMariadbRepository.findPageLotCount();
			return list;
		}
		else if(pass.equals("2") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list = usColoMariadbRepository.findPageBuyer(offset, noOfRecords);
			this.noOfRecords = usColoMariadbRepository.findPageBuyerCount();
			return list;
		}
		else if(pass.equals("3") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list = usColoMariadbRepository.findPageSeller(offset, noOfRecords);
			this.noOfRecords = usColoMariadbRepository.findPageSellerCount();
			return list;
		}
		else if(pass.equals("4") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list = usColoMariadbRepository.findPageVideo(offset, noOfRecords);
			this.noOfRecords = usColoMariadbRepository.findPageVideoCount();
			return list;
		}
		else if(pass.equals("6") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list = usColoMariadbRepository.findPageAttr(offset, noOfRecords);
			this.noOfRecords = usColoMariadbRepository.findPageAttrCount();
			return list;
		}
		else {
			List<UsColoMariadb> list = null;/*usColoMariadbRepository.findPageLot(offset, noOfRecords);
			this.noOfRecords = usColoMariadbRepository.findPageLotCount();*/
			return list;
		}
		
	}
	
	
	public List<UsColoMariadb> drawTodayGraph(String pass){
		if(pass.equals("1") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list1 = usColoMariadbRepository.findTodayGraphLot(); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list2 = usColoMariadbRepository.findTodayGraphBuyer();
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list3 = usColoMariadbRepository.findTodayGraphSeller();
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list4 = usColoMariadbRepository.findTodayGraphVideo();
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list5 = usColoMariadbRepository.findTodayGraphAttr();
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UsColoMariadb> list = null; /*usColoMariadbRepository.findTodayGraphLot();
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	public List<UsColoMariadb> drawRangeGraph(String pass,Date from,Date to){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date from1,to1;
		try {
		from1 = df.parse(new SimpleDateFormat("yyyy-MM-dd").format(from));
		}catch (ParseException e) {
	        e.printStackTrace();
	        from1 = from;
	    }
		try {
			to1 = df.parse(new SimpleDateFormat("yyyy-MM-dd").format(to));
			}catch (ParseException e) {
		        e.printStackTrace();
		        to1 = to;
		    }
		
		if(pass.equals("1") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list1 = usColoMariadbRepository.findRangeGraphLot(from1, to1); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list2 = usColoMariadbRepository.findRangeGraphBuyer(from1, to1);
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list3 = usColoMariadbRepository.findRangeGraphSeller(from1, to1);
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list4 = usColoMariadbRepository.findRangeGraphVideo(from1, to1);
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && usColoMariadb_conf_flag){
			List<UsColoMariadb> list5 = usColoMariadbRepository.findRangeGraphAttr(from1, to1);
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UsColoMariadb> list = null; /*usColoMariadbRepository.findRangeGraphLot(from1, to1);
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	
	public int getNoOfRecords() {
		return noOfRecords;
	}
	
}
