package com.web_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.AccountEntity;
import com.web_service.entity.ServerInfoEntity;

public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Long> {
	@Query(value = "select * from server_infors " +
	        "where (server_domain LIKE %?1% ) " +
	        "or (server_host LIKE %?1% ) " +
	        "and deleted = false" +
	        "ORDER BY id DESC \n#pageable\n" ,
	        countQuery = "select * from server_infors " +
	    	        "where (server_domain LIKE %?1% ) " +
	    	        "or (server_host LIKE %?1% ) " +
	    	        "ORDER BY id DESC \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<AccountEntity> getAllServerInfo(String keyword, Pageable pageable);
	
	@Query(value = "select count(*) from server_infors " +
	        "where (server_domain LIKE %?1% ) " +
	        "or (server_host LIKE %?1% ) " +
	        "and deleted = false",
	        nativeQuery = true
	)
    int countServerInfo(String keyword);
	
}
