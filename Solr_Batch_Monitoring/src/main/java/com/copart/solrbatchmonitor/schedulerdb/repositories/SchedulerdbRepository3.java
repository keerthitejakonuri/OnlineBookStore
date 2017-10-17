package com.copart.solrbatchmonitor.schedulerdb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerInfo;
@Transactional
@Repository
public interface SchedulerdbRepository3 extends JpaRepository<SchedulerInfo, Long> {
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='lot' and data_center ='us_reno';",nativeQuery=true) 
	public int updateLotUsReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='lot' and data_center ='us_colo';",nativeQuery=true) 
	public int updateLotUsColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='lot' and data_center ='us_reno';",nativeQuery=true) 
	public int updateLotUsRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='lot' and data_center ='us_colo';",nativeQuery=true) 
	public int updateLotUsColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='lot' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateLotUkReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='lot' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateLotUkColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='lot' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateLotUkRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='lot' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateLotUkColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='buyer' and data_center ='us_reno';",nativeQuery=true) 
	public int updateBuyerUsReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='buyer' and data_center ='us_colo';",nativeQuery=true) 
	public int updateBuyerUsColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='buyer' and data_center ='us_reno';",nativeQuery=true) 
	public int updateBuyerUsRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='buyer' and data_center ='us_colo';",nativeQuery=true) 
	public int updateBuyerUsColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='buyer' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateBuyerUkReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='buyer' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateBuyerUkColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='buyer' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateBuyerUkRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='buyer' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateBuyerUkColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='seller' and data_center ='us_reno';",nativeQuery=true) 
	public int updateSellerUsReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='seller' and data_center ='us_colo';",nativeQuery=true) 
	public int updateSellerUsColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='seller' and data_center ='us_reno';",nativeQuery=true) 
	public int updateSellerUsRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='seller' and data_center ='us_colo';",nativeQuery=true) 
	public int updateSellerUsColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='seller' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateSellerUkReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='seller' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateSellerUkColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='seller' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateSellerUkRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='seller' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateSellerUkColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='video' and data_center ='us_reno';",nativeQuery=true) 
	public int updateVideoUsReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='video' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateVideoUkReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='video' and data_center ='us_reno';",nativeQuery=true) 
	public int updateVideoUsRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='video' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateVideoUkRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='video' and data_center ='us_colo';",nativeQuery=true) 
	public int updateVideoUsColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='video' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateVideoUkColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='video' and data_center ='us_colo';",nativeQuery=true) 
	public int updateVideoUsColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='video' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateVideoUkColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='image' and data_center ='us_reno';",nativeQuery=true) 
	public int updateImageUsReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='image' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateImageUkReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='image' and data_center ='us_reno';",nativeQuery=true) 
	public int updateImageUsRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='image' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateImageUkRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='image' and data_center ='us_colo';",nativeQuery=true) 
	public int updateImageUsColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='image' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateImageUkColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='image' and data_center ='us_colo';",nativeQuery=true) 
	public int updateImageUsColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='image' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateImageUkColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='attr' and data_center ='us_reno';",nativeQuery=true) 
	public int updateAttrUsReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='attr' and data_center ='us_colo';",nativeQuery=true) 
	public int updateAttrUsColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='attr' and data_center ='us_reno';",nativeQuery=true) 
	public int updateAttrUsRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='attr' and data_center ='us_colo';",nativeQuery=true) 
	public int updateAttrUsColoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='attr' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateAttrUkReno(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set last_ran_at = :time where object_type ='attr' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateAttrUkColo(@Param("time") Date time);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='attr' and data_center ='uk_reno';",nativeQuery=true) 
	public int updateAttrUkRenoScheduler(@Param("value") int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE scheduler_info set scheduler_interval = :value where object_type ='attr' and data_center ='uk_colo';",nativeQuery=true) 
	public int updateAttrUkColoScheduler(@Param("value") int value);
	
	@Query(value="SELECT * FROM scheduler_info;",nativeQuery=true)
	public List<SchedulerInfo> getSchedulerInfo();
}
