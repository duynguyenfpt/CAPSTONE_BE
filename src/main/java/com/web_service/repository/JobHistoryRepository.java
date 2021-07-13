package com.web_service.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web_service.entity.mongo.JobHistoryEntity;

public interface JobHistoryRepository extends MongoRepository<JobHistoryEntity, ObjectId> {
	Page<JobHistoryEntity> findByJobId(long jobId, Pageable pageable);
	
	List<JobHistoryEntity> getByJobId(long jobId);

}
