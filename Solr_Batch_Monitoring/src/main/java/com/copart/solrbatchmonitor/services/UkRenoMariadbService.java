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

import com.copart.solrbatchmonitor.uk.renomariadb.entities.UkRenoMariadb;
import com.copart.solrbatchmonitor.uk.renomariadb.repositories.UkRenoMariadbRepository;





@Service
public class UkRenoMariadbService {
	@Autowired
	private UkRenoMariadbRepository ukRenoMariadbRepository; 
	private int noOfRecords;
	
	@Value("${spring.datasource.ukrenomariadb.conf_flag}")
	private boolean ukRenoMariadb_conf_flag;
	
	public UkRenoMariadbService(){}
	
	public List<UkRenoMariadb> viewTable(){
    		UkRenoMariadb dr1 = ukRenoMariadbRepository.findTableLot();	
    		if(dr1 == null || !(ukRenoMariadb_conf_flag)){
    			//System.out.println(dr1);
    			java.util.Date date= new java.util.Date();
    			 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
    		}
    		UkRenoMariadb dr2 = ukRenoMariadbRepository.findTableBuyer();	
    		if(dr2 == null || !(ukRenoMariadb_conf_flag)){
    			//System.out.println(dr2);
    			java.util.Date date= new java.util.Date();
    			 dr2 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "buyer", "-1");
    		}
    		UkRenoMariadb dr3 = ukRenoMariadbRepository.findTableSeller();	
    		if(dr3 == null || !(ukRenoMariadb_conf_flag)){
    			//System.out.println(dr3);
    			java.util.Date date= new java.util.Date();
    			 dr3 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "seller", "-1");
    		}
    		UkRenoMariadb dr4 = ukRenoMariadbRepository.findTableVideo();
    		if(dr4 == null || !(ukRenoMariadb_conf_flag)){
    			//System.out.println(dr4);
    			java.util.Date date= new java.util.Date();
    			 dr4 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "video", "-1");
    		}
    		UkRenoMariadb dr5 = ukRenoMariadbRepository.findTableAttr();
    		if(dr5 == null || !(ukRenoMariadb_conf_flag)){
    			//System.out.println(dr5);
    			java.util.Date date= new java.util.Date();
    			 dr5 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "attr", "-1");
    		}
    		List<UkRenoMariadb> list = new ArrayList<UkRenoMariadb>();
    		list.add(dr1);
    		list.add(dr2);
    		list.add(dr3);
    		list.add(dr4);
    		list.add(dr5);
    		this.noOfRecords = 5;
		return list;
	}
	
	public List<UkRenoMariadb> makePagination(int offset, int noOfRecords, String pass){
		if(pass.equals("1") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list = ukRenoMariadbRepository.findPageLot(offset, noOfRecords);
			this.noOfRecords = ukRenoMariadbRepository.findPageLotCount();
			return list;
		}
		else if(pass.equals("2") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list = ukRenoMariadbRepository.findPageBuyer(offset, noOfRecords);
			this.noOfRecords = ukRenoMariadbRepository.findPageBuyerCount();
			return list;
		}
		else if(pass.equals("3") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list = ukRenoMariadbRepository.findPageSeller(offset, noOfRecords);
			this.noOfRecords = ukRenoMariadbRepository.findPageSellerCount();
			return list;
		}
		else if(pass.equals("4") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list = ukRenoMariadbRepository.findPageVideo(offset, noOfRecords);
			this.noOfRecords = ukRenoMariadbRepository.findPageVideoCount();
			return list;
		}
		else if(pass.equals("6") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list = ukRenoMariadbRepository.findPageAttr(offset, noOfRecords);
			this.noOfRecords = ukRenoMariadbRepository.findPageAttrCount();
			return list;
		}
		else {
			List<UkRenoMariadb> list = null;/*ukRenoMariadbRepository.findPageLot(offset, noOfRecords);
			this.noOfRecords = ukRenoMariadbRepository.findPageLotCount();*/
			return list;
		}
		
	}
	
	public List<UkRenoMariadb> drawTodayGraph(String pass){
		if(pass.equals("1") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list1 = ukRenoMariadbRepository.findTodayGraphLot(); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list2 = ukRenoMariadbRepository.findTodayGraphBuyer();
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list3 = ukRenoMariadbRepository.findTodayGraphSeller();
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list4 = ukRenoMariadbRepository.findTodayGraphVideo();
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list5 = ukRenoMariadbRepository.findTodayGraphAttr();
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UkRenoMariadb> list = null; /*ukRenoMariadbRepository.findTodayGraphLot();
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	public List<UkRenoMariadb> drawRangeGraph(String pass,Date from,Date to){
		
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
		
		if(pass.equals("1") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list1 = ukRenoMariadbRepository.findRangeGraphLot(from1, to1); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list2 = ukRenoMariadbRepository.findRangeGraphBuyer(from1, to1);
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list3 = ukRenoMariadbRepository.findRangeGraphSeller(from1, to1);
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list4 = ukRenoMariadbRepository.findRangeGraphVideo(from1, to1);
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && ukRenoMariadb_conf_flag){
			List<UkRenoMariadb> list5 = ukRenoMariadbRepository.findRangeGraphAttr(from1, to1);
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UkRenoMariadb> list = null; /*ukRenoMariadbRepository.findRangeGraphLot(from1, to1);
			this.noOfRecords = list.size(); */
			return list;
		}
	}
	
	public int getNoOfRecords() {
		return noOfRecords;
	}
	
}
