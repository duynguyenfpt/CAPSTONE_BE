package com.web_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findByUsername(String username);
	
	@Query(value = "select * from accounts " +
	        "where (email LIKE %?1% ) " +
	        "or (phone LIKE %?1% ) " +
	        "or (role LIKE %?1%) " +
	        "or (username LIKE %?1%) " +
	        "ORDER BY id \n#pageable\n" ,
	        countQuery = "select * from accounts " +
	    	        "where (email LIKE %?1% ) " +
	    	        "or (phone LIKE %?1% ) " +
	    	        "or (role LIKE %?1%) " +
	    	        "or (username LIKE %?1%) " +
	    	        "ORDER BY id \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<AccountEntity> getAllAccount(String keyword, Pageable pageable);
	
	@Query(value = "select COUNT(*) from accounts " +
	        "where (email LIKE %?1% ) " +
	        "or (phone LIKE %?1% ) " +
	        "or (role LIKE %?1%) " +
	        "or (username LIKE %?1%) ",
	        nativeQuery = true
	)
    int countAccount(String keyword);
}
