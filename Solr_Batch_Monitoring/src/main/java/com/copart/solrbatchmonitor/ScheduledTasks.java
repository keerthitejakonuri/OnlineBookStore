package com.copart.solrbatchmonitor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.copart.solrbatchmonitor.controllers.MainController;
import com.copart.solrbatchmonitor.schedulerdb.entities.Emails;
import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerTimings;
import com.copart.solrbatchmonitor.schedulerdb.repositories.SchedulerdbRepository1;
import com.copart.solrbatchmonitor.schedulerdb.repositories.SchedulerdbRepository2;
import com.copart.solrbatchmonitor.services.ControlEmailService;
import com.copart.solrbatchmonitor.uk.colomariadb.entities.UkColoMariadb;
import com.copart.solrbatchmonitor.uk.colomariadb.repositories.UkColoMariadbRepository;
import com.copart.solrbatchmonitor.uk.renomariadb.entities.UkRenoMariadb;
import com.copart.solrbatchmonitor.uk.renomariadb.repositories.UkRenoMariadbRepository;
import com.copart.solrbatchmonitor.us.colomariadb.entities.UsColoMariadb;
import com.copart.solrbatchmonitor.us.colomariadb.repositories.UsColoMariadbRepository;
import com.copart.solrbatchmonitor.us.db2.entities.UsDb2;
import com.copart.solrbatchmonitor.us.db2.repositories.UsDb2Repository;
import com.copart.solrbatchmonitor.us.renomariadb.entities.UsRenoMariadb;
import com.copart.solrbatchmonitor.us.renomariadb.repositories.UsRenoMariadbRepository;



@Component
public class ScheduledTasks {	// This is a scheduler class that checks conditions periodically
	@Autowired
	private UsRenoMariadbRepository usRenoMariadbRepository;
	@Autowired
	private UsColoMariadbRepository usColoMariadbRepository;
	@Autowired
	private UkRenoMariadbRepository ukRenoMariadbRepository;
	@Autowired
	private UkColoMariadbRepository ukColoMariadbRepository;
	@Autowired
	private UsDb2Repository usDb2Repository;
	//@Autowired
	//private EmailService emailService;
	@Autowired
	private SchedulerdbRepository1 schedulerdbRepository1;
	@Autowired
	private SchedulerdbRepository2 schedulerdbRepository2;
	@Autowired
	private ControlEmailService controlEmailService;
	
	@Value("${spring.datasource.usrenomariadb.conf_flag}")
	private boolean usRenoMariadb_conf_flag;
	@Value("${spring.datasource.uscolomariadb.conf_flag}")
	private boolean usColoMariadb_conf_flag;
	@Value("${spring.datasource.ukrenomariadb.conf_flag}")
	private boolean ukRenoMariadb_conf_flag;
	@Value("${spring.datasource.ukcolomariadb.conf_flag}")
	private boolean ukColoMariadb_conf_flag;
	@Value("${spring.datasource.usdb2.conf_flag}")
	private boolean usDb2_conf_flag;
	@Value("${spring.profiles}")
	private String prof;
	@Value("${spring.host}")
	private String HOST;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static String username = "keerthi.konuri@copart.com";
	private static String password = "XXXXXXXXXX";
	//public String fileName = System.getProperty("user.home")+"/email.csv";
	public String us_reno = "us_reno", us_colo = "us_colo", uk_reno = "uk_reno", uk_colo ="uk_colo";
	public int lot_number = 1, buyer_number = 2, seller_number = 3, video_number = 4, image_number = 5, attr_number = 6;
	public String status5, status15, status10, status30;
//=========================================================================================================================
	
	
	@Scheduled(cron="0 0/5 * * * *")
    public void scheduler5min() {      //This function triggers every 5 minutes.
		
		perform(5);
		
		
}

	//=========================================================================================================================
	
		@Scheduled(cron="0 0/10 * * * *")
	    public void scheduler10min() {		//This function triggers every 10 minutes.
			
			perform(10);
			
		
		}
		
//=========================================================================================================================
	
	@Scheduled(cron="0 0/15 * * * *")
    public void scheduler15min() {			//This function triggers every 15 minutes.
	
		perform(15);
		
   }
	

	
	
	
//=========================================================================================================================	
	
	
	
	

	
	@Scheduled(cron="0 0/30 * * * *")
    public void scheduler30min() {				//This function triggers every 30 minutes.
		
		
		perform(30);
		
   }
 
//=========================================================================================================================	
	
