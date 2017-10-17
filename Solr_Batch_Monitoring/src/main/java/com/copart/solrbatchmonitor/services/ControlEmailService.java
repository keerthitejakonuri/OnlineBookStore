package com.copart.solrbatchmonitor.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copart.solrbatchmonitor.schedulerdb.entities.Emails;
import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerInfo;
//import com.copart.solrbatchmonitor.schedulerdb.repositories.SchedulerdbRepository1;
import com.copart.solrbatchmonitor.schedulerdb.repositories.SchedulerdbRepository2;
import com.copart.solrbatchmonitor.schedulerdb.repositories.SchedulerdbRepository3;
@Transactional
@Service
public class ControlEmailService {
	public static String lotUsReno = "1";
	public static String lotUsRenoProfile = "1";
	public static String lotUkReno = "1";
	public static String lotUkRenoProfile = "1";
	public static String lotUsColo = "1";
	public static String lotUsColoProfile = "1";
	public static String lotUkColo = "1";
	public static String lotUkColoProfile = "1";
	public static String imgReno = "1";
	public static String imgRenoProfile = "1";
	public static String imgColo = "1";
	public static String imgColoProfile = "1";
	public static String email = "keerthi.konuri@copart.com";
	@Autowired
	private SchedulerdbRepository3 schedulerdbRepository3;
	@Autowired
	private SchedulerdbRepository2 schedulerdbRepository2;
		/*public void setlotUsreno(String lotUsReno){
			//System.out.println("Entered control emails in Service");
			this.lotUsReno = lotUsReno;
			
		}*/
		public String getlotUsReno(){
			return lotUsReno;
		}
		
		/*public void setlotUkReno(String lotUkReno){
			//System.out.println("Entered control emails in Service");
			this.lotUkReno = lotUkReno;
			
		}*/
		public String getlotUkReno(){
			return lotUkReno;
		}
		
		
		/*public void setlotUsColo(String lotUsColo){
			//System.out.println("Entered control emails in Service");
			this.lotUsColo = lotUsColo;
			
		}*/
		public String getlotUsColo(){
			return lotUsColo;
		}
		
		
		/*public void setlotUkColo(String lotUkColo){
			//System.out.println("Entered control emails in Service");
			this.lotUkColo = lotUkColo;
			
		}*/
		public String getlotUkColo(){
			return lotUkColo;
		}
		
		
		/*public void setimgColo(String imgColo){
			//System.out.println("Entered control emails in Service");
			this.imgColo = imgColo;
			
		}*/
		public String getimgColo(){
			return imgColo;
		}
		
		/*public void setimgReno(String imgReno){
			//System.out.println("Entered control emails in Service");
			this.imgReno = imgReno;
			
		}*/
		public String getimgReno(){
			return imgReno;
		}
		
		
		public void updateLotUsReno(Date date){
			schedulerdbRepository3.updateLotUsReno(date);
		}
		
		public void updateLotUkReno(Date date){
			schedulerdbRepository3.updateLotUkReno(date);
		}
		
		public void updateBuyerUsReno(Date date){
			schedulerdbRepository3.updateBuyerUsReno(date);
		}
		
		public void updateBuyerUkReno(Date date){
			schedulerdbRepository3.updateBuyerUkReno(date);
		}
		
		public void updateSellerUsReno(Date date){
			schedulerdbRepository3.updateSellerUsReno(date);
		}
		
		public void updateSellerUkReno(Date date){
			schedulerdbRepository3.updateSellerUkReno(date);
		}
		
		public void updateVideoUsReno(Date date){
			schedulerdbRepository3.updateVideoUsReno(date);
		}
		
		public void updateVideoUsColo(Date date){
			schedulerdbRepository3.updateVideoUsColo(date);
		}
		
		public void updateImageUsReno(Date date){
			schedulerdbRepository3.updateImageUsReno(date);
		}
		
		public void updateImageUsColo(Date date){
			schedulerdbRepository3.updateImageUsColo(date);
			
		}
		
		public void updateAttrUsReno(Date date){
			schedulerdbRepository3.updateAttrUsReno(date);
		}
		
		public void updateAttrUkReno(Date date){
			schedulerdbRepository3.updateAttrUkReno(date);
		}
		
		public void updateLotUsColo(Date date){
			 schedulerdbRepository3.updateLotUsColo(date);
			 
		}
		
		public void updateLotUkColo(Date date){
			schedulerdbRepository3.updateLotUkColo(date);
			
		}
		
		public void updateBuyerUsColo(Date date){
			schedulerdbRepository3.updateBuyerUsColo(date);
		}
		
		public void updateBuyerUkColo(Date date){
			schedulerdbRepository3.updateBuyerUkColo(date);
		}
		
