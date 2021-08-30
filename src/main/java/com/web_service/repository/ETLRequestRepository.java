package com.web_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web_service.entity.ETLEntity;

public interface ETLRequestRepository extends JpaRepository<ETLEntity, Long>{
	ETLEntity findByRequestId(Long requestId);
	
	Page<ETLEntity> findAllByOrderByIdDesc(Pageable pageable);
	
	@Query(value = "select etl_request.* from etl_request inner join request "
			+ "on etl_request.request_type_id = request.id where created_by = ?1"
			+ " ORDER BY id DESC \n#pageable\n",
	        countQuery =  "select etl_request.* from etl_request inner join request "
	    			+ "on etl_request.request_type_id = request.id where created_by = ?1"
	    			+ " ORDER BY id DESC \n#pageable\n",
	        nativeQuery = true
	)
	Page<ETLEntity> findAllByCreator(String username, Pageable pageable);
	
	@Query(value = "select count(*) from etl_request inner join request "
			+ "on etl_request.request_type_id = request.id where created_by = ?1",
	        nativeQuery = true
	)
	int countAllByCreator(String username);

}
