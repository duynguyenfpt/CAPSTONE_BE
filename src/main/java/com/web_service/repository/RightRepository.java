package com.web_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.web_service.entity.RightEntity;

public interface RightRepository extends JpaRepository<RightEntity, Long> {
	@Query(value = "SELECT r.* FROM rights as r "
			+ "inner join account_right as a "
			+ "on r.id = a.right_id where a.account_id = ?1 "
			+ "where r.deleted = false "
			+ "ORDER BY id DESC \n#pageable\n",
			 countQuery = "SELECT r.* FROM rights as r "
						+ "inner join account_right as a "
						+ "on r.id = a.right_id where a.account_id = ?1 "
						+ "ORDER BY id DESC \n#pageable\n",
			nativeQuery = true)
    Page<RightEntity> findRightByAccountId(long accountId, Pageable pageable);
	
	@Query(value = "SELECT count(r.id) FROM rights as r"
			+ "	inner join account_right as a"
			+ "	on r.id = a.right_id where a.account_id = ?1 "
			+ " and r.deleted = false",
	        countQuery = "SELECT r.* FROM rights as r"
	    			+ "	inner join account_right as a"
	    			+ "	on r.id = a.right_id where a.account_id = ?1" 
	    			+ " and r.deleted = false",
	        nativeQuery = true
	)
    int countByAccountId(long accountId);
}
