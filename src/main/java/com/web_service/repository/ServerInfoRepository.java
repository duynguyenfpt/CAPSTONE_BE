package com.web_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.ServerInfoEntity;

public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Long> {
	@Query(value = "select * from server_infos " +
	        "where (server_domain LIKE %?1% " +
	        "or server_host LIKE %?1% ) " +
	        "and deleted = false " +
	        "ORDER BY id DESC \n#pageable\n" ,
	        countQuery = "select * from server_infos " +
	    	        "where (server_domain LIKE %?1% " +
	    	        "or server_host LIKE %?1% ) " +
	    	        "and deleted = false " +
	    	        "ORDER BY id DESC \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<ServerInfoEntity> getAllServerInfo(String keyword, Pageable pageable);
	
	@Query(value ="select COUNT(*) from server_infos " +
	        "where (server_domain LIKE %?1% " +
	        "or server_host LIKE %?1% ) " +
	        "and deleted = false ",
	        nativeQuery = true
	)
    int countServerInfo(String keyword);
	
	
	@Query(value ="select * from server_infos " +
	        "where server_domain LIKE ?1 " +
	        "and deleted = false ",
	        nativeQuery = true
	)
    List<ServerInfoEntity> findByServerDomain(String serverDomain);
	
	@Query(value ="select * from server_infos " +
	        "where server_host LIKE ?1 " +
	        "and deleted = false ",
	        nativeQuery = true
	)
	List<ServerInfoEntity> findByServerHost(String serverHost);
}