		public void updateSellerUsColo(Date date){
			schedulerdbRepository3.updateSellerUsColo(date);
		}
		
		public void updateSellerUkColo(Date date){
			schedulerdbRepository3.updateSellerUkColo(date);
		}
		
		public void updateVideoUkReno(Date date){
			schedulerdbRepository3.updateVideoUkReno(date);
		}
		
		public void updateVideoUkColo(Date date){
			schedulerdbRepository3.updateVideoUkColo(date);
		}
		
		public void updateImageUkReno(Date date){
			schedulerdbRepository3.updateImageUkReno(date);
		}
		
		public void updateImageUkColo(Date date){
			schedulerdbRepository3.updateImageUkColo(date);
		}
		
		public void updateAttrUsColo(Date date){
			schedulerdbRepository3.updateAttrUsColo(date);
		}
		
		public void updateAttrUkColo(Date date){
			schedulerdbRepository3.updateAttrUkColo(date);
		}
		
		public void updateLotUsRenoScheduler(int value){
			schedulerdbRepository3.updateLotUsRenoScheduler(value);
		}
		
		public void updateLotUkRenoScheduler(int value){
			schedulerdbRepository3.updateLotUkRenoScheduler(value);
		}
		
		public void updateBuyerUsRenoScheduler(int value){
			schedulerdbRepository3.updateBuyerUsRenoScheduler(value);
		}
		
		public void updateBuyerUkRenoScheduler(int value){
			schedulerdbRepository3.updateBuyerUkRenoScheduler(value);
		}
		
		public void updateSellerUsRenoScheduler(int value){
			schedulerdbRepository3.updateSellerUsRenoScheduler(value);
		}
		
		public void updateSellerUkRenoScheduler(int value){
			schedulerdbRepository3.updateSellerUkRenoScheduler(value);
		}
		
		public void updateVideoUsRenoScheduler(int value){
			schedulerdbRepository3.updateVideoUsRenoScheduler(value);
		}
		
		public void updateVideoUsColoScheduler(int value){
			schedulerdbRepository3.updateVideoUsColoScheduler(value);
		}
		
		public void updateImageUsRenoScheduler(int value){
			schedulerdbRepository3.updateImageUsRenoScheduler(value);
		}
		
		public void updateImageUsColoScheduler(int value){
			schedulerdbRepository3.updateImageUsColoScheduler(value);
		}
		
		public void updateAttrUsRenoScheduler(int value){
			schedulerdbRepository3.updateAttrUsRenoScheduler(value);
		}
		
		public void updateAttrUkRenoScheduler(int value){
			schedulerdbRepository3.updateAttrUkRenoScheduler(value);
		}
		
		public void updateLotUsColoScheduler(int value){
			schedulerdbRepository3.updateLotUsColoScheduler(value);
		}
		
		public void updateLotUkColoScheduler(int value){
			schedulerdbRepository3.updateLotUkColoScheduler(value);
		}
		
		public void updateBuyerUsColoScheduler(int value){
			schedulerdbRepository3.updateBuyerUsColoScheduler(value);
		}
		
		public void updateBuyerUkColoScheduler(int value){
			schedulerdbRepository3.updateBuyerUkColoScheduler(value);
		}
		
		public void updateSellerUsColoScheduler(int value){
			schedulerdbRepository3.updateSellerUsColoScheduler(value);
		}
		
		public void updateSellerUkColoScheduler(int value){
			schedulerdbRepository3.updateSellerUkColoScheduler(value);
		}
		
		public void updateVideoUkRenoScheduler(int value){
			schedulerdbRepository3.updateVideoUkRenoScheduler(value);
		}
		
		public void updateVideoUkColoScheduler(int value){
			schedulerdbRepository3.updateVideoUkColoScheduler(value);
		}
		
		public void updateImageUkRenoScheduler(int value){
			schedulerdbRepository3.updateImageUkRenoScheduler(value);
		}
		
		public void updateImageUkColoScheduler(int value){
			schedulerdbRepository3.updateImageUkColoScheduler(value);
		}
		
		public void updateAttrUsColoScheduler(int value){
			schedulerdbRepository3.updateAttrUsColoScheduler(value);
		}
		
		public void updateAttrUkColoScheduler(int value){
			schedulerdbRepository3.updateAttrUkColoScheduler(value);
		}
		
		public List<SchedulerInfo> getSchedulerInfo(){
			return schedulerdbRepository3.getSchedulerInfo();
		}
		
		public void insertEmailsInfo(String email){
			schedulerdbRepository2.insertEmailsInfo(email);
		}
		
		public List<Emails> getAllReceivers(){
			return schedulerdbRepository2.getAllReceivers();
		}
		
		public void deleteEmailsInfo(String email_id){
		  schedulerdbRepository2.deletefromEmailsInfo(email_id);;
		}
}