	public void perform(int s_interval){		//This function performs checking conditions by calling checkConditions() fn and followConditions() fn
		List<SchedulerTimings> lotlist = schedulerdbRepository1.getAllLot();
		List<SchedulerTimings> buyerlist = schedulerdbRepository1.getAllBuyer();
		List<SchedulerTimings> sellerlist = schedulerdbRepository1.getAllSeller();
		List<SchedulerTimings> videolist = schedulerdbRepository1.getAllVideo();
		List<SchedulerTimings> imagelist = schedulerdbRepository1.getAllImage();
		List<SchedulerTimings> attrlist = schedulerdbRepository1.getAllAttr();
		for(int i =0; i < lotlist.size(); i++){
			if(lotlist.get(i).scheduler_interval == s_interval){
				//System.out.print("object_type is "+ lotlist.get(i).object_type+" Data Center is "+lotlist.get(i).data_center+" Scheduler_Interval" +lotlist.get(i).scheduler_interval+" Batch_Interval" +lotlist.get(i).batch_interval+" Start Time is "+lotlist.get(i).Start_Time+" End Time is "+lotlist.get(i).End_Time);
				//System.out.print(" "+isNowInBetween(lotlist.get(i).Start_Time, lotlist.get(i).End_Time));
				//System.out.println();
				if(isNowInBetween(lotlist.get(i).Start_Time, lotlist.get(i).End_Time)){
					int condition = 0;
					if(lotlist.get(i).data_center.equals(us_reno)){
						condition = checkConditions(lot_number,1,lotlist.get(i).batch_interval);
						followConditions(lot_number,1,condition);
					}else if(lotlist.get(i).data_center.equals(us_colo)){
						condition = checkConditions(lot_number,2,lotlist.get(i).batch_interval);
						followConditions(lot_number,2,condition);
					}else if(lotlist.get(i).data_center.equals(uk_reno)){
						condition = checkConditions(lot_number,3,lotlist.get(i).batch_interval);
						followConditions(lot_number,3,condition);
					}else if(lotlist.get(i).data_center.equals(uk_colo)){	
						condition = checkConditions(lot_number,4,lotlist.get(i).batch_interval);
						followConditions(lot_number,4,condition);
					}
				}
			}
		}
		
		for(int i =0; i < buyerlist.size(); i++){
			if(buyerlist.get(i).scheduler_interval == s_interval){
				//System.out.print("object_type is "+ buyerlist.get(i).object_type+" Data Center is "+buyerlist.get(i).data_center+" Scheduler_Interval" +buyerlist.get(i).scheduler_interval+" Batch_Interval" +buyerlist.get(i).batch_interval+" Start Time is "+buyerlist.get(i).Start_Time+" End Time is "+buyerlist.get(i).End_Time);
				//System.out.print(" "+isNowInBetween(buyerlist.get(i).Start_Time, buyerlist.get(i).End_Time));
				//System.out.println();
				if(isNowInBetween(buyerlist.get(i).Start_Time, buyerlist.get(i).End_Time)){
					int condition = 0;
					if(buyerlist.get(i).data_center.equals(us_reno) ){
						condition = checkConditions(buyer_number,1,buyerlist.get(i).batch_interval);
						followConditions(buyer_number,1,condition);
					}else if(buyerlist.get(i).data_center.equals(us_colo) ){
						condition = checkConditions(buyer_number,2,buyerlist.get(i).batch_interval);
						followConditions(buyer_number,2,condition);
					}else if(buyerlist.get(i).data_center.equals(uk_reno) ){
						condition = checkConditions(buyer_number,3,buyerlist.get(i).batch_interval);
						followConditions(buyer_number,3,condition);
					}else if(buyerlist.get(i).data_center.equals(uk_colo) ){
						condition = checkConditions(buyer_number,4,buyerlist.get(i).batch_interval);
						followConditions(buyer_number,4,condition);
					}
				}
			}
		}
		
		
		for(int i =0; i < sellerlist.size(); i++){
			if(sellerlist.get(i).scheduler_interval == s_interval){
				//System.out.print("object_type is "+ sellerlist.get(i).object_type+" Data Center is "+sellerlist.get(i).data_center+" Scheduler_Interval" +sellerlist.get(i).scheduler_interval+" Batch_Interval" +sellerlist.get(i).batch_interval+" Start Time is "+sellerlist.get(i).Start_Time+" End Time is "+sellerlist.get(i).End_Time);
				//System.out.print(" "+isNowInBetween(sellerlist.get(i).Start_Time, sellerlist.get(i).End_Time));
				//System.out.println();
				if(isNowInBetween(sellerlist.get(i).Start_Time, sellerlist.get(i).End_Time)){
					int condition = 0;
					if(sellerlist.get(i).data_center.equals(us_reno)){
						condition = checkConditions(seller_number,1,sellerlist.get(i).batch_interval);
						followConditions(seller_number,1,condition);
					}else if(sellerlist.get(i).data_center.equals(us_colo)){
						condition = checkConditions(seller_number,2,sellerlist.get(i).batch_interval);
						followConditions(seller_number,2,condition);
					}else if(sellerlist.get(i).data_center.equals(uk_reno)){
						condition = checkConditions(seller_number,3,sellerlist.get(i).batch_interval);
						followConditions(seller_number,3,condition);
					}else if(sellerlist.get(i).data_center.equals(uk_colo)){
						condition = checkConditions(seller_number,4,sellerlist.get(i).batch_interval);
						followConditions(seller_number,4,condition);
					}
				}
			}
		}
		
		for(int i =0; i < videolist.size(); i++){
			if(videolist.get(i).scheduler_interval == s_interval){
				//System.out.print("object_type is "+ videolist.get(i).object_type+" Data Center is "+videolist.get(i).data_center+" Scheduler_Interval" +videolist.get(i).scheduler_interval+" Batch_Interval" +videolist.get(i).batch_interval+" Start Time is "+videolist.get(i).Start_Time+" End Time is "+videolist.get(i).End_Time);
				//System.out.print(" "+isNowInBetween(videolist.get(i).Start_Time, videolist.get(i).End_Time));
				//System.out.println();
				if(isNowInBetween(videolist.get(i).Start_Time, videolist.get(i).End_Time)){
					int condition = 0;
					if(videolist.get(i).data_center.equals(us_reno)){
						condition = checkConditions(video_number,1,videolist.get(i).batch_interval);
						followConditions(video_number,1,condition);
					}else if(videolist.get(i).data_center.equals(us_colo)){
						condition = checkConditions(video_number,2,videolist.get(i).batch_interval);
						followConditions(video_number,2,condition);
					}else if(videolist.get(i).data_center.equals(uk_reno)){
						condition = checkConditions(video_number,3,videolist.get(i).batch_interval);
						followConditions(video_number,3,condition);
					}else if(videolist.get(i).data_center.equals(uk_colo)){
						condition = checkConditions(video_number,4,videolist.get(i).batch_interval);
						followConditions(video_number,4,condition);
					}
				}
			}
		}
		
		
		for(int i =0; i < imagelist.size(); i++){
			if(imagelist.get(i).scheduler_interval == s_interval){
				//System.out.print("object_type is "+ imagelist.get(i).object_type+" Data Center is "+imagelist.get(i).data_center+" Scheduler_Interval" +imagelist.get(i).scheduler_interval+" Batch_Interval" +imagelist.get(i).batch_interval+" Start Time is "+imagelist.get(i).Start_Time+" End Time is "+imagelist.get(i).End_Time);
				//System.out.print(" "+isNowInBetween(imagelist.get(i).Start_Time, imagelist.get(i).End_Time));
				//System.out.println();
				if(isNowInBetween(imagelist.get(i).Start_Time, imagelist.get(i).End_Time)){
					int condition = 0;
					if(imagelist.get(i).data_center.equals(us_reno)){
						condition = checkConditions(image_number,1,imagelist.get(i).batch_interval);
						followConditions(image_number,1,condition);
					}else if(imagelist.get(i).data_center.equals(us_colo)){
						condition = checkConditions(image_number,2,imagelist.get(i).batch_interval);
						followConditions(image_number,2,condition);
					}
					else if(imagelist.get(i).data_center.equals(uk_reno)){
						condition = checkConditions(image_number,3,imagelist.get(i).batch_interval);
						followConditions(image_number,3,condition);
					}else if(imagelist.get(i).data_center.equals(uk_colo)){
						condition = checkConditions(image_number,4,imagelist.get(i).batch_interval);
						followConditions(image_number,4,condition);
					}
				}
			}
		}
		
		
		for(int i =0; i < attrlist.size(); i++){
			if(attrlist.get(i).scheduler_interval == s_interval){
				//System.out.print("object_type is "+ attrlist.get(i).object_type+" Data Center is "+attrlist.get(i).data_center+" Scheduler_Interval" +attrlist.get(i).scheduler_interval+" Batch_Interval" +attrlist.get(i).batch_interval+" Start Time is "+attrlist.get(i).Start_Time+" End Time is "+attrlist.get(i).End_Time);
				//System.out.print(" "+isNowInBetween(attrlist.get(i).Start_Time, attrlist.get(i).End_Time));
				//System.out.println();
				if(isNowInBetween(attrlist.get(i).Start_Time, attrlist.get(i).End_Time)){
					int condition = 0;
					if(attrlist.get(i).data_center.equals(us_reno)){
						condition = checkConditions(attr_number,1,attrlist.get(i).batch_interval);
						followConditions(attr_number,1,condition);
					}else if(attrlist.get(i).data_center.equals(us_colo)){
						condition = checkConditions(attr_number,2,attrlist.get(i).batch_interval);
						followConditions(attr_number,2,condition);
					}else if(attrlist.get(i).data_center.equals(uk_reno)){
						condition = checkConditions(attr_number,3,attrlist.get(i).batch_interval);
						followConditions(attr_number,3,condition);
					}else if(attrlist.get(i).data_center.equals(uk_colo)){
						condition = checkConditions(attr_number,4,attrlist.get(i).batch_interval);
						followConditions(attr_number,4,condition);
					}
				}
			}
		}
	}

    
//=========================================================================================================================	
	
	
	
	
	public void sendemail(String object, String to, String userName, String password, int option){			// This function sends the email
		//String host = "msgsmtp.copart.com";
		//String host = "smtp.copart.com";
		String host = HOST;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
	        
	    	
	    	
	    	List<Emails> email_list = schedulerdbRepository2.getAllReceivers();
	    	String userName1 = schedulerdbRepository2.getSender().email_id;
     // Get the Session object.
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(userName1, password);
           }
        });
        
        for(int i = 0; i < email_list.size(); i++){
	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);
	
	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(userName1));
	            
	            	
	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_list.get(i).email_id));
	            if(option == 1){
	            // Set Subject: header field
	            message.setSubject(prof.toUpperCase()+" Alert! ");
	
	            // Now set the actual message
	            message.setText(object +" on "+prof.toUpperCase()+" server is Running Late ");
	            }
	            else if(option == 2) {
	            	// Set Subject: header field
	                message.setSubject(prof.toUpperCase()+" High Alert!!!! ");
	
	                // Now set the actual message
	                message.setText(object +" on "+prof.toUpperCase()+" server is Not Running ");
	            }
	            else {
	            	// Set Subject: header field
	                message.setSubject(prof.toUpperCase()+" Alert! ");
	
	                // Now set the actual message
	                message.setText(object +" on "+prof.toUpperCase()+" server is Running Perfect");
	            }
	            // Send message
	            Transport.send(message);
	
	            log.info("Sent message successfully to "+email_list.get(i).email_id);
	
	         } catch (MessagingException e) {
	        	 e.printStackTrace();
	        	 log.info(email_list.get(i).email_id +" is InValid EmailId");
	         }
        }
        
	}
	
	public long changeX(long x){
		/*if(x >= 7200000){							//Comment this if before deploying to server
        	x = x  - 7200000;		
        }*/
		return x;
	}
	
	public void followConditions(int object,int profile, int condition){			// Based on the value of condition argument from checkConditions it calls sendEmail() fn.
		String str1 = "lot", str2 = "US_Reno";
		
		if(profile == 1){
			str2 = "US_Reno";
		}else if(profile == 2){
			str2 = "US_COLO";
		}else if(profile == 3){
			str2 = "UK_Reno";
		}else if(profile == 4){
			str2 = "UK_Colo";
		}else{
			
		}
		
		if(object == 1){
			str1 = "Lot";
		}else if(object == 2){
			str1 = "Buyer";
		}else if(object == 3){
			str1 = "Seller";
		}else if(object == 4){
			str1 = "Video";
		}else if(object == 5){
			str1 = "Image";
			if(profile == 1 || profile == 3){str2 = "Reno";}else{str2 = "Colo";}
		}else if(object == 6){
			str1 = "Attr";
		}
		else{
			
		}
		
		if(condition == 0){
			//log.info(str1 +" in "+str2+" is Running Perfect");
       	}else if(condition == 1){
       		//log.info(str1 +" in "+str2+" is Running Late");
           	sendemail(str1 +" in "+str2, "keerthi.konuri@copart.com", username, password, 1 );
        }else{
        	//log.info(str1 +" in "+str2+" is Not Running");
              	sendemail(str1 +" in "+str2, "keerthi.konuri@copart.com", username, password, 2 );	
        }	
		
		
		
	}
	
	public int checkConditions(int object,int profile, int interval){		// This function returns the status as int after checking the conditions.
		Timestamp now = new Timestamp(System.currentTimeMillis());
		java.util.Date date= new java.util.Date();
		Timestamp end = null;
		if(object == 1){
			
			if(profile == 1){
				UsRenoMariadb dr1 = usRenoMariadbRepository.findTableLot();
				if(dr1 == null){	
					 dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}
				else{
					if(!usRenoMariadb_conf_flag){
						dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateLotUsReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 2){
				UsColoMariadb dr1 = usColoMariadbRepository.findTableLot();
				if(dr1 == null){
					 dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usColoMariadb_conf_flag){
						dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateLotUsColo(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 3 ){
				UkRenoMariadb dr1 = ukRenoMariadbRepository.findTableLot();
				if(dr1 == null){
					 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukRenoMariadb_conf_flag){
						dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateLotUkReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 4){
				UkColoMariadb dr1 = ukColoMariadbRepository.findTableLot();
				if(dr1 == null){
					 dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukColoMariadb_conf_flag){
						dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateLotUkColo(date);
				}
				end = dr1.getend_dttm();
			}  
			        
		}else if(object == 2){
			
			if(profile == 1){
				UsRenoMariadb dr1 = usRenoMariadbRepository.findTableBuyer();
				if(dr1 == null){
					 dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}
				else{
					if(!usRenoMariadb_conf_flag){
						dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateBuyerUsReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 2){
				UsColoMariadb dr1 = usColoMariadbRepository.findTableBuyer();
				if(dr1 == null){
					 dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usColoMariadb_conf_flag){
						dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateBuyerUsColo(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 3){
				UkRenoMariadb dr1 = ukRenoMariadbRepository.findTableBuyer();
				if(dr1 == null){
					 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukRenoMariadb_conf_flag){
						 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateBuyerUkReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 4){
				UkColoMariadb dr1 = ukColoMariadbRepository.findTableBuyer();
				if(dr1 == null){
					 dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukColoMariadb_conf_flag){
						dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateBuyerUkColo(date);
				}
				end = dr1.getend_dttm();
			}  
				
			
		}
		else if(object == 3){
			
			if(profile == 1){
				UsRenoMariadb dr1 = usRenoMariadbRepository.findTableSeller();
				if(dr1 == null){
					 dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}
				else{
					if(!usRenoMariadb_conf_flag){
						dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateSellerUsReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 2){
				UsColoMariadb dr1 = usColoMariadbRepository.findTableSeller();
				if(dr1 == null){
					 dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usColoMariadb_conf_flag){
						dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateSellerUsColo(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 3){
				UkRenoMariadb dr1 = ukRenoMariadbRepository.findTableSeller();
				if(dr1 == null){
					 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukRenoMariadb_conf_flag){
						dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateSellerUkReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 4){
				UkColoMariadb dr1 = ukColoMariadbRepository.findTableSeller();
				if(dr1 == null){
					 dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukColoMariadb_conf_flag){
						dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateSellerUkColo(date);
				}
				end = dr1.getend_dttm();
			}  
			
		}
		else if(object == 4){
			if(profile == 1){
				UsRenoMariadb dr1 = usRenoMariadbRepository.findTableVideo();
				if(dr1 == null){
					 dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}
				else{
					if(!usRenoMariadb_conf_flag){
						dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateVideoUsReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 2){
				UsColoMariadb dr1 = usColoMariadbRepository.findTableVideo();
				if(dr1 == null){
					 dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usColoMariadb_conf_flag){
						dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateVideoUsColo(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 3){
				UkRenoMariadb dr1 = ukRenoMariadbRepository.findTableVideo();
				if(dr1 == null){
					 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukRenoMariadb_conf_flag){
						 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateVideoUkReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 4){
				UkColoMariadb dr1 = ukColoMariadbRepository.findTableVideo();
				if(dr1 == null){
					 dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukColoMariadb_conf_flag){
						dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateVideoUkColo(date);
				}
				end = dr1.getend_dttm();
			}  
		}
		else if(object == 5){
			if(profile == 1){
				UsDb2 dr1 = usDb2Repository.findTableImgReno();
				if(dr1 == null){
					 dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}
				else{
					if(!usDb2_conf_flag){
						dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateImageUsReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 2){
				UsDb2 dr1 = usDb2Repository.findTableImgColo();
				//System.out.println("Entered here in Image colous1 "+dr1);
				if(dr1 == null){
					 dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usDb2_conf_flag){
						dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					//System.out.println("Entered here in Image colous2 "+dr1);
					controlEmailService.updateImageUsColo(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 3){
				UsDb2 dr1 = usDb2Repository.findTableImgReno();
				if(dr1 == null){
					 dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usDb2_conf_flag){
						dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateImageUkReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 4){
				UsDb2 dr1 = usDb2Repository.findTableImgColo();
				//System.out.println("Entered here in Image colouk1 "+dr1);
				if(dr1 == null){
					 dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usDb2_conf_flag){
						dr1 = new UsDb2(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					//System.out.println("Entered here in Image colouk2 "+dr1);
					controlEmailService.updateImageUkColo(date);
				}
				end = dr1.getend_dttm();
			}
		}
		else if(object == 6){
			if(profile == 1 ){
				UsRenoMariadb dr1 = usRenoMariadbRepository.findTableAttr();
				if(dr1 == null){
					 dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}
				else{
					if(!usRenoMariadb_conf_flag){
						dr1 = new UsRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateAttrUsReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 2){
				UsColoMariadb dr1 = usColoMariadbRepository.findTableAttr();
				if(dr1 == null){
					 dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!usColoMariadb_conf_flag){
						dr1 = new UsColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateAttrUsColo(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 3){
				UkRenoMariadb dr1 = ukRenoMariadbRepository.findTableAttr();
				if(dr1 == null){
					 dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukRenoMariadb_conf_flag){
						dr1 = new UkRenoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateAttrUkReno(date);
				}
				end = dr1.getend_dttm();
			}
			else if(profile == 4){
				UkColoMariadb dr1 = ukColoMariadbRepository.findTableAttr();
				if(dr1 == null){
						 dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
				}else{
					if(!ukColoMariadb_conf_flag){
						dr1 = new UkColoMariadb(-1, new Timestamp(date.getTime()), new Timestamp(date.getTime()), new Timestamp(date.getTime()), "-1", "Not Started", "lot", "-1");
					}
					controlEmailService.updateAttrUkColo(date);
				}
				end = dr1.getend_dttm();
			}  
		}
		
		
		long x = ( now.getTime() - end.getTime());
		x = changeX(x);
		//System.out.println("Param is "+x);
		//System.out.println("Runs after"+convertMinutesToMillisec(5));
        
        if(x < convertMinutesToMillisec(interval)){
        	//System.out.println("Entered here with "+x);
        	return 0;
        }
        else if(x < convertMinutesToMillisec(1.25*interval)){
        	//System.out.println("Entered here with "+x);
        	return 1;
        }
        else{
        	//System.out.println("Entered here with "+x);
        	return 2;
        }
		
		
	}
	
	public long convertMinutesToMillisec(double interval){					// This function takes minutes as arguments and returns milliseconds.
		long millisec = 0;
		if(interval <= 5){
			millisec = (long)(interval + 2.0) * 60 * 1000;
		}else if(interval <= 30){
			millisec = (long)(interval + 5.0) * 60 * 1000;
		}
		else{
			millisec = (long)(interval + 10.0) * 60 * 1000;
		}
		
		return millisec;
	}
	
		
	public static boolean isNowInBetween(String initialTime, String finalTime) {			// Checks whether the current time is between the given arguments.
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
