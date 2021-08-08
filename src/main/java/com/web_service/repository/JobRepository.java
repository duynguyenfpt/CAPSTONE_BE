package com.web_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Long>{
	@Query(value = "select * from jobs " +
	        "where (job_schedule LIKE %?1% ) " +
	        "or (max_retries LIKE %?1% ) " +
	        "or (description LIKE %?1% ) " +
	        "or (status LIKE %?1% ) " +
	        "or (number_retries LIKE %?1% ) " +
	        "and deleted = false " +
	        "ORDER BY id DESC \n#pageable\n" ,
	        countQuery = "select * from jobs " +
	    	        "where (job_schedule LIKE %?1% ) " +
	    	        "or (max_retries LIKE %?1% ) " +
	    	        "or (description LIKE %?1% ) " +
	    	        "or (status LIKE %?1% ) " +
	    	        "or (number_retries LIKE %?1% ) " +
	    	        "and deleted = false " +
	    	        "ORDER BY id DESC \n#pageable\n" ,
	        nativeQuery = true
	)
    Page<JobEntity> getAllJob(String keyword, Pageable pageable);
	
	@Query(value = "select COUNT(*) from jobs " +
	        "where (job_schedule LIKE %?1% ) " +
	        "or (max_retries LIKE %?1% ) " +
	        "or (description LIKE %?1% ) " +
	        "or (status LIKE %?1% ) " +
	        "or (number_retries LIKE %?1% ) " +
	        "and deleted = false " +
	        "ORDER BY id DESC \n#pageable\n",
	        nativeQuery = true
	)
    int countJob(String keyword);
}
