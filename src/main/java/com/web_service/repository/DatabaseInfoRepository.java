package com.web_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.DatabaseInfoEntity;

public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfoEntity, Long>{
	@Query(value = "select * from database_infos " +
			"where (deleted = false ) " +
	        "and (username LIKE %?1%  " +
	        "or port LIKE %?1% " +
	        "or database_type LIKE %?1% " +
	        "or database_name LIKE %?1% " +
	        "or modified_date LIKE %?1% " +
	        "or modified_by LIKE %?1% " +
	        "or alias LIKE %?1%) " +
	        "ORDER BY id DESC \n#pageable\n" ,
	        countQuery =  "select * from database_infos " +
	    			"where deleted = false " +
	    	        "and (username LIKE %?1%  " +
	    	        "or port LIKE %?1% " +
	    	        "or database_type LIKE %?1% " +
	    	        "or database_name LIKE %?1% " +
	    	        "or modified_date LIKE %?1% " +
	    	        "or modified_by LIKE %?1% " +
	    	        "or alias LIKE %?1%) " +
	    	        "ORDER BY id DESC \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<DatabaseInfoEntity> search(String keyword, Pageable pageable);
	
	@Query(value =  "select COUNT(*) from database_infos " +
			"where deleted = false " +
	        "and (username LIKE %?1%  " +
	        "or port LIKE %?1% " +
	        "or database_type LIKE %?1% " +
	        "or database_name LIKE %?1% " +
	        "or modified_date LIKE %?1% " +
	        "or modified_by LIKE %?1% " +
	        "or alias LIKE %?1%) ",
	        nativeQuery = true
	)
    int search(String keyword);
}
	