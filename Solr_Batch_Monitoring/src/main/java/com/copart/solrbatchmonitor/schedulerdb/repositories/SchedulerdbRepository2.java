package com.copart.solrbatchmonitor.schedulerdb.repositories;

//import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copart.solrbatchmonitor.schedulerdb.entities.Emails;

@Transactional(readOnly = true)
@Repository
public interface SchedulerdbRepository2 extends JpaRepository<Emails, Long> {
	
	@Query(value="SELECT email_id, type FROM Emails_Info  where type= 'r';",nativeQuery=true) 
	public List<Emails> getAllReceivers();
	
	@Query(value="SELECT email_id, type FROM Emails_Info e where type= 's' LIMIT 1;",nativeQuery=true) 
	public Emails getSender();
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO Emails_Info (email_id,type) VALUES(:email,'r');",nativeQuery=true) 
	public int insertEmailsInfo(@Param("email") String email);
	
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM Emails_Info WHERE email_id =?1 and type= 'r';",nativeQuery=true) 
	public void deletefromEmailsInfo(String email_id);
}
