package com.hoc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hoc.entity.DatabaseInfoEntity;

public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfoEntity, Long>{
	@Query(value = "select * from database_infos " +
	        "where (username LIKE %?1% ) " +
	        "or (port LIKE %?1% ) " +
	        "or (database_type LIKE %?1%) " +
	        "or (database_name LIKE %?1%) " +
	        "or (modified_date LIKE %?1%) " +
	        "or (modified_by LIKE %?1%) " +
	        "ORDER BY id \n#pageable\n" ,
	        countQuery = "select * from database_infos " +
	    	        "where (username LIKE %?1% ) " +
	    	        "or (port LIKE %?1% ) " +
	    	        "or (database_type LIKE %?1%) " +
	    	        "or (database_name LIKE %?1%) " +
	    	        "or (modified_date LIKE %?1%) " +
	    	        "or (modified_by LIKE %?1%) " +
	    	        "ORDER BY id \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<DatabaseInfoEntity> search(String keyword, Pageable pageable);
}
