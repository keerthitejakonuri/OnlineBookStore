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

import com.copart.solrbatchmonitor.uk.db2.entities.UkDb2;
import com.copart.solrbatchmonitor.uk.db2.repositories.UkDb2Repository;




@Service
public class UkDb2Service {
	@Autowired
	private UkDb2Repository ukDb2Repository;
	private int noOfRecords;
	

	@Value("${spring.datasource.ukdb2.conf_flag}")
	private boolean ukDb2_conf_flag;
	
	
	
	
	public UkDb2Service(){}
	
	public List<UkDb2> viewTable(String profile){
		this.noOfRecords = 1;
		if(Integer.valueOf(profile) == 3 ){
		UkDb2 ukDb2 = ukDb2Repository.findTableImgReno();
		if(ukDb2 == null || !(ukDb2_conf_flag)){
			//System.out.println(ukDb2);
			java.util.Date date= new java.util.Date();
			ukDb2 = new UkDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "image_reno", "-1");
		}
		List<UkDb2> list = new ArrayList<>();
		list.add(ukDb2);
		return list;
		}
		else if(Integer.valueOf(profile) == 4  ){
			//System.out.println("Entered IMGCOLO");
			UkDb2 ukDb2 = ukDb2Repository.findTableImgColo();
			if(ukDb2 == null || !(ukDb2_conf_flag)){
				//System.out.println(ukDb2);
				java.util.Date date= new java.util.Date();
				ukDb2 = new UkDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "image_colo", "-1");
			}
			List<UkDb2> list = new ArrayList<>();
			list.add(ukDb2);
			return list;
		}
		else{
			UkDb2 ukDb2 = ukDb2Repository.findTableImgReno();
			if(ukDb2 == null || !(ukDb2_conf_flag)){
				//System.out.println(ukDb2);
				java.util.Date date= new java.util.Date();
				ukDb2 = new UkDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "image_reno", "-1");
			}
			List<UkDb2> list = new ArrayList<>();
			list.add(ukDb2);
			return list;
		}
	}
	
	public List<UkDb2> makePagination(int offset, int noOfRecords, String option, String profile){
		if(Integer.valueOf(profile) == 3 && ukDb2_conf_flag){
			List<UkDb2> list = ukDb2Repository.findPageImgReno(offset, offset + noOfRecords);
			this.noOfRecords = ukDb2Repository.findPageImgRenoCount();
			return list;
		}
		else if(Integer.valueOf(profile) == 4 && ukDb2_conf_flag){
			List<UkDb2> list = ukDb2Repository.findPageImgColo(offset, offset + noOfRecords);
			this.noOfRecords = ukDb2Repository.findPageImgColoCount();
			return list;
		}
		else {
			List<UkDb2> list = null;/*ukDb2Repository.findPageImgReno(offset, offset + noOfRecords);
			this.noOfRecords = ukDb2Repository.findPageImgRenoCount();*/
			return list;
		}
	}
	
	
	public List<UkDb2> drawTodayGraph(String profile){
		if(Integer.valueOf(profile) == 3 && ukDb2_conf_flag){
			List<UkDb2> list = ukDb2Repository.findTodayGraphImgReno();
			this.noOfRecords = list.size();
			return list;
		}
		else if(Integer.valueOf(profile) == 4 && ukDb2_conf_flag){
			List<UkDb2> list = ukDb2Repository.findTodayGraphImgColo();
			this.noOfRecords = list.size();
			return list;
		}
		else {
			List<UkDb2> list = null; /*ukDb2Repository.findTodayGraphImgReno();
			this.noOfRecords = list.size();*/
			return list;
		}
	}

	
	public List<UkDb2> drawRangeGraph(String profile, Date from, Date to){
		
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
		
		
		if(Integer.valueOf(profile) == 3 && ukDb2_conf_flag){
			List<UkDb2> list = ukDb2Repository.findRangeGraphImgReno(from1,to1);
			this.noOfRecords = list.size();
			return list;
		}
		else if(Integer.valueOf(profile) == 4 && ukDb2_conf_flag){
			List<UkDb2> list = ukDb2Repository.findRangeGraphImgReno(from1,to1);
			this.noOfRecords = list.size();
			return list;
		}
		else {
			List<UkDb2> list = null; /*ukDb2Repository.findRangeGraphImgReno(from1,to1);
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	
	public int getNoOfRecords() {	
		return noOfRecords;
	}

	
}
