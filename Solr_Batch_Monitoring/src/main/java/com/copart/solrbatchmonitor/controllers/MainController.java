package com.copart.solrbatchmonitor.controllers;

import java.util.ArrayList;
import java.util.Date;
//import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.copart.solrbatchmonitor.schedulerdb.entities.Emails;
import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerInfo;
import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerTimings;
import com.copart.solrbatchmonitor.services.ConditionService;
import com.copart.solrbatchmonitor.services.ControlEmailService;
import com.copart.solrbatchmonitor.services.UkColoMariadbService;
import com.copart.solrbatchmonitor.services.UkDb2Service;
import com.copart.solrbatchmonitor.services.UkRenoMariadbService;
import com.copart.solrbatchmonitor.services.UsColoMariadbService;
import com.copart.solrbatchmonitor.services.UsDb2Service;
import com.copart.solrbatchmonitor.services.UsRenoMariadbService;
import com.copart.solrbatchmonitor.uk.colomariadb.entities.UkColoMariadb;
import com.copart.solrbatchmonitor.uk.db2.entities.UkDb2;
import com.copart.solrbatchmonitor.uk.renomariadb.entities.UkRenoMariadb;
import com.copart.solrbatchmonitor.us.colomariadb.entities.UsColoMariadb;
import com.copart.solrbatchmonitor.us.db2.entities.UsDb2;
import com.copart.solrbatchmonitor.us.renomariadb.entities.UsRenoMariadb;


@Controller
public class MainController {
	@Autowired
	private UsRenoMariadbService usRenoMariadbService;
	@Autowired
	private UsColoMariadbService usColoMariadbService;
	@Autowired
	private UsDb2Service usDb2Service;
	@Autowired
	private UkRenoMariadbService ukRenoMariadbService;
	@Autowired
	private UkColoMariadbService ukColoMariadbService;
	@Autowired
	private UkDb2Service ukDb2Service;
	@Autowired
	private ControlEmailService controlEmailService;
	@Autowired
	private ConditionService conditionService;
	
	@Value("${spring.profiles}")
	private String profileFromYML;
	@Value("${spring.datasource.ukrenomariadb.conf_flag}")
	private boolean ukRenoMariadb_conf_flag;
	@Value("${spring.datasource.usrenomariadb.conf_flag}")
	private boolean usRenoMariadb_conf_flag;
	@Value("${spring.datasource.usdb2.conf_flag}")
	private boolean usDb2_conf_flag;
	@Value("${spring.datasource.uscolomariadb.conf_flag}")
	private boolean usColoMariadb_conf_flag;
	@Value("${spring.datasource.ukcolomariadb.conf_flag}")
	private boolean ukColoMariadb_conf_flag;
	
	//public static String fileName = System.getProperty("user.dir")+"/email.csv";
	//public static String fileName = "/email.csv";
	
