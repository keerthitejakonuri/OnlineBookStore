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

import com.copart.solrbatchmonitor.us.renomariadb.entities.UsRenoMariadb;
import com.copart.solrbatchmonitor.us.renomariadb.repositories.UsRenoMariadbRepository;


@Service
public class UsRenoMariadbService {

	@Autowired
	private UsRenoMariadbRepository usRenoMariadbRepository; 
	private int noOfRecords;
	@Value("${spring.datasource.usrenomariadb.conf_flag}")
	private boolean usRenoMariadb_conf_flag;
	
	public UsRenoMariadbService(){}
	
	public List<UsRenoMariadb> viewTable(){
    		UsRenoMariadb dr1 = usRenoMariadbRepository.findTableLot();	
    		
    		if(dr1 == null || !(usRenoMariadb_conf_flag)){
    			//System.out.println(dr1);
    			java.util.Date date= new java.util.Date();
    			 dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
    		}
    		UsRenoMariadb dr2 = usRenoMariadbRepository.findTableBuyer();	
    		if(dr2 == null || !(usRenoMariadb_conf_flag)){
    			//System.out.println(dr2);
    			java.util.Date date= new java.util.Date();
    			 dr2 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "buyer", "-1");
    		}
    		UsRenoMariadb dr3 = usRenoMariadbRepository.findTableSeller();
    		if(dr3 == null || !(usRenoMariadb_conf_flag)){
    			//System.out.println(dr3);
    			java.util.Date date= new java.util.Date();
    			 dr3 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "seller", "-1");
    		}
    		UsRenoMariadb dr4 = usRenoMariadbRepository.findTableVideo();	
    		if(dr4 == null || !(usRenoMariadb_conf_flag)){
    			//System.out.println(dr4);
    			java.util.Date date= new java.util.Date();
    			 dr4 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "video", "-1");
    		}
    		UsRenoMariadb dr5 = usRenoMariadbRepository.findTableAttr();
    		if(dr5 == null || !(usRenoMariadb_conf_flag)){
    			//System.out.println(dr5);
    			java.util.Date date= new java.util.Date();
    			 dr5 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "attr", "-1");
    		}
    		List<UsRenoMariadb> list = new ArrayList<UsRenoMariadb>();
    		list.add(dr1);
    		list.add(dr2);
    		list.add(dr3);
    		list.add(dr4);
    		list.add(dr5);
    		this.noOfRecords = 5;
		return list;
	}
	
	
	public List<UsRenoMariadb> makePagination(int offset, int noOfRecords, String pass){
		if(pass.equals("1") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list1 = usRenoMariadbRepository.findPageLot(offset, noOfRecords); 
			this.noOfRecords = usRenoMariadbRepository.findPageLotCount();
			return list1;
		}
		else if(pass.equals("2") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list2 = usRenoMariadbRepository.findPageBuyer(offset, noOfRecords);
			this.noOfRecords = usRenoMariadbRepository.findPageBuyerCount();
			return list2;
		}
		else if(pass.equals("3") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list3 = usRenoMariadbRepository.findPageSeller(offset, noOfRecords);
			this.noOfRecords = usRenoMariadbRepository.findPageSellerCount();
			return list3;
		}
		else if(pass.equals("4") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list4 = usRenoMariadbRepository.findPageVideo(offset, noOfRecords);
			this.noOfRecords = usRenoMariadbRepository.findPageVideoCount();
			return list4;
		}
		else if(pass.equals("6") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list5 = usRenoMariadbRepository.findPageAttr(offset, noOfRecords);
			this.noOfRecords = usRenoMariadbRepository.findPageAttrCount();
			return list5;
		}
		else {
			List<UsRenoMariadb> list = null;/*usRenoMariadbRepository.findPageLot(offset, noOfRecords);
			this.noOfRecords = usRenoMariadbRepository.findPageLotCount();*/
			return list;
		}
		
	}
	
	public List<UsRenoMariadb> drawTodayGraph(String pass){
		if(pass.equals("1") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list1 = usRenoMariadbRepository.findTodayGraphLot(); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list2 = usRenoMariadbRepository.findTodayGraphBuyer();
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list3 = usRenoMariadbRepository.findTodayGraphSeller();
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list4 = usRenoMariadbRepository.findTodayGraphVideo();
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list5 = usRenoMariadbRepository.findTodayGraphAttr();
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UsRenoMariadb> list = null; /*usRenoMariadbRepository.findTodayGraphLot();
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	public List<UsRenoMariadb> drawRangeGraph(String pass,Date from,Date to){
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
		//System.out.println("From is "+from+" To is "+to);
		//System.out.println("From1 is "+from1+" To1 is "+to1);
		if(pass.equals("1") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list1 = usRenoMariadbRepository.findRangeGraphLot(from1, to1); 
			this.noOfRecords = list1.size();
			return list1;
		}
		else if(pass.equals("2") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list2 = usRenoMariadbRepository.findRangeGraphBuyer(from1, to1);
			this.noOfRecords = list2.size();
			return list2;
		}
		else if(pass.equals("3") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list3 = usRenoMariadbRepository.findRangeGraphSeller(from1, to1);
			this.noOfRecords = list3.size();
			return list3;
		}
		else if(pass.equals("4") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list4 = usRenoMariadbRepository.findRangeGraphVideo(from1, to1);
			this.noOfRecords = list4.size();
			return list4;
		}
		else if(pass.equals("6") && usRenoMariadb_conf_flag){
			List<UsRenoMariadb> list5 = usRenoMariadbRepository.findRangeGraphAttr(from1, to1);
			this.noOfRecords = list5.size();
			return list5;
		}
		else {
			List<UsRenoMariadb> list = null; /*usRenoMariadbRepository.findRangeGraphLot(from1, to1);
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	public int getNoOfRecords() {
			return noOfRecords;
		}
	
}
