package com.copart.solrbatchmonitor.schedulerdb.repositories;

//import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copart.solrbatchmonitor.schedulerdb.entities.SchedulerTimings;

@Transactional
@Repository
public interface SchedulerdbRepository1 extends JpaRepository<SchedulerTimings, Long> {
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type= 'lot' and s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllLot();
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type= 'buyer' and s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllBuyer();
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type= 'seller' and s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllSeller();
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type= 'video' and s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllVideo();
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type= 'image' and s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllImage();
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type= 'attr' and s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllAttr();
	
	@Query(value="SELECT s.object_type, s.data_center,s.scheduler_interval, t.batch_interval,t.Start_Time,t.End_Time FROM scheduler_info s, Timings t where s.object_type = t.object_type and s.data_center = t.data_center;",nativeQuery=true) 
	public List<SchedulerTimings> getAllConditions();
}
