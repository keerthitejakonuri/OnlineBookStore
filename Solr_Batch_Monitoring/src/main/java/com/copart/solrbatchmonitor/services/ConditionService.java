package com.copart.solrbatchmonitor.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerTimings;
import com.copart.solrbatchmonitor.schedulerdb.repositories.SchedulerdbRepository1;

@Service
public class ConditionService {
	
	@Autowired
	public SchedulerdbRepository1 schedulerdbRepository1;
	
	public ConditionService(){}
	
	public List<SchedulerTimings> viewConditions(){
		List<SchedulerTimings> list1 = schedulerdbRepository1.getAllConditions();
		List<SchedulerTimings> list = new ArrayList<SchedulerTimings>();
		for(int i = 0; i < list1.size(); i++){
			if(isNowInBetween(list1.get(i).Start_Time, list1.get(i).End_Time)){
				list.add(list1.get(i));
			}
		}
		return list;
	}
	
	public static boolean isNowInBetween(String initialTime, String finalTime) {
   	 try{
           boolean valid  = false;
           //Start Time
           java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
           Calendar calendar1 = Calendar.getInstance();
           calendar1.setTime(inTime);

         //Current Time
           DateFormat df = new SimpleDateFormat("HH:mm:ss");
           Date date = new Date();
           //System.out.println(df.format(date));
           java.util.Date curTime = new SimpleDateFormat("HH:mm:ss").parse(df.format(date));
           Calendar calendar2 = Calendar.getInstance();
           calendar2.setTime(curTime);

           //End Time
           java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
           Calendar calendar3 = Calendar.getInstance();
           calendar3.setTime(finTime);

          /* if(finalTime.compareTo(initialTime) < 0) {
               calendar2.add(Calendar.DATE, 1);
               calendar3.add(Calendar.DATE, 1);
           }*/
  
           java.util.Date actualTime = calendar2.getTime();
           if(((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime())==0)) && actualTime.before(calendar3.getTime())){
               valid = true;
           }
           return valid;
   	 }catch (ParseException e) {
	  	    e.printStackTrace();
	  	    return false;
	  	}

   }
}
