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

import com.copart.solrbatchmonitor.uk.colomariadb.entities.UkColoMariadb;
import com.copart.solrbatchmonitor.uk.colomariadb.repositories.UkColoMariadbRepository;


@Service
public class UkColoMariadbService {
	@Autowired
	private UkColoMariadbRepository ukColoMariadbRepository;
	private int noOfRecords;
	
	@Value("${spring.datasource.ukcolomariadb.conf_flag}")
	private boolean ukColoMariadb_conf_flag;
	
	
public UkColoMariadbService(){}
	//int x = 10;
	public List<UkColoMariadb> viewTable(){
    		UkColoMariadb dr1 = ukColoMariadbRepository.findTableLot();	
    		if(dr1 == null || !(ukColoMariadb_conf_flag)){
    			//System.out.println(dr1);
    			java.util.Date date= new java.util.Date();
    			 dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
    		}
    		UkColoMariadb dr2 = ukColoMariadbRepository.findTableBuyer();
    		if(dr2 == null || !(ukColoMariadb_conf_flag)){
    			//System.out.println(dr2);
    			java.util.Date date= new java.util.Date();
    			 dr2 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "buyer", "-1");
    		}
    		UkColoMariadb dr3 = ukColoMariadbRepository.findTableSeller();	
    		if(dr3 == null || !(ukColoMariadb_conf_flag)){
    			//System.out.println(dr3);
    			java.util.Date date= new java.util.Date();
    			 dr3 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "seller", "-1");
    		}
    		UkColoMariadb dr4 = ukColoMariadbRepository.findTableVideo();	
    		if(dr4 == null || !(ukColoMariadb_conf_flag)){
    			//System.out.println(dr4);
    			java.util.Date date= new java.util.Date();
    			 dr4 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "video", "-1");
    		}
    		UkColoMariadb dr5 = ukColoMariadbRepository.findTableAttr();	
    		if(dr5 == null || !(ukColoMariadb_conf_flag)){
    			//System.out.println(dr5);
    			java.util.Date date= new java.util.Date();
    			 dr5 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "attr", "-1");
    		}
    		List<UkColoMariadb> list = new ArrayList<UkColoMariadb>();
    		list.add(dr1);
    		list.add(dr2);
    		list.add(dr3);
    		list.add(dr4);
    		list.add(dr5);
    		this.noOfRecords = 5;
		return list;
	}
	
	public List<UkColoMariadb> makePagination(int offset, int noOfRecords, String pass){
		if(pass.equals("1") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list = ukColoMariadbRepository.findPageLot(offset, noOfRecords); 
			this.noOfRecords = ukColoMariadbRepository.findPageLotCount();
			return list;
		}
		else if(pass.equals("2") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list = ukColoMariadbRepository.findPageBuyer(offset, noOfRecords);
			this.noOfRecords = ukColoMariadbRepository.findPageBuyerCount();
			return list;
		}
		else if(pass.equals("3") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list = ukColoMariadbRepository.findPageSeller(offset, noOfRecords);
			this.noOfRecords = ukColoMariadbRepository.findPageSellerCount();
			return list;
		}
		else if(pass.equals("4") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list = ukColoMariadbRepository.findPageVideo(offset, noOfRecords);
			this.noOfRecords = ukColoMariadbRepository.findPageVideoCount();
			return list;
		}
		else if(pass.equals("6") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list = ukColoMariadbRepository.findPageAttr(offset, noOfRecords);
			this.noOfRecords = ukColoMariadbRepository.findPageAttrCount();
			return list;
		}
		else {
			List<UkColoMariadb> list = null;/*ukColoMariadbRepository.findPageLot(offset, noOfRecords);
			this.noOfRecords = ukColoMariadbRepository.findPageLotCount();*/
			return list;
		}
		
	}
	
	
	public List<UkColoMariadb> drawTodayGraph(String pass){
		if(pass.equals("1") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list1 = ukColoMariadbRepository.findTodayGraphLot(); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list2 = ukColoMariadbRepository.findTodayGraphBuyer();
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list3 = ukColoMariadbRepository.findTodayGraphSeller();
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list4 = ukColoMariadbRepository.findTodayGraphVideo();
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list5 = ukColoMariadbRepository.findTodayGraphAttr();
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UkColoMariadb> list = null; /*ukColoMariadbRepository.findTodayGraphLot();
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	
	public List<UkColoMariadb> drawRangeGraph(String pass,Date from,Date to){
		
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
		
		if(pass.equals("1") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list1 = ukColoMariadbRepository.findRangeGraphLot(from1, to1); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list2 = ukColoMariadbRepository.findRangeGraphBuyer(from1, to1);
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list3 = ukColoMariadbRepository.findRangeGraphSeller(from1, to1);
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list4 = ukColoMariadbRepository.findRangeGraphVideo(from1, to1);
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && ukColoMariadb_conf_flag){
			List<UkColoMariadb> list5 = ukColoMariadbRepository.findRangeGraphAttr(from1, to1);
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UkColoMariadb> list = null; /*ukColoMariadbRepository.findRangeGraphLot(from1, to1);
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	
	public int getNoOfRecords() {
		return noOfRecords;
	}
	
}
