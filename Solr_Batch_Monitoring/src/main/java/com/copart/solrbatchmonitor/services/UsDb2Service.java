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

import com.copart.solrbatchmonitor.us.db2.entities.UsDb2;
import com.copart.solrbatchmonitor.us.db2.repositories.UsDb2Repository;
//import com.copart.solrbatchmonitor.us.renomariadb.entities.UsRenoMariadb;


@Service
public class UsDb2Service {
	@Autowired
	private UsDb2Repository usDb2Repository;
	private int noOfRecords;
	
	@Value("${spring.datasource.usdb2.conf_flag}")
	private boolean usDb2_conf_flag;
	
	
	public UsDb2Service(){}
	
	public List<UsDb2> viewTable(String profile){
		//System.out.println(usDb2Repository);
		this.noOfRecords = 1;
		if(Integer.valueOf(profile) == 1  && usDb2_conf_flag){
			
		UsDb2 usDb2 = usDb2Repository.findTableImgReno();
		if(usDb2 == null || !(usDb2_conf_flag)){
			//System.out.println(usDb2);
			java.util.Date date= new java.util.Date();
			usDb2 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "image_reno", "-1");
		}
		List<UsDb2> list = new ArrayList<>();
		list.add(usDb2);
		return list;
		}
		else if(Integer.valueOf(profile) == 2  && usDb2_conf_flag){
			//System.out.println("Entered IMGCOLO");
			UsDb2 usDb2 = usDb2Repository.findTableImgColo();
			//System.out.println("Entered IMG COLO "+usDb2);
			if(usDb2 == null || !(usDb2_conf_flag)){
				//System.out.println(usDb2);
				java.util.Date date= new java.util.Date();
				usDb2 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "image_colo", "-1");
			}
			List<UsDb2> list = new ArrayList<>();
			list.add(usDb2);
			return list;
		}
		else{
			UsDb2 usDb2 = usDb2Repository.findTableImgReno();
			if(usDb2 == null || !(usDb2_conf_flag)){
				//System.out.println(usDb2);
				java.util.Date date= new java.util.Date();
				usDb2 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "image_reno", "-1");
			}
			List<UsDb2> list = new ArrayList<>();
			list.add(usDb2);
			return list;
		}
	}
	
	public List<UsDb2> makePagination(int offset, int noOfRecords, String option, String profile){
		if(Integer.valueOf(profile) == 1 && usDb2_conf_flag){
			List<UsDb2> list = usDb2Repository.findPageImgReno(offset, offset + noOfRecords);
			this.noOfRecords = usDb2Repository.findPageImgRenoCount();
			return list;
		}
		else if(Integer.valueOf(profile) == 2 && usDb2_conf_flag){
			List<UsDb2> list = usDb2Repository.findPageImgColo(offset, offset + noOfRecords);
			this.noOfRecords = usDb2Repository.findPageImgColoCount();
			return list;
		}
		else {
			List<UsDb2> list = null;/*usDb2Repository.findPageImgReno(offset, offset + noOfRecords);
			this.noOfRecords = usDb2Repository.findPageImgRenoCount();*/
			return list;
		}
	}
	
	public List<UsDb2> drawTodayGraph(String profile){
		if(Integer.valueOf(profile) == 1 && usDb2_conf_flag){
			//System.out.println("Entered Profile 1 and pass 5");
			List<UsDb2> list = usDb2Repository.findTodayGraphImgReno();
			this.noOfRecords = list.size();
			return list;
		}
		else if(Integer.valueOf(profile) == 2 && usDb2_conf_flag){
			List<UsDb2> list = usDb2Repository.findTodayGraphImgColo();
			this.noOfRecords = list.size();
			return list;
		}
		else {
			List<UsDb2> list = null; /*usDb2Repository.findTodayGraphImgReno();
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	public List<UsDb2> drawRangeGraph(String profile, Date from, Date to){
		
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
		
		if(Integer.valueOf(profile) == 1 && usDb2_conf_flag){
			//System.out.println("Entered Profile 1 and pass 5");
			List<UsDb2> list = usDb2Repository.findRangeGraphImgReno(from1, to1);
			this.noOfRecords = list.size();
			return list;
		}
		else if(Integer.valueOf(profile) == 2 && usDb2_conf_flag){
			List<UsDb2> list = usDb2Repository.findRangeGraphImgColo(from1, to1);
			this.noOfRecords = list.size();
			return list;
		}
		else {
			List<UsDb2> list = null;/*usDb2Repository.findRangeGraphImgReno(from1, to1);
			this.noOfRecords = list.size();*/
			return list;
		}
	}
	
	public int getNoOfRecords() {	
		return noOfRecords;
	}

}
