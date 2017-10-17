package com.copart.solrbatchmonitor.uk.colomariadb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.copart.solrbatchmonitor.uk.colomariadb.entities.UkColoMariadb;

@Repository
public interface UkColoMariadbRepository extends JpaRepository<UkColoMariadb, Integer> {

	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='lot' order by batch_id DESC limit 1",nativeQuery=true) 
	public UkColoMariadb findTableLot();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='buyer' order by batch_id DESC limit 1",nativeQuery=true) 
	public UkColoMariadb findTableBuyer();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='seller' order by batch_id DESC limit 1",nativeQuery=true) 
	public UkColoMariadb findTableSeller();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' order by batch_id DESC limit 1",nativeQuery=true) 
	public UkColoMariadb findTableVideo();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='attr' order by batch_id DESC limit 1",nativeQuery=true) 
	public UkColoMariadb findTableAttr();

	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='lot' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UkColoMariadb> findPageLot(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From gbrprddb.batch_search where object_type='lot'",nativeQuery=true) 
	public int findPageLotCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='buyer' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UkColoMariadb> findPageBuyer(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From gbrprddb.batch_search where object_type='buyer'",nativeQuery=true) 
	public int findPageBuyerCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='seller' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UkColoMariadb> findPageSeller(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From gbrprddb.batch_search where object_type='seller'",nativeQuery=true) 
	public int findPageSellerCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UkColoMariadb> findPageVideo(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From copdta.batch_search where object_type='video'",nativeQuery=true) 
	public int findPageVideoCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='attr' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UkColoMariadb> findPageAttr(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From misprddb.batch_search where object_type='attr'",nativeQuery=true) 
	public int findPageAttrCount();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='lot' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findTodayGraphLot();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='buyer' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findTodayGraphBuyer();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='seller' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findTodayGraphSeller();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findTodayGraphVideo();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='attr' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findTodayGraphAttr();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='lot' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findRangeGraphLot(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='buyer' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findRangeGraphBuyer(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='seller' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findRangeGraphSeller(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findRangeGraphVideo(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From gbrprddb.batch_search where object_type='attr' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UkColoMariadb> findRangeGraphAttr(@Param("from") Date from, @Param("to") Date to);
	
}
