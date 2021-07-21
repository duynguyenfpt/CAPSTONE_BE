package com.web_service.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import com.web_service.entity.mongo.JobLogEntity;

public interface IJobLogService {
	JobLogEntity save(JobLogEntity jobLogEntity);
	List<JobLogEntity> findAll(Pageable pageable);
	int totalItem();
	List<JobLogEntity> findAllByJobId(long jobId, Pageable pageable);
	int totalItemByJobId(long jobId);
}
