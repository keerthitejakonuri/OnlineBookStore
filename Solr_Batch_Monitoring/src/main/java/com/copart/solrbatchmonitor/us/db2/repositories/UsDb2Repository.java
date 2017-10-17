package com.copart.solrbatchmonitor.us.db2.repositories;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.copart.solrbatchmonitor.us.db2.entities.UsDb2;


@Repository
public interface UsDb2Repository extends JpaRepository<UsDb2, Integer> {
		
	
	@Query(value="SELECT object_type, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param FROM COPDTA.SOLR_BATCH_SEARCH where (object_type='IMAGE_RENO') Order by batch_id DESC limit 1",nativeQuery=true) 
	public UsDb2 findTableImgReno();
	
	@Query(value="SELECT object_type, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param FROM COPDTA.SOLR_BATCH_SEARCH where (object_type='IMAGE_COLO') Order by batch_id DESC limit 1",nativeQuery=true) 
	public UsDb2 findTableImgColo();
	
	@Query(value="SELECT object_type, batch_id, batch_status, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param From (SELECT ROW_NUMBER() OVER() AS rownum, COPDTA.SOLR_BATCH_SEARCH.* FROM COPDTA.SOLR_BATCH_SEARCH where object_type='IMAGE_RENO') AS tmp WHERE rownum > :offset AND rownum <= :noOfRecords order by batch_id DESC",nativeQuery=true) 
	public List<UsDb2> findPageImgReno(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) FROM COPDTA.SOLR_BATCH_SEARCH where object_type='IMAGE_RENO'",nativeQuery=true) 
	public int findPageImgRenoCount();
	
	@Query(value="SELECT object_type, batch_id, batch_status, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param From (SELECT ROW_NUMBER() OVER() AS rownum, COPDTA.SOLR_BATCH_SEARCH.* FROM COPDTA.SOLR_BATCH_SEARCH where object_type='IMAGE_COLO') AS tmp WHERE rownum > :offset AND rownum <= :noOfRecords order by batch_id DESC",nativeQuery=true) 
	public List<UsDb2> findPageImgColo(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) FROM COPDTA.SOLR_BATCH_SEARCH where  object_type='IMAGE_COLO'",nativeQuery=true) 
	public int findPageImgColoCount();
	
	@Query(value="SELECT object_type, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param FROM COPDTA.SOLR_BATCH_SEARCH where DATE(start_dttm) >= (SELECT CURRENT date FROM sysibm.sysdummy1) and (object_type='IMAGE_RENO') Order by batch_id DESC",nativeQuery=true) 
	public List<UsDb2> findTodayGraphImgReno();
	
	@Query(value="SELECT object_type, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param FROM COPDTA.SOLR_BATCH_SEARCH where DATE(start_dttm) >= (SELECT CURRENT date FROM sysibm.sysdummy1) and (object_type='IMAGE_COLO') Order by batch_id DESC",nativeQuery=true) 
	public List<UsDb2> findTodayGraphImgColo();
	
	@Query(value="SELECT object_type, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param FROM COPDTA.SOLR_BATCH_SEARCH where (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) and (object_type='IMAGE_RENO') Order by batch_id DESC",nativeQuery=true) 
	public List<UsDb2> findRangeGraphImgReno(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, timestampdiff(2, char(update_dttm - end_dttm)) as param FROM COPDTA.SOLR_BATCH_SEARCH where (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) and (object_type='IMAGE_COLO') Order by batch_id DESC",nativeQuery=true) 
	public List<UsDb2> findRangeGraphImgColo(@Param("from") Date from, @Param("to") Date to);
	
}