	@RequestMapping(value = "/index", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET )
	public ResponseEntity<ResponseforTable> getTableValues(Model model, @RequestParam(value="profile", required=true, defaultValue="1") String profile){
		//System.out.println("profileFromYML is "+profileFromYML);
		List<ProfileFromYML> list7 = new ArrayList<ProfileFromYML>();
		ProfileFromYML pf = new ProfileFromYML(profileFromYML, ukRenoMariadb_conf_flag, usRenoMariadb_conf_flag, usDb2_conf_flag, usColoMariadb_conf_flag, ukColoMariadb_conf_flag);
		list7.add(pf);
		if((Integer.valueOf(profile) == 1)){
			//System.out.println("Entered profile 1");
			List<UsRenoMariadb> usRenoMariadb = usRenoMariadbService.viewTable();
			List<UsDb2> usDb2 = usDb2Service.viewTable(profile);
				ResponseforTable rst = new ResponseforTable();
				rst.setList1(usRenoMariadb);
				rst.setList5(usDb2);
				rst.setList7(list7);
				if(usRenoMariadb.isEmpty()){
		            return new ResponseEntity<ResponseforTable>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<ResponseforTable>(rst, HttpStatus.OK);
			
		}
		else if(Integer.valueOf(profile) == 2){
			//System.out.println("Entered profile 2");
			List<UsColoMariadb> usColoMariadb = usColoMariadbService.viewTable();
			List<UsDb2> usDb2 = usDb2Service.viewTable(profile);
			ResponseforTable rst = new ResponseforTable();
			rst.setList2(usColoMariadb);
			rst.setList5(usDb2);
			rst.setList7(list7);
				if(usColoMariadb.isEmpty()){
		            return new ResponseEntity<ResponseforTable>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<ResponseforTable>(rst, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3){
				//System.out.println("Entered profile 3");
				
				List<UkDb2> ukDb2 = ukDb2Service.viewTable(profile);
				List<UkRenoMariadb> ukRenoMariadb = ukRenoMariadbService.viewTable();
					ResponseforTable rst = new ResponseforTable();
					rst.setList3(ukRenoMariadb);
					rst.setList6(ukDb2);
					rst.setList7(list7);
				if(ukRenoMariadb.isEmpty()){
		            return new ResponseEntity<ResponseforTable>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<ResponseforTable>(rst, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 4){
			//System.out.println("Entered profile 4");
			List<UkColoMariadb> ukColoMariadb = ukColoMariadbService.viewTable();
			List<UkDb2> ukDb2 = ukDb2Service.viewTable(profile);
			ResponseforTable rst = new ResponseforTable();
			rst.setList4(ukColoMariadb);
			rst.setList6(ukDb2);
			rst.setList7(list7);
				if(ukColoMariadb.isEmpty()){
					
		            return new ResponseEntity<ResponseforTable>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<ResponseforTable>(rst, HttpStatus.OK);
		}
		else {
			//System.out.println("Default Profile");
			List<UsRenoMariadb> usRenoMariadb = usRenoMariadbService.viewTable();
			List<UsDb2> usDb2 = usDb2Service.viewTable(profile);
				ResponseforTable rst = new ResponseforTable();
				rst.setList1(usRenoMariadb);
				rst.setList5(usDb2);
				rst.setList7(list7);
				if(usRenoMariadb.isEmpty()){
		            return new ResponseEntity<ResponseforTable>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<ResponseforTable>(rst, HttpStatus.OK);
			
		}
		
	}
	
	
	@RequestMapping(value="/getconditions", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET)
	public ResponseEntity<ResponseforConditions> getAllConditions(){
		List<SchedulerTimings> list = conditionService.viewConditions();
		//System.out.println(list);
		ResponseforConditions rsc = new ResponseforConditions();
		rsc.setList(list);
		if(list.isEmpty()){
            return new ResponseEntity<ResponseforConditions>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<ResponseforConditions>(rsc, HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/solr", produces = {MediaType.TEXT_HTML_VALUE}, method= RequestMethod.GET)
	public String viewDetails1(Model model){
		model.addAttribute("profileFromYML", profileFromYML);
		return "solr_batch_monitor";
	}
	
	/*@RequestMapping(value="/solrbatchmonitor", produces = {MediaType.TEXT_HTML_VALUE}, method= RequestMethod.GET)
	public String viewDetails(Model model){
		model.addAttribute("profileFromYML", profileFromYML);
		return "hello";
	}*/
	
	@RequestMapping(value="/stopemail")
	public String stopemails(Model model){
		model.addAttribute("profileFromYML", profileFromYML);
		return "control_emails";
	}
	
	@RequestMapping(value="/deleteemail", method= RequestMethod.POST)
	public String deleteEmail(Model model, @RequestParam(value="email_id", defaultValue="keerthi") String email_id){
		//System.out.println("email deleted is "+email_id);
		//email = email.trim();
		 controlEmailService.deleteEmailsInfo(email_id);
		return "control_emails";
	}
	
	@RequestMapping(value = "/getemail", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET )
	public ResponseEntity<ResponseforEmail> getEmail(Model model){
		ResponseforEmail rs = new ResponseforEmail();
		List<Emails> list = controlEmailService.getAllReceivers();
		rs.setList(list);
		if(list.isEmpty()){
            return new ResponseEntity<ResponseforEmail>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<ResponseforEmail>(rs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getemaildetails", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET )
	public ResponseEntity<ResponseforControlEmail> getEmails(Model model){
		ResponseforControlEmail rs = new ResponseforControlEmail();
		List<SchedulerInfo> list = controlEmailService.getSchedulerInfo();
		rs.setList(list);
		if(list.isEmpty()){
            return new ResponseEntity<ResponseforControlEmail>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<ResponseforControlEmail>(rs, HttpStatus.OK);
	}
	
	@RequestMapping(value="/controlemails", method= RequestMethod.POST)
	public String controlEmails(Model model, @RequestParam(value="value", defaultValue="100") int value, @RequestParam(value="obj", defaultValue="LOT") String obj, @RequestParam(value="profile", defaultValue="100") int profile, @RequestParam(value="email", defaultValue="keerthi.konuri@copart.com") String email){
		
		if(obj.equals("lot")){
			if(profile == 1){
				controlEmailService.updateLotUsRenoScheduler(value);
			}
			else if(profile == 2){
				controlEmailService.updateLotUsColoScheduler(value);
			}
			else if(profile == 3){
				controlEmailService.updateLotUkRenoScheduler(value);;
			}
			else if(profile == 4){
				controlEmailService.updateLotUkColoScheduler(value);
			}
		}
		else if(obj.equals("buyer")){
			if(profile == 1){
				controlEmailService.updateBuyerUsRenoScheduler(value);
			}
			else if(profile == 2){
				controlEmailService.updateBuyerUsColoScheduler(value);
			}
			else if(profile == 3){
				controlEmailService.updateBuyerUkRenoScheduler(value);;
			}
			else if(profile == 4){
				controlEmailService.updateBuyerUkColoScheduler(value);
			}
		}
		else if(obj.equals("seller")){
			if(profile == 1){
				controlEmailService.updateSellerUsRenoScheduler(value);
			}
			else if(profile == 2){
				controlEmailService.updateSellerUsColoScheduler(value);
			}
			else if(profile == 3){
				controlEmailService.updateSellerUkRenoScheduler(value);;
			}
			else if(profile == 4){
				controlEmailService.updateSellerUkColoScheduler(value);
			}
		}
		else if(obj.equals("video")){
			if(profile == 1){
				controlEmailService.updateVideoUsRenoScheduler(value);
			}
			else if(profile == 2){
				controlEmailService.updateVideoUsColoScheduler(value);
			}
			else if(profile == 3){
				controlEmailService.updateVideoUkRenoScheduler(value);;
			}
			else if(profile == 4){
				controlEmailService.updateVideoUkColoScheduler(value);
			}
		}
		else if(obj.equals("img")){
			if(profile == 1){
				controlEmailService.updateImageUsRenoScheduler(value);
			}
			else if(profile == 2){
				controlEmailService.updateImageUsColoScheduler(value);
			}
			else if(profile == 3){
				controlEmailService.updateImageUkRenoScheduler(value);;
			}
			else if(profile == 4){
				controlEmailService.updateImageUkColoScheduler(value);
			}
		}
		else if(obj.equals("attr")){
			if(profile == 1){
				controlEmailService.updateAttrUsRenoScheduler(value);
			}
			else if(profile == 2){
				controlEmailService.updateAttrUsColoScheduler(value);
			}
			else if(profile == 3){
				controlEmailService.updateAttrUkRenoScheduler(value);;
			}
			else if(profile == 4){
				controlEmailService.updateAttrUkColoScheduler(value);
			}
		}
		else{
			controlEmailService.insertEmailsInfo(email);
		}
		
		
		
		return "control_emails";
	}
	
	@RequestMapping("/pagination")
	public String paginationResults(Model model, @RequestParam(value="pass", required=true, defaultValue="1") String pass, @RequestParam(value="page", required=false, defaultValue="1") String page, @RequestParam(value="profile", required=false, defaultValue="1") String profile) {
		
		int page1 = 1;
	     int recordsPerPage = 5;
	     if(page != null)
	           page1 = Integer.parseInt(page);
	     int noOfPages = 0;
	     if(Integer.valueOf(profile) == 1 ){
		    	 if(Integer.valueOf(pass) < 5 || Integer.valueOf(pass) == 6){
		    		 List<UsRenoMariadb> list =  usRenoMariadbService.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass);
		    		 for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		 int noOfRecords = usRenoMariadbService.getNoOfRecords();
		    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				     model.addAttribute("detailsList", list);
		    	 }
		    	 else{
		    		 List<UsDb2> list = usDb2Service.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass, profile);
		    		 for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		 int noOfRecords = usDb2Service.getNoOfRecords();
		    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		    		 model.addAttribute("detailsList", list);
		    	 }
	     }
	     else if(Integer.valueOf(profile) == 2 ){
		    	 if(Integer.valueOf(pass) < 5 || Integer.valueOf(pass) == 6){
		    		 List<UsColoMariadb> list =  usColoMariadbService.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass);
		    		 for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		 int noOfRecords = usColoMariadbService.getNoOfRecords();
		    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				     model.addAttribute("detailsList", list);
		    	 }
		    	 else{
		    		 List<UsDb2> list = usDb2Service.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass, profile);
		    		 for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		 int noOfRecords = usDb2Service.getNoOfRecords();
		    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		    		 model.addAttribute("detailsList", list);
		    	 }
        }
	     else if(Integer.valueOf(profile) == 3 ){
		    	 if(Integer.valueOf(pass) < 5 || Integer.valueOf(pass) == 6){
		    		 List<UkRenoMariadb> list =  ukRenoMariadbService.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass);
		    		 for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		 int noOfRecords = ukRenoMariadbService.getNoOfRecords();
		    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				     model.addAttribute("detailsList", list);
		    	 }
		    	 else{
		    		 List<UkDb2> list = ukDb2Service.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass, profile);
		    		 for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		 int noOfRecords = ukDb2Service.getNoOfRecords();
		    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		    		 model.addAttribute("detailsList", list);
		    	 }
        }
	    else if(Integer.valueOf(profile) == 4 ){
	    	 if(Integer.valueOf(pass) < 5 || Integer.valueOf(pass) == 6){
	    		 List<UkColoMariadb> list =  ukColoMariadbService.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass);
	    		 for(int i = 0; i <list.size(); i ++){
	    			 if(list.get(i).getobject_count()!= null)
	    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
	    		 }
	    		 int noOfRecords = ukColoMariadbService.getNoOfRecords();
	    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			     model.addAttribute("detailsList", list);
	    	 }
	    	 else{
	    		 List<UkDb2> list = ukDb2Service.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass, profile);
	    		 for(int i = 0; i <list.size(); i ++){
	    			 if(list.get(i).getobject_count()!= null)
	    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
	    		 }
	    		 int noOfRecords = ukDb2Service.getNoOfRecords();
	    		 noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	    		 model.addAttribute("detailsList", list);
	    	 }
        }
	    else{
		    	if(Integer.valueOf(pass) < 5 || Integer.valueOf(pass) == 6){
		    		List<UsRenoMariadb> list =  usRenoMariadbService.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass);
		    		for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		int noOfRecords = usRenoMariadbService.getNoOfRecords();
		    		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		    		model.addAttribute("detailsList", list);
		    	}
		    	else{
		    		List<UsDb2> list = usDb2Service.makePagination((page1-1)*recordsPerPage, recordsPerPage, pass, profile);
		    		for(int i = 0; i <list.size(); i ++){
		    			 if(list.get(i).getobject_count()!= null)
		    			 list.get(i).setobject_count(list.get(i).getobject_count().replaceAll("[^\\d]", ""));
		    		 }
		    		int noOfRecords = usDb2Service.getNoOfRecords();
		    		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		    		model.addAttribute("detailsList", list);
		    	}
	    }
	     
	     	model.addAttribute("profile",profile);
	        model.addAttribute("noOfPages", noOfPages);
	        model.addAttribute("currentPage", Integer.valueOf(page));
	        model.addAttribute("CurrentObject", pass);
		
		return "display";
	}
	
	@RequestMapping(value = "/graphToday", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET )
	public ResponseEntity<ResponseforGraph> drawTodayGraph(Model model, @RequestParam(value="pass", required=true, defaultValue="1") String pass, @RequestParam(value="profile", required=true, defaultValue="1") String profile){
		//System.out.println("Entered Graph Range");
		if(Integer.valueOf(profile) == 1 && Integer.valueOf(pass) < 5){
			List<UsRenoMariadb> list1 = usRenoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList1(list1);
			if(list1.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 1 && Integer.valueOf(pass) == 5){
			//System.out.println("Entered Profile 1 and pass 5");
			List<UsDb2> list5 = usDb2Service.drawTodayGraph(profile);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList5(list5);
			if(list5.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 1 && Integer.valueOf(pass) == 6){
			List<UsRenoMariadb> list1 = usRenoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList1(list1);
			if(list1.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 2 && Integer.valueOf(pass) < 5){
			List<UsColoMariadb> list2 = usColoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList2(list2);
			if(list2.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 2 && Integer.valueOf(pass) == 5){
			List<UsDb2> list5 = usDb2Service.drawTodayGraph(profile);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList5(list5);
			if(list5.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 2 && Integer.valueOf(pass) == 6){
			List<UsColoMariadb> list2 = usColoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList2(list2);
			if(list2.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3 && Integer.valueOf(pass) < 5){
			List<UkRenoMariadb> list3 = ukRenoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList3(list3);
			if(list3.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3 && Integer.valueOf(pass) == 5){
			List<UkDb2> list6 = ukDb2Service.drawTodayGraph(profile);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList6(list6);
			if(list6.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3 && Integer.valueOf(pass) == 6){
			List<UkRenoMariadb> list3 = ukRenoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList3(list3);
			if(list3.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 4 && Integer.valueOf(pass) < 5){
			List<UkColoMariadb> list4 = ukColoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList4(list4);
			if(list4.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 4 && Integer.valueOf(pass) == 5){
			List<UkDb2> list6 = ukDb2Service.drawTodayGraph(profile);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList6(list6);
			if(list6.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		if(Integer.valueOf(profile) == 4 && Integer.valueOf(pass) == 6){
			List<UkColoMariadb> list4 = ukColoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList4(list4);
			if(list4.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else{
			List<UsRenoMariadb> list1 = usRenoMariadbService.drawTodayGraph(pass);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList1(list1);
			if(list1.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/graphRange", produces = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET )
	public ResponseEntity<ResponseforGraph> drawRangeGraph(Model model, @RequestParam(value="obj", required=true, defaultValue="1") String obj, @RequestParam(value="profile", required=true, defaultValue="1") String profile, @RequestParam(value="From", required=true, defaultValue="1") Date From, @RequestParam(value="To", required=true, defaultValue="1") Date To){
		if(Integer.valueOf(profile) == 1 && Integer.valueOf(obj) < 5){
			//System.out.println("Entered for others");
			List<UsRenoMariadb> list1 = usRenoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList1(list1);
			if(list1.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 1 && Integer.valueOf(obj) == 5){
			//System.out.println("Entered for IMG");
			List<UsDb2> list5 = usDb2Service.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList5(list5);
			if(list5.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 1 && Integer.valueOf(obj) == 6){
			//System.out.println("Entered for Attr");
			List<UsRenoMariadb> list1 = usRenoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList1(list1);
			if(list1.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 2 && Integer.valueOf(obj) < 5){
			List<UsColoMariadb> list2 = usColoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList2(list2);
			if(list2.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 2 && Integer.valueOf(obj) == 5){
			List<UsDb2> list5 = usDb2Service.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList5(list5);
			if(list5.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 2 && Integer.valueOf(obj) == 6){
			List<UsColoMariadb> list2 = usColoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList2(list2);
			if(list2.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3 && Integer.valueOf(obj) < 5){
			List<UkRenoMariadb> list3 = ukRenoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList3(list3);
			if(list3.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3 && Integer.valueOf(obj) == 5){
			List<UkDb2> list6 = ukDb2Service.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList6(list6);
			if(list6.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 3 && Integer.valueOf(obj) == 6){
			List<UkRenoMariadb> list3 = ukRenoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList3(list3);
			if(list3.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 4 && Integer.valueOf(obj) < 5){
			List<UkColoMariadb> list4 = ukColoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList4(list4);
			if(list4.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 4 && Integer.valueOf(obj) == 5){
			List<UkDb2> list6 = ukDb2Service.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList6(list6);
			if(list6.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else if(Integer.valueOf(profile) == 4 && Integer.valueOf(obj) == 6){
			List<UkColoMariadb> list4 = ukColoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList4(list4);
			if(list4.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
		else{
			List<UsRenoMariadb> list1 = usRenoMariadbService.drawRangeGraph(obj, From, To);
			ResponseforGraph rsg = new ResponseforGraph();
			rsg.setList1(list1);
			if(list1.isEmpty()){
	            return new ResponseEntity<ResponseforGraph>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ResponseforGraph>(rsg, HttpStatus.OK);
		}
	}
}
