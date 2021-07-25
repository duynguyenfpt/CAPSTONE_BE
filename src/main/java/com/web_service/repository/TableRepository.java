package com.web_service.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

	Page<TableEntity> findByDatabaseInfo(DatabaseInfoEntity databaseInfoEntity, Pageable pageable);
	
	@Query(value = "select * from tables where LOWER(table_name) LIKE %?1% ORDER BY id \n#pageable\n",
			countQuery = "select * from tables where LOWER(table_name) LIKE %?1% ORDER BY id \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<TableEntity> search(String keyword, Pageable pageable);
	
	@Query(value = "select COUNT(*) from tables " +
	        "where LOWER(table_name) LIKE %?1%",
	        nativeQuery = true
	)
    int countSearchTotalItem(String keyword);
}
