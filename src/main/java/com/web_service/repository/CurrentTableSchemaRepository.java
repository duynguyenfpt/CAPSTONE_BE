package com.web_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.CurrentTableSchemaEntity;

public interface CurrentTableSchemaRepository extends JpaRepository<CurrentTableSchemaEntity, Long> {
	@Query(value = "select * from current_table_schemas " +
	        "where (table_info_id LIKE ?1 ) " +
	        "ORDER BY id \n#pageable\n" ,
	        countQuery = "select * from current_table_schemas " +
	    	        "where (table_info_id LIKE ?1 ) " +
	    	        "ORDER BY id \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<CurrentTableSchemaEntity> findByTableInfoId(long tableInfoId, Pageable pageable);
	
	@Query(value = "select * from current_table_schemas " +
	        "where (table_info_id = ?1 ) ",
	        countQuery = "select * from current_table_schemas " +
	    	        "where (table_info_id = ?1 ) " ,
	        nativeQuery = true
	)
    List<CurrentTableSchemaEntity> countByTableInfoId(long tableInfoId);
}
