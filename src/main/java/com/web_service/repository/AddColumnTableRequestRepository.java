package com.web_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.AddColumnTableRequestEntity;

public interface AddColumnTableRequestRepository extends JpaRepository<AddColumnTableRequestEntity, Long> {
	@Query(value = "select * from add_column_table_requests " +
	        "where (request_id = ?1 ) " ,
	        countQuery = "select * from add_column_table_request " +
	    	        "where (request_id = ?1 ) " ,
	        nativeQuery = true
	)
	List<AddColumnTableRequestEntity> findByRequestId(Long requestId);
}
