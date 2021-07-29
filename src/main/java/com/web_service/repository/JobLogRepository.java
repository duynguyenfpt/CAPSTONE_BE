package com.web_service.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web_service.entity.mongo.JobLogEntity;

public interface JobLogRepository extends MongoRepository<JobLogEntity, ObjectId> {
	JobLogEntity findById(ObjectId _id);
	Page<JobLogEntity> findByJobIdOrderByCreatedTimeDesc(long jobId, Pageable pageable);
	List<JobLogEntity> getAllJobLogByJobId(long jobId);
	Page<JobLogEntity> findAllByOrderByCreatedTimeDesc(Pageable pageable);
}
