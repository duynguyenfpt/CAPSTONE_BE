package com.web_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.web_service.entity.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
//	@Query(value = "select * from request " +
//	        "where (request_type LIKE %?1% ) " +
//	        "or (approved_by LIKE %?1% ) " +
//	        "and deleted = false " +
//	        "ORDER BY id DESC \n#pageable\n" ,
//	        countQuery = "select * from request " +
//	    	        "where (request_type LIKE %?1% ) " +
//	    	        "or (approved_by LIKE %?1% ) " +
//	    	        "and deleted = false " +
//	    	        "ORDER BY id DESC \n#pageable\n" ,
//	        nativeQuery = true
//	)
//    Page<RequestEntity> getAllRequest(String keyword, Pageable pageable);
//	
//	@Query(value ="select COUNT(*) from request " +
//	        "where (request_type LIKE %?1% ) " +
//	        "or (approved_by LIKE %?1% ) " +
//	        "and deleted = false " +
//	        "ORDER BY id DESC \n#pageable\n",
//	        nativeQuery = true
//	)
//    int countRequest(String keyword);
}
