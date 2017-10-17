package com.copart.solrbatchmonitor.us.colomariadb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.copart.solrbatchmonitor.us.colomariadb.entities.UsColoMariadb;


@Repository
public interface UsColoMariadbRepository extends JpaRepository<UsColoMariadb, Integer> {

	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='lot' order by batch_id DESC limit 1",nativeQuery=true) 
	public UsColoMariadb findTableLot();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='buyer' order by batch_id DESC limit 1",nativeQuery=true) 
	public UsColoMariadb findTableBuyer();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='seller' order by batch_id DESC limit 1",nativeQuery=true) 
	public UsColoMariadb findTableSeller();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' order by batch_id DESC limit 1",nativeQuery=true) 
	public UsColoMariadb findTableVideo();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='attr' order by batch_id DESC limit 1",nativeQuery=true) 
	public UsColoMariadb findTableAttr();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='lot' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UsColoMariadb> findPageLot(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From misprddb.batch_search where object_type='lot'",nativeQuery=true) 
	public int findPageLotCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='buyer' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UsColoMariadb> findPageBuyer(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From misprddb.batch_search where object_type='buyer'",nativeQuery=true) 
	public int findPageBuyerCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='seller' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UsColoMariadb> findPageSeller(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From misprddb.batch_search where object_type='seller'",nativeQuery=true) 
	public int findPageSellerCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UsColoMariadb> findPageVideo(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From copdta.batch_search where object_type='video'",nativeQuery=true) 
	public int findPageVideoCount();
	
	@Query(value="SELECT SQL_CALC_FOUND_ROWS object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='attr' order by batch_id DESC limit :offset, :noOfRecords",nativeQuery=true) 
	public List<UsColoMariadb> findPageAttr(@Param("offset") int offset, @Param("noOfRecords") int noOfRecords);
	
	@Query(value="SELECT count(*) From misprddb.batch_search where object_type='attr'",nativeQuery=true) 
	public int findPageAttrCount();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='lot' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findTodayGraphLot();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='buyer' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findTodayGraphBuyer();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='seller' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findTodayGraphSeller();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findTodayGraphVideo();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='attr' and start_dttm >= CURDATE() Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findTodayGraphAttr();
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='lot' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findRangeGraphLot(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='buyer' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findRangeGraphBuyer(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='seller' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findRangeGraphSeller(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From copdta.batch_search where object_type='video' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findRangeGraphVideo(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT object_type, user, batch_status, batch_id, start_dttm, end_dttm, update_dttm, object_count, cast(update_dttm as signed) - cast(end_dttm as signed) as param From misprddb.batch_search where object_type='attr' and (DATE(start_dttm) <= :to) and (DATE(start_dttm) >= :from) Order by batch_id DESC",nativeQuery=true) 
	public List<UsColoMariadb> findRangeGraphAttr(@Param("from") Date from, @Param("to") Date to);
	
}
